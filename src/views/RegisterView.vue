<template>
  <div>
    <Header></Header>
    <div class="login-content">
      <div class="login-flex">
        <div class="login-left">
          <p>With the trust of millions of users&nbsp;&nbsp;&nbsp;&nbsp;<span>{{ historyAvgRate }}%</span> annualized return</p>
          <p>Millions in R&D investment&nbsp;&nbsp;&nbsp;&nbsp;Billions in registered capital platform</p>
        </div>
        <!---->
        <div class="login-box">
          <h3 class="login-title">User Registration</h3>
          <form action="" id="register_Submit">
            <div class="alert-input">
              <input type="text" class="form-border user-num" @blur="checkPhone" v-model="phone" name="mobile"
                     placeholder="Please enter an 11-digit mobile number">
              <div class="err">{{ phoneErr }}</div>
              <p class="prompt_num"></p>
              <input type="password" placeholder="Please enter a 6-20 character password with letters and numbers"
                     class="form-border user-pass" autocomplete name="password" v-model="password"
                     @blur="checkPassword">
              <div class="err">{{ passwordErr }}</div>
              <p class="prompt_pass"></p>
              <div class="form-yzm form-border">
                <input class="yzm-write" type="text" name="" placeholder="Enter SMS verification code" v-model="code" @blur="checkCode">
                <input class="yzm-send" type="button" v-bind:value="yzmText" @click="requestSmsCode" id="yzmBtn">
              </div>
              <div class="err">{{ codeErr }}</div>

              <p class="prompt_yan"></p>
            </div>
            <div class="alert-input-agree">
              <input type="checkbox" v-model="agree">I have read and agree to <a href="javascript:;" target="_blank">"Service Agreement"</a>
            </div>
            <div class="alert-input-btn">
              <input type="button" class="login-submit" @click="requestUserRegister" value="Register">
            </div>
          </form>
          <div class="login-skip">
            Already have an account? <a href="javascript:void(0)" @click="goLink('/page/user/login')">Login</a>
          </div>
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
import md5 from 'js-md5';

export default {
  name: "RegisterView",
  components: {
    // eslint-disable-next-line vue/no-unused-components
    Header,
    // eslint-disable-next-line vue/no-unused-components
    Footer
  },
  data() {
    return {
      historyAvgRate: 0.0,
      phone: '13812345699',
      phoneErr: '',
      password: '111aaa',
      passwordErr: '',
      code: '',
      codeErr: '',
      yzmText: 'Get Code',
      isSend: false,
      agree: false
    }
  },
  mounted() {
    // Request data from the server to update the page
    doGet('/v1/plat/info').then(resp => {
      if (resp) {
        this.historyAvgRate = resp.data.data.historyAvgRate;
      }
    })
  },
  methods: {
    goLink(url, params) {
      // Use router for page navigation, vue object
      this.$router.push({
        path: url,
        query: params
      })
    },
    checkPhone() {
      if (this.phone === '' || this.phone === undefined) {
        this.phoneErr = 'Please enter a mobile number';
      } else if (this.phone.length !== 11) {
        this.phoneErr = 'The mobile number must be 11 digits';
      } else if (!/^1[1-9]\d{9}$/.test(this.phone)) {
        this.phoneErr = 'Incorrect mobile number format';
      } else {
        this.phoneErr = '';
        // Send a request to the server to verify if the mobile number can be registered
        doGet('/v1/user/phone/exists', { phone: this.phone })
            .then(resp => {
              if (resp && resp.data.code === 1000) {
                // The mobile number can be registered
                console.log("The mobile number can be registered");
              } else {
                this.phoneErr = resp.data.msg;
              }
            });
      }
    },
    checkPassword() {
      if (this.password === '' || this.password === undefined) {
        this.passwordErr = 'Please enter a password';
      } else if (this.password.length < 6 || this.password.length > 20) {
        this.passwordErr = 'The password length should be 6-20 characters';
      } else if (!/^[0-9a-zA-Z]+$/.test(this.password)) {
        this.passwordErr = 'The password can only use numbers and letters';
      } else if (!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(this.password)) {
        this.passwordErr = 'The password should be a combination of numbers and letters';
      } else {
        this.passwordErr = '';
      }
    },
    checkCode() {
      if (this.code === '' || this.code === undefined) {
        this.codeErr = 'Verification code is required';
      } else if (this.code.length !== 4) {
        this.codeErr = 'The verification code must be 4 digits';
      } else {
        this.codeErr = '';
      }
    },
    requestSmsCode() {
      // isSend:true, sending verification code, countdown in progress. false can resend verification code

      if (!this.isSend) {
        this.checkPhone();
        if (this.phoneErr === '') {
          this.isSend = true;
          let btn = document.getElementById("yzmBtn");
          btn.style.color = 'red';
          // Handle countdown
          let second = 5; // countdown time, default is 60 seconds
          setInterval(() => {
            if (second <= 1) {
              btn.style.color = '#688EF9';
              this.yzmText = "Get Verification Code";
              this.isSend = false;
            } else {
              second = second - 1;
              this.yzmText = second + " seconds to retry";
            }
          }, 1000);
          // Send request to send verification code to mobile number
          doGet('/v1/sms/code/register', { phone: this.phone }).then(resp => {
            if (resp && (resp.data.code === 1000 || resp.data.code === 1006)) {
              layx.msg('SMS has been sent', { dialogIcon: 'success', position: 'ct' });
            }
          });

        }
      }
    },
    requestUserRegister() {
      this.checkPhone();
      this.checkPassword();
      this.checkCode();
      if (this.phoneErr == '' && this.passwordErr == '' && this.codeErr == '') {
        if (this.agree) {
          let newPassword = md5(this.password);
          doPost('/v1/user/register', {
            phone: this.phone,
            pword: newPassword,
            scode: this.code
          }).then(resp => {
            if (resp && resp.data.code == 1000) {
              this.$router.push({
                path: "/page/user/login"
              })
            }
          })
        } else {
          layx.msg("Please read the registration agreement",{dialogIcon:'warn',position:'ct'});
        }


      }
    }
  }
}
</script>

<style scoped>
.err {
  color: red;
  font-size: 18px;
}
</style>