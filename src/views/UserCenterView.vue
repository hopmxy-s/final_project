<template>
  <div>
    <Header></Header>
    <div class="content clearfix">
      <!--个人信息-->
      <div class="user-head">
        <div class="user-head-left fl">
          <div class="user-head-img">
            <img src="@/assets/image/user-head.png" alt="">
          </div>
        </div>
        <div class="user-head-right fl">
          <ul class="user-head-name fl">
            <li><b>{{userBaseInfo.name}}</b></li>
            <li>{{ userBaseInfo.phone }}</li>
            <li>Latest Login：{{userBaseInfo.loginTime}}</li>
          </ul>
          <div class="user-head-money fr">
            <p>Available Balance：<span>￥{{ userBaseInfo.money }}</span></p>
            <a href="javascript:void(0)" @click="goLink('/')"  style="color: red" class="user-head-a2">Invest</a>
          </div>
        </div>

      </div>
      <!--记录-->
      <div class="user-record-box clearfix">
        <div class="user-record user-record-1">
          <h3 class="user-record-title">Recent Investment</h3>
          <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
            <thead class="datail_thead">
            <tr>
              <th>Serial number	</th>
              <th>Product name</th>
              <th>Invest amount</th>
              <th>Invest date</th>
            </tr>
            </thead>

            <tbody>
              <tr v-for="(item,index) in investList" :key="item.id">
                <td>{{index+1}}</td>
                <td>{{item.productName}}</td>
                <td>{{item.bidMoney}}</td>
                <td>{{item.bidTime}}</td>
              </tr>

            </tbody>

          </table>
        </div>
        <div class="user-record user-record-2">
          <h3 class="user-record-title">Recent Top Up</h3>
          <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
            <thead class="datail_thead">
            <tr>
              <th> Serial number </th>
              <th> Result </th>
              <th> Amount </th>
              <th> Recharge time </th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item,index) in rechargeList" :key="item.id">
              <td>{{index+1}}</td>
              <td>{{item.result}}</td>
              <td>{{item.rechargeMoney}}</td>
              <td>{{item.rechargeDate}}</td>
            </tr>

            </tbody>
          </table>
        </div>
        <div class="user-record user-record-3">
          <h3 class="user-record-title ">Recent Return</h3>
          <table align="center" width="388" border="0" cellspacing="0" cellpadding="0">
            <thead class="datail_thead">
            <tr>
              <th>Serial number</th>
              <th>Product name</th>
              <th>Invest date</th>
              <th>Income</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="(item,index) in incomeList" :key="item.id">
                <td>{{index+1}}</td>
                <td>{{item.prodId}}</td>
                <td>{{item.incomeDate}}</td>
                <td>{{item.incomeMoney}}</td>
              </tr>
            </tbody>

          </table>
        </div>

      </div>


    </div>


    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import {doGet} from "@/api/httpRequest";

export default {
  name: "UserCenterView",
  components: {
    // eslint-disable-next-line vue/no-unused-components
    Header,
    // eslint-disable-next-line vue/no-unused-components
    Footer
  },
  data() {
    return {
      userBaseInfo: {
        loginTime: "",
        money: 0.0,
        phone: "",
        name: "",
        headerUrl: ""
      },
      rechargeList:[
        {
          id: 0,
          result: "",
          rechargeDate: "",
          rechargeMoney: 0
        },

      ],
      incomeList:[
        {
          id: 0,
          prodId: "",
          incomeDate: "",
          incomeMoney: 0
        },

      ],
        investList:[
      {
        id: 0,
        productName: "",
        bidTime: "",
        bidMoney: 0
      }
    ]
    }
  },
  mounted() {
    doGet('/v1/user/usercenter').then(resp => {
      if (resp && resp.data.code == 1000) {
        this.userBaseInfo = resp.data.data;
      }
    });

    doGet('/v1/recharge/records',{pageNo:1,pageSize: 6}).then(resp=>{
      if(resp  && resp.data.code == 1000){
        this.rechargeList = resp.data.list;
      }
    });

    doGet('/v1/income/records',{pageNo:1,pageSize: 6}).then(resp=>{
      if(resp  && resp.data.code == 1000){
        this.incomeList = resp.data.list;
      }
    });
    doGet('/v1/bid/records',{pageNo:1,pageSize: 6}).then(resp=>{
      if(resp  && resp.data.code == 1000){
        this.investList = resp.data.list;
      }
    });

  },
  methods:{
    goLink(url, params){
      //use router for redirecting webpage
      this.$router.push({
        path: url,
        query: params
      })
    },
  }

}
</script>

<style scoped>





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