(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-login-pwd"],{"4f86":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i={data:function(){return{phone:"",pwd:""}},mounted:function(){uni.setNavigationBarColor({backgroundColor:"#77f7de"})},methods:{jump_pwd:function(){uni.navigateTo({url:"/pages/login/forget"})},login:function(){var t=this,e=this;e.phone?e.pwd?uni.request({url:this.server_url+"/loginbyPassword?user="+this.phone+"&password="+this.pwd,method:"GET",success:function(e){"登录成功"==e.data.result?(uni.showToast({icon:"success",title:"登录成功"}),uni.setStorage({key:"token",data:e.data.token}),uni.setStorage({key:"user",data:t.phone}),uni.setStorageSync("gender",e.data.gender),"admin"==t.phone&&uni.setStorage({key:"admin",data:!0}),uni.switchTab({url:"../navigation/ShouYe/ShouYe"})):(uni.showToast({icon:"error",position:"bottom",title:e.data.result}),t.pwd="")}}):uni.showToast({title:"请输入密码",icon:"none"}):uni.showToast({title:"请输入手机号",icon:"none"})}}};e.default=i},"57a0":function(t,e,n){"use strict";var i;n.d(e,"b",(function(){return o})),n.d(e,"c",(function(){return a})),n.d(e,"a",(function(){return i}));var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-uni-view",{staticStyle:{width:"100%"}},[n("v-uni-view",{staticStyle:{background:"linear-gradient(to top right,#77f7de,#4ddaf0)",height:"100rpx",width:"100%",display:"flex","flex-direction":"column-reverse"}},[n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row","justify-content":"center","margin-bottom":"5px"}},[n("v-uni-text",{staticStyle:{color:"#fff"}},[t._v("登录")])],1)],1),n("v-uni-view",{staticStyle:{padding:"10px",display:"flex","flex-direction":"column"}},[n("v-uni-input",{staticStyle:{height:"20px"},attrs:{disabled:"true"}}),n("v-uni-text",{staticStyle:{"font-size":"30px",color:"#11b3a6","font-weight":"bold"}},[t._v("密码登录")]),n("v-uni-text",{staticStyle:{"font-size":"14px",color:"#a0d9d5","margin-top":"50rpx","font-weight":"bold"}},[t._v("用户名")]),n("v-uni-input",{staticStyle:{"background-color":"#f9f9f9","border-radius":"10px",height:"40px","margin-top":"5px"},model:{value:t.phone,callback:function(e){t.phone=e},expression:"phone"}}),n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row","align-items":"center"}},[n("v-uni-text",{staticStyle:{"font-size":"14px",color:"#a0d9d5","margin-top":"50rpx","font-weight":"bold"}},[t._v("密码")]),n("v-uni-text",{staticStyle:{"font-size":"12px",color:"#d1d1d1","margin-top":"50rpx"}},[t._v("(密码必须8位数字或字母)")])],1),n("v-uni-input",{staticStyle:{"background-color":"#f9f9f9","border-radius":"10px",height:"40px","margin-top":"5px"},attrs:{password:"true"},model:{value:t.pwd,callback:function(e){t.pwd=e},expression:"pwd"}}),n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row","margin-top":"20px",width:"100%","align-items":"center","justify-content":"space-between"}},[n("v-uni-view",{staticStyle:{background:"linear-gradient(to top right,#4ddaf0,#77f7de)",width:"250rpx",height:"40px",display:"flex","flex-direction":"column","justify-content":"center","border-radius":"20px","text-align":"center"},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.login()}}},[n("v-uni-text",{staticStyle:{color:"#fff"}},[t._v("登录")])],1),n("v-uni-text",{staticStyle:{color:"#7fccc6"},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.jump_pwd()}}},[t._v("忘记密码")])],1)],1)],1)},a=[]},8091:function(t,e,n){"use strict";n.r(e);var i=n("57a0"),o=n("dfb6");for(var a in o)"default"!==a&&function(t){n.d(e,t,(function(){return o[t]}))}(a);var r,u=n("f0c5"),c=Object(u["a"])(o["default"],i["b"],i["c"],!1,null,"77967b63",null,!1,i["a"],r);e["default"]=c.exports},dfb6:function(t,e,n){"use strict";n.r(e);var i=n("4f86"),o=n.n(i);for(var a in i)"default"!==a&&function(t){n.d(e,t,(function(){return i[t]}))}(a);e["default"]=o.a}}]);