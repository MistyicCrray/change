(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/address/address"],{3536:function(e,t,n){},4870:function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement;e._self._c},s=[];n.d(t,"a",function(){return a}),n.d(t,"b",function(){return s})},"59db":function(e,t,n){"use strict";n.r(t);var a=n("98ec"),s=n.n(a);for(var r in a)"default"!==r&&function(e){n.d(t,e,function(){return a[e]})}(r);t["default"]=s.a},"98ec":function(e,t,n){"use strict";(function(e,n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a={data:function(){return{source:0,addressList:[]}},onLoad:function(e){this.source=e.source},methods:{checkAddress:function(t){1==this.source&&(this.$api.prePage().addressData=t,e.navigateBack())},addAddress:function(t,n){e.navigateTo({url:"/pages/address/addressManage?type=".concat(t,"&data=").concat(JSON.stringify(n))})},refreshList:function(e,t){this.addressList.unshift(e),console.log(n(e,t," at pages\\address\\address.vue:54"))}}};t.default=a}).call(this,n("6e42")["default"],n("0de9")["default"])},ac7e:function(e,t,n){"use strict";n.r(t);var a=n("4870"),s=n("59db");for(var r in s)"default"!==r&&function(e){n.d(t,e,function(){return s[e]})}(r);n("ec6b");var u=n("2877"),c=Object(u["a"])(s["default"],a["a"],a["b"],!1,null,null,null);t["default"]=c.exports},ec6b:function(e,t,n){"use strict";var a=n("3536"),s=n.n(a);s.a}},[["7d3b","common/runtime","common/vendor"]]]);