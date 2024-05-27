<template>
  <div>
    <Header></Header>

    <!--banner-->
    <div class="banner-content">
      <div class="swiper-container banner-lb">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <a href="javascript:;" >
              <img src="@/assets/image/banner.jpg" alt="">
            </a>
          </div>
          <div class="swiper-slide">
            <a href="javascript:;" >
              <img src="@/assets/image/banner.jpg" alt="">
            </a>
          </div>
        </div>
      </div>
      <div class="banner-abs">
        <div class="banner-abs-box">
          <div class="banner-abs-title">Historical Annualized Rate</div>
          <b>{{platInfo.historyAvgRate}}<i>%</i></b>
          <p>Number of Platform Users</p>
          <span>{{platInfo.registerUsers}}<i>users</i></span>
          <p class="banner-abs-border">Cumulative Transaction</p>
          <span>{{ platInfo.sumBidMoney }}<i>CNY</i></span>
        </div>
      </div>
    </div>
    <div class="banner-list">
      <ul>
        <li>
          <img src="@/assets/image/banner-tag1.png" alt="">
          <p>
            <b>Enormous Potentiality</b>
            <span>Millions in registered capital, millions in technology investment</span>
          </p>
        </li>
        <li>
          <img src="@/assets/image/banner-tag2.png" alt="">
          <p>
            <b>Strict Risk Control</b>
            <span>30 risk control processes, 60 credit audits</span>
          </p>
        </li>
        <li>
          <img src="@/assets/image/banner-tag3.png" alt="">
          <p>
            <b>Easy Investment</b>
            <span>Fund safety control, bank secure custody</span>
          </p>
        </li>
      </ul>
    </div>

    <!--product-->
    <div class="content">
      <h2 class="public-title"><span>Beginner Treasure</span></h2>
      <div class="new-user" v-for="product in beginnerTreasure"  :key="product.id">
        <div class="new-user-sm">
          <span>Minimum Investment: {{product.bidMinLimit}} CNY</span>
          <span>Maximum Investment Limit: {{product.bidMaxLimit}} CNY</span>
          <span>Same Day Arrival</span>
        </div>
        <div class="new-user-number">
          <ul>
            <li>
              <p><b>{{product.rate}}</b>%</p>
              <span>Historical Annualized Rate of Return</span>
            </li>
            <li>
              <p><b>{{product.cycle}}</b>days</p>
              <span>Investment Period</span>
            </li>
            <li>
              <p><b>{{ product.leftProductMoney }}</b>CNY</p>
              <span>Remaining Amount Available for Investment</span>
            </li>
          </ul>
          <a href="javascript:void(0)" @click="goLink('/page/product/detail', {productId: product.id})" class="new-user-btn">Invest Now</a>
        </div>
        <span class="new-tag">Exclusive for New Users</span>
      </div>

      <h2 class="public-title"><span>Premier Product</span> <a href="javascript:void(0)" @click="goLink('/page/product/list', {ptype:1})" class="public-title-more">View More Products>></a></h2>
      <ul class="preferred-select clearfix">
        <li v-for="(product, index) in premier" :key="product.id">
          <h3 class="preferred-select-title">
            <span>{{ product.productName }}</span>
            <img src="@/assets/image/1-bg1.jpg" alt="" v-if="index == 0">
            <img src="@/assets/image/1-bg2.jpg" alt="" v-else-if="index == 1">
            <img src="@/assets/image/1-bg3.jpg" alt="" v-else>
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>Historical Annualized Rate of Return</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>Investment Period</span>
              <p><b>{{ product.cycle }}</b>month</p>
            </div>
            <div>
              <span>Remaining Amount Available for Investment</span>
              <p><b>{{ product.leftProductMoney }}</b>CNY</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            Premier plan project, 1-month investment return cycle, suitable for short-term capital turnover.
          </p>
          <a href="javascript:void(0)" @click="goLink('/page/product/detail', {productId: product.id})" class="preferred-select-btn">Invest Now</a>
        </li>

      </ul>

      <h2 class="public-title"><span>Individual Assets</span> <a href="javascript:void(0)" @click="goLink('/page/product/list', {ptype:2})" class="public-title-more">View More Products>></a></h2>
      <ul class="preferred-select clearfix">
        <li v-for="product in individual" :key="product.id">
          <h3 class="preferred-select-title1">Individual Credit Consumption
            <span></span>
          </h3>
          <div class="preferred-select-number">
            <p><b>{{ product.rate }}</b>%</p>
            <span>Historical Annualized Rate of Return</span>
          </div>
          <div class="preferred-select-date">
            <div>
              <span>Investment Period</span>
              <p><b>{{ product.cycle }}</b>month</p>
            </div>
            <div>
              <span>Remaining Amount Available for Investment</span>
              <p><b>{{ product.leftProductMoney }}</b>CNY</p>
            </div>
          </div>
          <p class="preferred-select-txt">
            Featured plan project, 1-month investment return cycle, suitable for short-term capital turnover.
          </p>
          <a href="javascript:void(0)" @click="goLink('/page/product/detail', {productId: product.id})" class="preferred-select-btn">Invest Now</a>
        </li>

      </ul>

    </div>

    <!--Description-->
    <div class="information-box">
      <ul>
        <li>
          <img src="@/assets/image/2-icon1.png" alt="">
          <p>Quality Loans</p>
          <span>Strict risk control, multiple audits</span>

        </li>
        <li>
          <img src="@/assets/image/2-icon2.png" alt="">
          <p>Next Day Interest</p>
          <span>Lightning-fast transactions, who's faster than me</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon3.png" alt="">
          <p>Open All Year</p>
          <span>Millions of users, always by your side</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon4.png" alt="">
          <p>Trusted Service</p>
          <span>Millions of users, always by your side</span>
        </li>
        <li>
          <img src="@/assets/image/2-icon5.png" alt="">
          <p>Technical Guarantee</p>
          <span>Millions invested, focused on R&D</span>
        </li>
      </ul>
    </div>

    <!--footer -->
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "@/components/common/Header"
import Footer from "@/components/common/Footer"
import {doGet} from "@/api/httpRequest";


export default {
  name: "IndexView",
  components:{
    // eslint-disable-next-line vue/no-unused-components
    Header,
    Footer
  },
  data(){
    return {
      platInfo:{historyAvgRate:0.00,sumBidMoney:0.00,registerUsers:0},
      beginnerTreasure: [{
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
        productFullTime: 0,
        productDesc: ""
      }],
      premier: [{
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
        productFullTime: 0,
        productDesc: ""
      }],
      individual: [{
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
        productFullTime: 0,
        productDesc: ""
      }]
    }
  },
  mounted() {


    doGet('/v1/plat/info').then(resp=> {
      if (resp){
        this.platInfo = resp.data.data;
      }
    })

    //get product data
    doGet('/v1/product/index').then(resp=>{
      if(resp){
        this.beginnerTreasure = resp.data.data.beginnerTreasure;
        this.premier = resp.data.data.premier;
        this.individual = resp.data.data.individual;
      }
    })
  },
  methods:{
    goLink(url, params){
      //use router for redirecting webpage
      this.$router.push({
        path: url,
        query: params
      })
    }
  }
}
</script>

<style scoped>

</style>