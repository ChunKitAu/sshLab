webpackJsonp([1],{L5MO:function(t,e){},a8Lk:function(t,e,s){"use strict";var a=s("2S+2"),i={props:["item","from"],data:function(){return{typeList:["软件外包","跑腿代取","视频剪辑","食堂兼职","办公助理","澳门代购"],loading:!1}},methods:{accept:function(){var t=this;this.loading=!0,Object(a.a)({"task.id":this.item.id}).then(function(e){console.log(e),t.$Message.success("任务接受成功"),t.loading=!1,t.item.number=-(t.item.number+1)})},complete:function(){var t=this;this.loading=!0,Object(a.c)({"task.id":this.item.id}).then(function(e){console.log(e),t.$Message.success("任务已完成"),t.loading=!1,t.item.status=!0})}}},n={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"item"},[s("h2",{staticClass:"title"},[t._v(t._s(t.item.title))]),t._v(" "),s("div",{staticClass:"info"},[s("span",[t._v(t._s(t.item.start_time.split("T")[0])+" - "+t._s(t.item.end_time.split("T")[0]))]),t._v("  |  \n    "),s("span",[t._v(t._s(t.typeList[t.item.type_id]))]),t._v("  |  \n    "),s("span",[t._v(t._s(t.item.phone))]),t._v("  |  \n    "),s("span",[t._v(t._s(t.item.integral)+"积分")]),t._v("  |  \n    "),s("span",[t._v(t._s(t.item.number>0?t.item.number:~t.item.number-1)+"人")])]),t._v(" "),s("div",{staticClass:"article"},[t._v(t._s(t.item.content))]),t._v(" "),"search"==t.from?s("div",{staticClass:"footer"},[t.item.number>0?s("Button",{attrs:{size:"small",loading:t.loading},on:{click:t.accept}},[t._v("接受任务")]):s("span",{staticStyle:{color:"#19be6b"}},[t._v("已接受")])],1):t._e(),t._v(" "),"myTasks"==t.from?s("div",{staticClass:"footer"},[t.item.status?s("span",{staticStyle:{color:"#19be6b"}},[t._v("已完成")]):s("span",{staticStyle:{color:"#ff9900"}},[t._v("进行中")])]):t._e(),t._v(" "),"postTasks"==t.from?s("div",{staticClass:"footer"},[t.item.status?s("span",{staticStyle:{color:"#19be6b"}},[t._v("已完成")]):s("Button",{attrs:{size:"small",loading:t.loading},on:{click:t.complete}},[t._v("完成任务")])],1):t._e()])},staticRenderFns:[]};var o=s("VU/8")(i,n,!1,function(t){s("L5MO")},null,null);e.a=o.exports},iiVp:function(t,e){},m0zx:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("2S+2"),i=s("a8Lk"),n={data:function(){return{content:"",pageSize:999,currentPage:1,loading:!1,length:0,from:"search",list:[]}},components:{Loader:s("RQmB").a,Task:i.a},watch:{$route:"changeRoute"},created:function(){this.changeRoute()},methods:{changeRoute:function(){switch(this.from=this.$route.meta.from,this.from){case"search":break;case"myTasks":this.getUserTasks();break;case"postTasks":this.getPostTasks()}},search:function(){var t=this;this.content?(this.list=[],this.loading=!0,Object(a.o)({pageSize:this.pageSize,currentPage:this.currentPage,"task.title":this.content}).then(function(e){console.log(e),t.list=e.data.data.data,t.loading=!1})):this.$Message.error("搜索内容不能为空")},getUserTasks:function(){var t=this;this.list=[],this.loading=!0,Object(a.l)({currentPage:1,pageSize:999}).then(function(e){console.log(e),e.data.data.total>0&&(t.list=e.data.data.data),t.loading=!1})},getPostTasks:function(){var t=this;this.list=[],this.loading=!0,Object(a.h)({currentPage:1,pageSize:999}).then(function(e){console.log(e),e.data.data.total>0&&(t.list=e.data.data.data),t.loading=!1})}}},o={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"container my-shadow box"},[s("div",{staticClass:"input-group",style:{display:"search"===t.from?"":"none"}},[s("i-input",{staticClass:"input",attrs:{placeholder:"输入任务标题关键字进行查询"},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}}),t._v(" "),s("Button",{attrs:{shape:"circle",icon:"ios-search"},on:{click:t.search}})],1),t._v(" "),s("div",[t._l(t.list,function(e){return s("Task",{key:e.id,attrs:{item:e,from:t.from}})}),t._v(" "),s("Loader",{attrs:{loading:t.loading}}),t._v(" "),0!==t.list.length||t.loading?t._e():s("div",[t._v("空空如也~")])],2)])},staticRenderFns:[]};var c=s("VU/8")(n,o,!1,function(t){s("iiVp")},"data-v-65863380",null).exports;e.default=c}});
//# sourceMappingURL=1.76d38eda369afff22854.js.map