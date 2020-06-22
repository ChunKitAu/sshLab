webpackJsonp([10],{Ak7X:function(e,t){},B3Ai:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r("2S+2"),l={data:function(){return{loading:!1,formValidate:{type_id:"",title:"",content:"",img:"",integral:"",number:"",status:!1,phone:null,start_time:"",end_time:""},ruleValidate:{title:[{required:!0,message:"标题不能为空",trigger:"blur"}],type_id:[{required:!0,message:"选择一个任务种类",trigger:"blur"}],number:[{required:!0,message:"任务人数不能为空",trigger:"blur"},{type:"number",transform:function(e){return Number(e)},min:1,message:"任务人数不能小于1",trigger:"blur"}],integral:[{required:!0,message:"积分不能为空",trigger:"blur"},{type:"number",transform:function(e){return Number(e)},min:1,message:"积分不能小于1",trigger:"blur"}],phone:[{required:!0,message:"手机号不能为空",trigger:"blur"},{type:"number",transform:function(e){return Number(e)},min:1e10,message:"手机号格式错误",trigger:"blur"},{type:"number",transform:function(e){return Number(e)},max:99999999999,message:"手机号格式错误",trigger:"blur"}],content:[{required:!0,message:"任务要描述清楚哦",trigger:"blur"},{type:"string",min:10,message:"描述不能少于10个字符",trigger:"blur"}]}}},methods:{handleSubmit:function(e){var t=this;this.$refs[e].validate(function(e){if(t.formValidate.start_time>t.formValidate.end_time)t.$Message.error("任务过期时间不能早于开始时间！");else{var r=new Date;if(t.formValidate.start_time<r&&t.formValidate.end_time<r)t.$Message.error("任务有效期失效！");else if(e){t.loading=!0,console.log(t.formValidate);var l={};for(var i in t.formValidate)t.formValidate.hasOwnProperty(i)&&(l["task."+i]=t.formValidate[i]);Object(a.d)(l).then(function(e){console.log(e),200===e.data.code?(t.$Message.success("已发布"),console.log(e)):t.$Message.error("积分不足"),t.loading=!1})}else t.$Message.error("表单没有填写完整"),t.loading=!1}})},handleReset:function(e){this.$refs[e].resetFields()}}},i={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"container my-shadow box"},[r("h2",[e._v("发布任务")]),e._v(" "),r("Form",{ref:"formValidate",staticClass:"form",attrs:{model:e.formValidate,rules:e.ruleValidate,"label-width":80}},[r("FormItem",{attrs:{label:"标题",prop:"title"}},[r("Input",{attrs:{placeholder:"输入标题"},model:{value:e.formValidate.title,callback:function(t){e.$set(e.formValidate,"title",t)},expression:"formValidate.title"}})],1),e._v(" "),r("FormItem",{attrs:{label:"任务分类",prop:"type_id"}},[r("Select",{attrs:{placeholder:"选择任务分类"},model:{value:e.formValidate.type_id,callback:function(t){e.$set(e.formValidate,"type_id",t)},expression:"formValidate.type_id"}},[r("Option",{attrs:{value:"0"}},[e._v("软件外包")]),e._v(" "),r("Option",{attrs:{value:"1"}},[e._v("跑腿代取")]),e._v(" "),r("Option",{attrs:{value:"2"}},[e._v("视频剪辑")]),e._v(" "),r("Option",{attrs:{value:"3"}},[e._v("食堂兼职")]),e._v(" "),r("Option",{attrs:{value:"4"}},[e._v("办公助理")]),e._v(" "),r("Option",{attrs:{value:"5"}},[e._v("澳门代购")])],1)],1),e._v(" "),r("FormItem",{attrs:{label:"任务人数",prop:"number"}},[r("Input",{attrs:{placeholder:"输入任务人数",type:"number"},model:{value:e.formValidate.number,callback:function(t){e.$set(e.formValidate,"number",t)},expression:"formValidate.number"}})],1),e._v(" "),r("FormItem",{attrs:{label:"有效期"}},[r("Row",[r("Col",{attrs:{span:"11"}},[r("FormItem",{attrs:{prop:"start_time"}},[r("DatePicker",{attrs:{type:"date",placeholder:"选择开始日期"},model:{value:e.formValidate.start_time,callback:function(t){e.$set(e.formValidate,"start_time",t)},expression:"formValidate.start_time"}})],1)],1),e._v(" "),r("Col",{staticStyle:{"text-align":"center"},attrs:{span:"2"}},[e._v("-")]),e._v(" "),r("Col",{attrs:{span:"11"}},[r("FormItem",{attrs:{prop:"end_time"}},[r("DatePicker",{attrs:{type:"date",placeholder:"选择结束日期"},model:{value:e.formValidate.end_time,callback:function(t){e.$set(e.formValidate,"end_time",t)},expression:"formValidate.end_time"}})],1)],1)],1)],1),e._v(" "),r("FormItem",{attrs:{label:"任务积分",prop:"integral"}},[r("Input",{attrs:{placeholder:"输入任务积分",type:"number"},model:{value:e.formValidate.integral,callback:function(t){e.$set(e.formValidate,"integral",t)},expression:"formValidate.integral"}})],1),e._v(" "),r("FormItem",{attrs:{label:"手机号",prop:"phone"}},[r("Input",{attrs:{placeholder:"输入手机号",type:"number"},model:{value:e.formValidate.phone,callback:function(t){e.$set(e.formValidate,"phone",t)},expression:"formValidate.phone"}})],1),e._v(" "),r("FormItem",{attrs:{label:"任务内容",prop:"content"}},[r("Input",{attrs:{type:"textarea",autosize:{minRows:5,maxRows:5},placeholder:"输入任务详细内容"},model:{value:e.formValidate.content,callback:function(t){e.$set(e.formValidate,"content",t)},expression:"formValidate.content"}})],1),e._v(" "),r("FormItem",{staticStyle:{display:"flex","justify-content":"flex-end"}},[r("Button",{attrs:{type:"primary",loading:e.loading},on:{click:function(t){return e.handleSubmit("formValidate")}}},[e._v("发布")]),e._v(" "),r("Button",{staticStyle:{"margin-left":"8px"},on:{click:function(t){return e.handleReset("formValidate")}}},[e._v("重置")])],1)],1)],1)},staticRenderFns:[]};var o=r("VU/8")(l,i,!1,function(e){r("Ak7X")},"data-v-76977e4e",null).exports;t.default=o}});
//# sourceMappingURL=10.90fb7d7bef8388f41e58.js.map