<template>
  <div>
    <Header></Header>
    <div class="content clearfix">
      <div class="detail-left">
        <div class="detail-left-title">{{ product.productName }} ({{ product.productNo }} Period)</div>
        <ul class="detail-left-number">
          <li>
            <span>Historical Annual Yield</span>
            <p><b>{{ product.rate }}</b>%</p>
            <span>Historical Annual Yield</span>
          </li>
          <li>
            <span>Fundraising Amount (Yuan)</span>
            <p><b>{{ product.productMoney }}</b> Yuan</p>
            <span v-if="product.leftProductMoney > 0"> &nbsp;&nbsp; Remaining Amount {{ product.leftProductMoney }} Yuan</span>
            <span v-else>Fully Subscribed</span>
          </li>
          <li>
            <span>Investment Cycle</span>
            <p v-if="product.productType == 0"><b>{{product.cycle}}</b> Days</p>
            <p v-else><b>{{product.cycle}}</b> Months</p>
          </li>
        </ul>
        <div class="detail-left-way">
          <span>Income Acquisition Method</span>
          <span>Return on Investment: <i>All Paid at Maturity</i></span>
        </div>
        <!--Investment Records-->
        <div class="detail-record">
          <h2 class="detail-record-title">Investment Records</h2>
          <div class="detail-record-list">
            <table align="center" width="880" border="0" cellspacing="0" cellpadding="0">
              <colgroup>
                <col style="width: 72px" />
                <col style="width: 203px" />
                <col style="width: 251px" />
                <col style="width: 354px" />
              </colgroup>
              <thead class="detail_thead">
              <tr>
                <th class="large-text">No.</th>
                <th class="large-text">Investor</th>
                <th class="large-text">Investment Amount (Yuan)</th>
                <th class="large-text">Investment Time</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(bid, ind) in bidList" :key="bid.id">
                <td class="large-text">{{ ind + 1 }}</td>
                <td class="large-text">{{ bid.phone }}</td>
                <td class="large-text">{{ bid.bidMoney }}</td>
                <td class="large-text">{{ bid.bidTime }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!--Right Side-->
      <div class="detail-right">
        <div class="detail-right-title">Invest Now</div>
        <div class="detail-right-mode">
          <h3 class="detail-right-mode-title">Income Method</h3>
          <p class="detail-right-mode-p"><span>All Paid at Maturity</span></p>
          <h3 class="detail-right-mode-title">My Account Available</h3>

          <div class="detail-right-mode-rmb"  v-if="logined = false">
            <p>Funds (Yuan): ******</p>
            <a href="javascript:void(0);" @click="goLink('/page/user/login',)">Please Login</a>
          </div>

          <div class="detail-right-mode-rmb"  v-else>
            <p>Funds (Yuan): {{this.accountMoney}}</p>
          </div>

          <h3 class="detail-right-mode-title">Interest Income (Yuan) {{income}}</h3>
          <form action="" id="number_submit">
            <p>Please enter the investment amount below</p>
            <input type="text" placeholder="Please enter the daily investment amount, it should be a multiple of 100 Yuan" v-model="investMoney" @blur="checkInvestMoney" class="number-money" >
            <div class="err">{{investMoneyErr}}</div>

            <input type="button" value="Invest Now" @click="investProduct" class="submit-btn">
          </form>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import {doGet, doPost} from "@/api/httpRequest";
import layx from "vue-layx";

export default {
  name: "ProductDetail",
  components:{
    // eslint-disable-next-line vue/no-unused-components
    Header,
    // eslint-disable-next-line vue/no-unused-components
    Footer
  },
  data(){
    return {
      product:{
        id: 0,
        productName: "",
        rate: 0.0,
        cycle: 0,
        releaseTime: 0,
        productType: 0,
        productNo: "",
        productMoney: 0,
        leftProductMoney: 0,
        bidMinLimit: 0,
        bidMaxLimit: 0,
        productStatus: 0,
        productFullTime: "",
        productDesc: ""
      },
      bidList:[
        {
          id: 0,
          phone: "",
          bidTime: "",
          bidMoney: 0.00
        }],
      logined:false,
      accountMoney:0.0,
      investMoney: 100,
      investMoneyErr:'',
      income:""
    }
  },
  mounted() {
    if (window.localStorage.getItem("userinfo")){
      this.logined = true;
    }
    this.initPage();


  },
  methods:{
    goLink(url, params){
      //use router for redirecting webpage
      this.$router.push({
        path: url,
        query: params
      })
    },
    initPage(){
      let productId = this.$route.query.productId;
      doGet('/v1/product/info',{productId:productId})
          .then(resp=>{
            if( resp ) {
              this.product = resp.data.data;
              this.bidList = resp.data.list;
            }
          })

      doGet('/v1/user/usercenter').then(resp=>{
        if( resp && resp.data.code == 1000){
          this.accountMoney = resp.data.data.money;
        }
      })
    },
    checkInvestMoney(){
      if (isNaN(this.investMoney)){
        this.investMoneyErr = 'Please enter the correct amount';

      }else if (parseInt(this.investMoney) < 100){
        this.investMoneyErr = 'The minimum investment amount is 100 yuan';
      } else if (parseFloat(this.investMoney) % 100 != 0){
        this.investMoneyErr = 'The investment amount is an integer multiple of 100';
      } else {
        this.investMoneyErr = '';

        let dayRate  = this.product.rate / 365 / 100;
        let incomeMoney = 0.0;
        if( this.product.productType == 0 ) {
          incomeMoney = this.investMoney * this.product.cycle * dayRate;
        } else {
          incomeMoney = this.investMoney * (this.product.cycle * 30) * dayRate;
        }
        this.income = incomeMoney.toFixed(2);


      }
    },
    investProduct(){

      let userinfo = JSON.parse(window.localStorage.getItem("userinfo"));
      if (userinfo){
        this.checkInvestMoney();
        if (this.investMoneyErr == ''){
            doPost('/v1/invest/product',{productId: this.product.id, money: this.investMoney})
          .then(resp=>{
            if (resp && resp.data.code == 1000){
                this.initPage();
            }
          })
        }
      } else{
        layx.msg('Please log in first',{dialogIcon:'warn',position:'ct'});
      }


    }
  }
}
</script>

<style scoped>
.large-text {
  font-size: 1.5em;
}
.err {
  color: red;
  font-size: 18px;
}


table {
  width: 100%;
  border-collapse: collapse;
  overflow-x: auto;
  table-layout: fixed;
}

th, td {
  border: 1px solid #dddddd;
  text-align: center;
  padding: 4px;
  white-space: nowrap;
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
  font-size: 12px;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}
</style>