<template>
    <!--Header-->
    <div class="public-head">
      <div class="public-head-nav">
        <div class="public-head-left">
          <h1 class="public-head-logo"><a href="javascript:;">
            <img src="@/assets/image/logo.png" alt="">
          </a></h1>
          <ul class="public-head-list">
            <li><a href="javascript:void(0)" @click="goLink('/')">Home Page</a></li>
            <li class="public-head-hover">
              <a href="javascript:void(0);">Invest Now</a>
              <!--Second-->
              <div class="two-title">
                <a href="javascript:void(0);" @click="goLink('/page/product/list', {ptype: 1})">Premier</a>
                <a href="javascript:void(0);" @click="goLink('/page/product/list', {ptype: 2})">Individual</a>
              </div>
            </li>

          </ul>
        </div>
        <div class="public-head-right" v-if = "logined">

          <a href="javascript:void(0);" @click="goLink('/page/user/usercenter', )">center</a>
        </div>
        <div class="public-head-right" v-else>
          <a href="javascript:void(0);" @click="goLink('/page/user/login',)">log in</a>
          <a href="javascript:void(0);" @click="goLink('/page/user/register', )">register</a>
        </div>
      </div>
    </div>
    <!--end-->
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Header",

  data(){
    return {
      logined:false
    }
  },

  mounted() {
      if (window.localStorage.getItem("userinfo")){
        this.logined = true;
      }
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

</style>