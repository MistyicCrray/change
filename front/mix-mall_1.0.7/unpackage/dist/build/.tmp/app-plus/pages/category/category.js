(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/category/category"],{"209e":function(t,e,n){"use strict";(function(t,a){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=o(n("a34a")),r=o(n("0a18"));function o(t){return t&&t.__esModule?t:{default:t}}function u(t,e,n,a,i,r,o){try{var u=t[r](o),c=u.value}catch(s){return void n(s)}u.done?e(c):Promise.resolve(c).then(a,i)}function c(t){return function(){var e=this,n=arguments;return new Promise(function(a,i){var r=t.apply(e,n);function o(t){u(r,a,i,o,c,"next",t)}function c(t){u(r,a,i,o,c,"throw",t)}o(void 0)})}}var s={data:function(){return{sizeCalcState:!1,tabScrollTop:0,currentId:1,flist:[],slist:[],tlist:[],imageUrl:r.default.imgUrl()}},onLoad:function(){this.loadData()},methods:{loadData:function(){var e=c(i.default.mark(function e(){var n;return i.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:n=this,t.request({url:r.default.getUrl()+"category",method:"GET",success:function(t){console.info(a(JSON.stringify(t)," at pages\\category\\category.vue:47"));var e=t.data.data.rows;e.forEach(function(t){"0"==t.parentid?n.flist.push(t):null==t.img||""==t.img?n.slist.push(t):n.tlist.push(t)})},fail:function(t){console.info(a(t," at pages\\category\\category.vue:60"))}});case 2:case"end":return e.stop()}},e,this)}));function n(){return e.apply(this,arguments)}return n}(),tabtap:function(t){var e=this;e.sizeCalcState||e.calcSize(),e.currentId=t.id;var n=e.slist.findIndex(function(e){return e.parentid===t.id});e.tabScrollTop=e.slist[n].top},asideScroll:function(t){this.sizeCalcState||this.calcSize();var e=t.detail.scrollTop,n=this.slist.filter(function(t){return t.top<=e}).reverse();n.length>0&&(this.currentId=n[0].parentid)},calcSize:function(){var e=0;this.slist.forEach(function(n){var a=t.createSelectorQuery().select("#main-"+n.id);a.fields({size:!0},function(t){n.top=e,e+=t.height,n.bottom=e}).exec()}),this.sizeCalcState=!0},navToList:function(e,n){var a=this;t.navigateTo({url:"/pages/product/list?fid="+a.currentId+"&cateId="+n+"&sid="+e})}}};e.default=s}).call(this,n("6e42")["default"],n("0de9")["default"])},5176:function(t,e,n){"use strict";n.r(e);var a=n("209e"),i=n.n(a);for(var r in a)"default"!==r&&function(t){n.d(e,t,function(){return a[t]})}(r);e["default"]=i.a},6040:function(t,e,n){"use strict";var a=n("ab39"),i=n.n(a);i.a},"6ce4":function(t,e,n){"use strict";n.r(e);var a=n("e2ea"),i=n("5176");for(var r in i)"default"!==r&&function(t){n.d(e,t,function(){return i[t]})}(r);n("6040");var o=n("2877"),u=Object(o["a"])(i["default"],a["a"],a["b"],!1,null,null,null);e["default"]=u.exports},ab39:function(t,e,n){},e2ea:function(t,e,n){"use strict";var a=function(){var t=this,e=t.$createElement;t._self._c},i=[];n.d(e,"a",function(){return a}),n.d(e,"b",function(){return i})}},[["8fd5","common/runtime","common/vendor"]]]);