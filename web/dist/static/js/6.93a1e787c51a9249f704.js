webpackJsonp([6],{Gh4p:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("2S+2"),i=a("a8Lk"),n=a("RQmB"),o={data:function(){return{typeList:["软件外包","跑腿代取","视频剪辑","食堂兼职","办公助理","澳门代购"],loading:!1,currentPage:1,pageSize:10,total:0,list:[]}},components:{Task:i.a,Loader:n.a},created:function(){this.getTaskByTypeId({"task.type_id":0,currentPage:1,pageSize:10})},methods:{getTaskByTypeId:function(t){var e=this;this.list=[],this.loading=!0,Object(s.j)(t).then(function(t){console.log(t),t.data.data.hasOwnProperty("data")?(e.list=t.data.data.data,e.total=t.data.data.total):(e.list=[],e.total=0),e.loading=!1,console.log(e.list)})},changeTaskType:function(t){1===t.target.nodeType&&this.getTaskByTypeId({"task.type_id":this.typeList.indexOf(t.target.innerHTML),currentPage:1,pageSize:10})}}},c={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"container main"},[a("Content",{staticClass:"left my-shadow",attrs:{padding:"0"}},[a("nav",[a("ul",{on:{click:t.changeTaskType}},t._l(t.typeList,function(e){return a("li",{key:e},[t._v(t._s(e))])}),0)])]),t._v(" "),a("Content",{staticClass:"right my-shadow"},[t._l(t.list,function(t){return a("Task",{key:t,attrs:{item:t,from:"search"}})}),t._v(" "),a("Loader",{attrs:{loading:t.loading}}),t._v(" "),0!==t.total||t.loading?t._e():a("div",{staticStyle:{"margin-top":"50px"}},[t._v("这个分类下暂时没有任务")])],2)],1)},staticRenderFns:[]};var r=a("VU/8")(o,c,!1,function(t){a("ynWw")},"data-v-7b62a3cf",null).exports;e.default=r},a8Lk:function(t,e,a){"use strict";var s=a("2S+2"),i={props:["item","from"],data:function(){return{typeList:["软件外包","跑腿代取","视频剪辑","食堂兼职","办公助理","澳门代购"],loading:!1}},methods:{accept:function(){var t=this;this.loading=!0,Object(s.a)({"task.id":this.item.id}).then(function(e){console.log(e),t.$Message.success("任务接受成功"),t.loading=!1,t.item.number=-(t.item.number+1)})},complete:function(){var t=this;this.loading=!0,Object(s.c)({"task.id":this.item.id}).then(function(e){console.log(e),t.$Message.success("任务已完成"),t.loading=!1,t.item.number=-t.item.number})}}},n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"item"},[a("h2",{staticClass:"title"},[t._v(t._s(t.item.title))]),t._v(" "),a("div",{staticClass:"info"},[a("span",[t._v(t._s(t.item.start_time)+" - "+t._s(t.item.end_time))]),t._v("  |  \n    "),a("span",[t._v(t._s(t.typeList[t.item.type_id]))]),t._v("  |  \n    "),t._v(" "),a("span",[t._v(t._s(t.item.integral)+"积分")]),t._v("  |  \n    "),a("span",[t._v(t._s(t.item.number>0?t.item.number:~t.item.number-1)+"人")])]),t._v(" "),a("div",{staticClass:"article"},[t._v(t._s(t.item.content))]),t._v(" "),"search"==t.from?a("div",{staticClass:"footer"},[t.item.number>0?a("Button",{attrs:{size:"small",loading:t.loading},on:{click:t.accept}},[t._v("接受任务")]):a("span",{staticStyle:{color:"#19be6b"}},[t._v("已接受")])],1):t._e(),t._v(" "),"myTasks"==t.from?a("div",{staticClass:"footer"},[t.item.number>0?a("span",{staticStyle:{color:"#ff9900"}},[t._v("进行中")]):t._e(),t._v(" "),t.item.status?a("span",{staticStyle:{color:"#19be6b"}},[t._v("已完成")]):t._e()]):t._e(),t._v(" "),"postTasks"==t.from?a("div",{staticClass:"footer"},[t.item.number>0?a("Button",{attrs:{size:"small",loading:t.loading},on:{click:t.complete}},[t._v("完成任务")]):a("span",{staticStyle:{color:"#19be6b"}},[t._v("已完成")])],1):t._e()])},staticRenderFns:[]};var o=a("VU/8")(i,n,!1,function(t){a("paOD")},null,null);e.a=o.exports},paOD:function(t,e){},ynWw:function(t,e){}});
//# sourceMappingURL=6.93a1e787c51a9249f704.js.map