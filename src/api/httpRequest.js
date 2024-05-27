import axios from "axios";
import qs from 'qs';

// set default
axios.defaults.baseURL="http://localhost:8000/api";
axios.defaults.timeout=50000;


//get request method
//params: json object, request parameters
function doGet(url, params){
    return axios({
        url:url,
        method:'get',
        params:params
    });
}

//transfer json data in request
function doPostJson(url, params) {
    return axios({
        url:url,
        method:'post',
        data:params
    });
}

function doPost(url, params){
    let requestData = qs.stringify(params);
    return axios.post(url, requestData);
}

axios.interceptors.request.use(function (config) {

    let storageToken = window.localStorage.getItem("token");
    let userinfo = window.localStorage.getItem("userinfo");
    if (storageToken && userinfo) {
        if ( config.url == '/v1/user/usercenter' || config.url == '/v1/income/records' ||
            config.url == '/v1/bid/records' ||
            config.url == '/v1/recharge/records' || config.url=='/v1/invest/product') {

            config.headers['Authorization'] = 'Bearer ' + storageToken;
            config.headers['uid'] = JSON.parse(userinfo).uid;
        }
    }
    return config;
}, function (err) {
    console.log("请求错误" + err);
})

axios.interceptors.response.use(function (resp) {
    if (resp && resp.data.code > 1000) {
        let code = resp.data.code;
        if (code == 3000) {
            //token无效，重新登录
            window.location.href = '/page/user/login';
        } else {
            alert(resp.data.msg, {dialogIcon: 'warn', position: 'ct'});
        }
    }
    return resp;
}, function (err) {
    console.log("应答拦截器错误：" + err)
    //回到首页
    //window.location.href = '/';
})

//exposure for this function
export { doGet, doPost, doPostJson}