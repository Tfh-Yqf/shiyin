(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-navigation-WoDe-cuoti_list-cuoti_list"],{"0276":function(t,e,n){"use strict";function r(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:2;t+="";while(t.length<e)t="0"+t;return t.slice(-e)}n("c975"),n("fb6a"),n("ac1f"),n("5319"),Object.defineProperty(e,"__esModule",{value:!0}),e.formatDate=o,e.friendlyDate=u;var i={yyyy:function(t){return r(t.year,4)},yy:function(t){return r(t.year)},MM:function(t){return r(t.month)},M:function(t){return t.month},dd:function(t){return r(t.day)},d:function(t){return t.day},hh:function(t){return r(t.hour)},h:function(t){return t.hour},mm:function(t){return r(t.minute)},m:function(t){return t.minute},ss:function(t){return r(t.second)},s:function(t){return t.second},SSS:function(t){return r(t.millisecond,3)},S:function(t){return t.millisecond}};function a(t){if(t instanceof Date)return t;switch(typeof t){case"string":return t.indexOf("T")>-1?new Date(t):new Date(t.replace(/-/g,"/"));default:return new Date(t)}}function o(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"yyyy/MM/dd hh:mm:ss";if(!t&&0!==t)return"";t=a(t);var n={year:t.getFullYear(),month:t.getMonth()+1,day:t.getDate(),hour:t.getHours(),minute:t.getMinutes(),second:t.getSeconds(),millisecond:t.getMilliseconds()},r=/yyyy|yy|MM|M|dd|d|hh|h|mm|m|ss|s|SSS|SS|S/,o=!0,u=e;while(o)o=!1,u=u.replace(r,(function(t){return o=!0,i[t](n)}));return u}function u(t,e){var n=e.locale,r=void 0===n?"zh":n,i=e.threshold,u=void 0===i?[6e4,36e5]:i,s=e.format,c=void 0===s?"yyyy/MM/dd hh:mm:ss":s;if("-"===t)return t;if(!t&&0!==t)return"";var f,l,d={zh:{year:"年",month:"月",day:"天",hour:"小时",minute:"分钟",second:"秒",ago:"前",later:"后",justNow:"刚刚",soon:"马上",template:"{num}{unit}{suffix}"},en:{year:"year",month:"month",day:"day",hour:"hour",minute:"minute",second:"second",ago:"ago",later:"later",justNow:"just now",soon:"soon",template:"{num} {unit} {suffix}"}},h=d[r]||d.zh,v=a(t),p=v.getTime()-Date.now(),y=Math.abs(p);if(y<u[0])return p<0?h.justNow:h.soon;if(y>=u[1])return o(v,c);var g=h.later;p<0&&(g=h.ago,p=-p);var m=Math.floor(p/1e3),x=Math.floor(m/60),w=Math.floor(x/60),b=Math.floor(w/24),_=Math.floor(b/30),S=Math.floor(_/12);switch(!0){case S>0:f=S,l=h.year;break;case _>0:f=_,l=h.month;break;case b>0:f=b,l=h.day;break;case w>0:f=w,l=h.hour;break;case x>0:f=x,l=h.minute;break;default:f=m,l=h.second;break}return"en"===r&&(1===f?f="a":l+="s"),h.template.replace(/{\s*num\s*}/g,f+"").replace(/{\s*unit\s*}/g,l).replace(/{\s*suffix\s*}/g,g)}},"26af":function(t,e,n){"use strict";var r;n.d(e,"b",(function(){return i})),n.d(e,"c",(function(){return a})),n.d(e,"a",(function(){return r}));var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-uni-text",[t._v(t._s(t.dateShow))])},a=[]},"2ed3":function(t,e,n){"use strict";n("a9e3"),n("e25e"),Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var r=n("0276"),i={name:"uniDateformat",props:{date:{type:[Object,String,Number],default:function(){return"-"}},locale:{type:String,default:"zh"},threshold:{type:Array,default:function(){return[0,0]}},format:{type:String,default:"yyyy/MM/dd hh:mm:ss"},refreshRate:{type:[Number,String],default:0}},data:function(){return{refreshMark:0}},computed:{dateShow:function(){return this.refreshMark,(0,r.friendlyDate)(this.date,{locale:this.locale,threshold:this.threshold,format:this.format})}},watch:{refreshRate:{handler:function(){this.setAutoRefresh()},immediate:!0}},methods:{refresh:function(){this.refreshMark++},setAutoRefresh:function(){var t=this;clearInterval(this.refreshInterval),this.refreshRate&&(this.refreshInterval=setInterval((function(){t.refresh()}),parseInt(this.refreshRate)))}}};e.default=i},"5af3":function(t,e,n){"use strict";n.r(e);var r=n("26af"),i=n("fc22");for(var a in i)"default"!==a&&function(t){n.d(e,t,(function(){return i[t]}))}(a);var o,u=n("f0c5"),s=Object(u["a"])(i["default"],r["b"],r["c"],!1,null,"7d20afea",null,!1,r["a"],o);e["default"]=s.exports},a462:function(t,e,n){"use strict";n.r(e);var r=n("ac7b"),i=n("c56c");for(var a in i)"default"!==a&&function(t){n.d(e,t,(function(){return i[t]}))}(a);var o,u=n("f0c5"),s=Object(u["a"])(i["default"],r["b"],r["c"],!1,null,"9b6eb3a6",null,!1,r["a"],o);e["default"]=s.exports},ac7b:function(t,e,n){"use strict";n.d(e,"b",(function(){return i})),n.d(e,"c",(function(){return a})),n.d(e,"a",(function(){return r}));var r={uniDateformat:n("5af3").default},i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-uni-view",{},[t._l(t.data,(function(e,r){return n("v-uni-view",{staticStyle:{width:"95%","background-color":"#ecf1fe","margin-left":"2%","margin-top":"10px"}},[n("v-uni-view",{staticStyle:{"text-align":"center","background-color":"#FFFFFF",color:"#b9b9b9","font-size":"12px",padding:"10px 1px 10px 1px"}},[n("uni-dateformat",{attrs:{date:e.time}})],1),n("v-uni-view",{staticStyle:{padding:"15px 3px 15px 3px",display:"flex","flex-direction":"row","justify-content":"space-around","align-items":"center"},on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.jump_yinfu(r)}}},[n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"column",width:"20%","flex-shrink":"0"}},[n("v-uni-text",{staticStyle:{"font-weight":"600","font-size":"14px"}},[t._v(t._s(e.type)+"训练")]),n("v-uni-text",{staticStyle:{color:"#b6bfdb","font-size":"12px"}},[t._v(t._s(e.diff))])],1),n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row"}},[n("v-uni-text",{staticStyle:{"font-weight":"600","font-size":"12px"}},[t._v("正确个数：")]),n("v-uni-view",{staticStyle:{"background-color":"#759ffa","font-size":"10px",padding:"2px 5px 2px 5px","border-radius":"8rpx"}},[n("v-uni-text",[t._v(t._s(e.right_cnt)+"/"+t._s(e.all_cnt))])],1)],1),n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row"}},[n("v-uni-text",{staticStyle:{"font-weight":"600","font-size":"12px"}},[t._v("正确率：")]),n("v-uni-view",{staticStyle:{"background-color":"#759ffa","font-size":"10px",padding:"2px 5px 2px 5px","border-radius":"8rpx"}},[n("v-uni-text",[t._v(t._s(100*e.right_per)+"%")])],1)],1),n("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row"}},[n("v-uni-text",{staticStyle:{"font-weight":"600","font-size":"12px"}},[t._v("耗时：")]),n("v-uni-view",{staticStyle:{"background-color":"#759ffa","font-size":"10px",padding:"2px 5px 2px 5px","border-radius":"8rpx"}},[n("v-uni-text",[t._v(t._s(e.cost_time)+"秒")])],1)],1)],1)],1)})),n("v-uni-view",{staticStyle:{"text-align":"center","font-size":"12px"}},[t._v("无数据可下拉云同步")])],2)},a=[]},c56c:function(t,e,n){"use strict";n.r(e);var r=n("e24b"),i=n.n(r);for(var a in r)"default"!==a&&function(t){n.d(e,t,(function(){return r[t]}))}(a);e["default"]=i.a},e24b:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var r={data:function(){return{data:[],bofang:-1}},mounted:function(){this.data=uni.getStorageSync("tingyin_cuoti")},onPullDownRefresh:function(){var t=this;uni.showLoading({}),uni.request({url:this.server_url+"/get_tingyin_xunlian?user="+uni.getStorageSync("user"),method:"GET",success:function(e){console.log(e),t.data=e.data,uni.setStorageSync("tingyin_cuoti",e.data)},complete:function(t){uni.hideLoading(),uni.stopPullDownRefresh()}})},methods:{jump_yinfu:function(t){console.log(t),uni.setStorage({data:this.data[t],key:"lianxi_chuancan"}),uni.navigateTo({url:"/pages/navigation/WoDe/cuoti_list/xunlian"})}}};e.default=r},fc22:function(t,e,n){"use strict";n.r(e);var r=n("2ed3"),i=n.n(r);for(var a in r)"default"!==a&&function(t){n.d(e,t,(function(){return r[t]}))}(a);e["default"]=i.a}}]);