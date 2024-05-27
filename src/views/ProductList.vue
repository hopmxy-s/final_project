<template>
  <div>
    <Header></Header>
    <div class="content clearfix">
      <!-- Ranking List -->
      <ul class="rank-list">
        <li v-for="(item, index) in rank" :key="item.phone">
          <img src="@/assets/image/list-rank1.png" alt="" v-if="index == 0">
          <img src="@/assets/image/list-rank2.png" alt="" v-else-if="index == 1">
          <img src="@/assets/image/list-rank3.png" alt="" v-else>
          <p class="rank-list-phone">{{ item.phone }}</p>
          <span>{{ item.money }} CNY</span>
        </li>
      </ul>
      <!-- Product List -->
      <ul class="preferred-select clearfix">
        <li v-for="product in productList" :key="product.id">
          <h3 class="preferred-select-title">
            <span>{{ product.productName }}</span>
            <img src="@/assets/image/1-bg1.jpg" alt="">
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>Annualized Return</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>Investment Cycle</span>
              <p><b>{{ product.cycle }}</b> months</p>
            </div>
            <div>
              <span>Remaining Investable Amount</span>
              <p><b>{{ product.leftProductMoney }}</b> CNY</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            Preferred plan project, investment return cycle {{ product.cycle }} months, suitable for short-term capital turnover.
          </p>
          <a href="javascript:void(0)" @click="goLink('/page/product/detail', {productId: product.id})" class="preferred-select-btn">Invest Now</a>
        </li>
      </ul>

      <!-- Pagination -->
      <div class="page_box">
        <ul class="pagination">
          <li><a href="javascript:void(0)" @click="first">First</a></li>
          <li><a href="javascript:void(0)" @click="pre">Previous</a></li>
          <li class="active"><span>{{page.pageNo}}</span></li>
          <li><a href="javascript:void(0)" @click="next">Next</a></li>
          <li><a href="javascript:void(0)" @click="last">Last</a></li>
          <li class="totalPages"><span>Total {{page.totalPage}} Pages</span></li>
        </ul>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import {doGet} from "@/api/httpRequest";
import layx from "vue-layx";

let  productType = 0 ;

export default {
  name: "ProductList",
  components: {
    // eslint-disable-next-line vue/no-unused-components
    Header,
    // eslint-disable-next-line vue/no-unused-components
    Footer
  },
  data() {
    return {
      rank: [
        {
          "phone": "",
          "money": 0
        }
      ],
      productList: [{
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
      }],
      page:{
        pageNo: 1,
        pageSize: 0,
        totalPage: 0,
        totalRecord: 0
      }
    }
  },
  mounted() {
    productType =  this.$route.query.ptype;
    this.initPage(productType,1,9);


    doGet('/v1/invest/rank').then(resp => {
      if (resp) {
        this.rank = resp.data.list;
      }
    })
  },
  methods: {
    goLink(url, params){
      //use router for redirecting webpage
      this.$router.push({
        path: url,
        query: params
      })
    },
    initPage(productType,pNo,pSize) {

      doGet('/v1/product/list', {ptype: productType, pageNo: pNo, pageSize: pSize})
          .then(resp => {
            if (resp) {
              this.productList = resp.data.list;
              this.page  = resp.data.page;
            }
          })
    },
    first(){
      if( this.page.pageNo == 1 ){
        layx.msg('It\'s already the first page of data.',{dialogIcon:'warn',position:'ct'});
      } else {
        this.initPage(productType,1,9)
      }
    },
    last(){
      if( this.page.pageNo == this.page.totalPage ){
        layx.msg('It\'s the last page of data.',{dialogIcon:'warn',position:'ct'});
      } else {
        this.initPage(productType,this.page.totalPage,9)
      }
    },
    pre(){
      if( this.page.pageNo <= 1 ){
        layx.msg('It\'s already the first page of data.',{dialogIcon:'warn',position:'ct'});
      } else {
        this.initPage(productType,this.page.pageNo - 1 , 9);
      }

    },
    next(){
      if( this.page.pageNo >= this.page.totalPage ){
        layx.msg('It\'s the last page of data.',{dialogIcon:'warn',position:'ct'});
      } else {
        this.initPage(productType,this.page.pageNo + 1, 9);
      }

    }
  }
}
</script>

<style scoped>

</style>