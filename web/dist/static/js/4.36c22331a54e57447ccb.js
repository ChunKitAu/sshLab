webpackJsonp([4],{Wwbi:function(e,t){},bk6b:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=o("mvHQ"),n=o.n(r),a=o("2S+2"),i={created:function(){var e=localStorage.getItem("LoginForm");e&&(this.LoginForm=JSON.parse(e),this.remeberme=!0)},data:function(){return{LoginForm:{"user.accountName":"","user.password":""},loginLoading:!1,remeberme:!1}},methods:{login:function(e){var t=this;e["user.accountName"]&&e["user.password"]?(this.loginLoading=!0,Object(a.m)(e).then(function(e){if(200!==e.data.code)return t.$Message.error("账号或密码错误"),void(t.loginLoading=!1);t.remeberme&&localStorage.setItem("LoginForm",n()(t.LoginForm)),console.log(e);var o=e.data.data.getIntegral?"登录成功 +1积分":"登录成功";t.$Message.success(o),t.loginLoading=!1,t.$store.commit("setToken",e.data.data.token),t.$store.commit("setId",e.data.data.id),Object(a.k)({"user.id":e.data.data.id}).then(function(e){setTimeout(function(){t.$router.push({name:"home",params:{roleId:e.data.data.roleId}})},0)})})):this.$Message.error("账号和密码都不能为空")},toRegister:function(){this.$router.push({name:"register"})}}},s={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"animated fadeIn",attrs:{id:"id"}},[o("h2",{staticStyle:{"margin-bottom":"30px"}},[e._v("任务发布系统")]),e._v(" "),o("Form",{ref:"LoginForm",staticClass:"form-class",attrs:{model:e.LoginForm,rules:e.rule_LoginForm},nativeOn:{keydown:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login(e.LoginForm)}}},[o("FormItem",[o("span",[e._v("账号")]),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.LoginForm["user.accountName"],expression:"LoginForm['user.accountName']"}],staticClass:"input",attrs:{type:"text"},domProps:{value:e.LoginForm["user.accountName"]},on:{input:function(t){t.target.composing||e.$set(e.LoginForm,"user.accountName",t.target.value)}}})]),e._v(" "),o("FormItem",[o("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[o("span",[e._v("密码")])]),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.LoginForm["user.password"],expression:"LoginForm['user.password']"}],staticClass:"input",attrs:{type:"password"},domProps:{value:e.LoginForm["user.password"]},on:{input:function(t){t.target.composing||e.$set(e.LoginForm,"user.password",t.target.value)}}})]),e._v(" "),o("Checkbox",{model:{value:e.remeberme,callback:function(t){e.remeberme=t},expression:"remeberme"}},[o("span",{staticClass:"rememberKey"},[e._v("记住密码")])]),e._v(" "),o("br"),e._v(" "),o("br"),e._v(" "),o("FormItem",[o("Button",{staticStyle:{background:"#5A8CFF",border:"#5A8CFF"},attrs:{type:"primary",loading:e.loginLoading,long:""},nativeOn:{click:function(t){return t.preventDefault(),e.login(e.LoginForm)}}},[e._v("登录")])],1)],1),e._v(" "),o("div",{staticClass:"registerTips"},[o("div",[e._v("还没有账号?")]),e._v(" "),o("a",{staticStyle:{"margin-left":"5px",color:"#007bff"},on:{click:e.toRegister}},[e._v("点击注册")])])],1)},staticRenderFns:[]};var m=o("VU/8")(i,s,!1,function(e){o("Wwbi")},"data-v-9f241684",null).exports;t.default=m},mvHQ:function(e,t,o){e.exports={default:o("qkKv"),__esModule:!0}},qkKv:function(e,t,o){var r=o("FeBl"),n=r.JSON||(r.JSON={stringify:JSON.stringify});e.exports=function(e){return n.stringify.apply(n,arguments)}}});
//# sourceMappingURL=4.36c22331a54e57447ccb.js.map