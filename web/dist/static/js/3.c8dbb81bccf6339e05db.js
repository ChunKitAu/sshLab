webpackJsonp([3], {
    "1rc9": function (t, e) {
    }, a8Lk: function (t, e, i) {
        "use strict";
        var n = i("2S+2"), s = {
            props: ["item"], data: function () {
                return {typeList: ["软件外包", "跑腿代取", "视频剪辑", "食堂兼职", "办公助理", "澳门代购"], loading: !1}
            }, methods: {
                accept: function () {
                    var t = this;
                    this.loading = !0, Object(n.a)({"task.id": this.item.id}).then(function (e) {
                        console.log(e), t.$Message.success("任务接受成功"), t.loading = !1, t.item.status = !0
                    })
                }
            }
        }, a = {
            render: function () {
                var t = this, e = t.$createElement, i = t._self._c || e;
                return i("div", {staticClass: "item"}, [i("h2", {staticClass: "title"}, [t._v(t._s(t.item.title))]), t._v(" "), i("div", {staticClass: "info"}, [i("span", [t._v(t._s(t.item.start_time) + " - " + t._s(t.item.end_time))]), t._v("  |  \n    "), i("span", [t._v(t._s(t.typeList[t.item.type_id]))]), t._v("  |  \n    "), i("span", [t._v(t._s(t.item.area))]), t._v("  |  \n    "), i("span", [t._v(t._s(t.item.integral) + "积分")]), t._v("  |  \n    "), i("span", [t._v(t._s(t.item.number) + "人")])]), t._v(" "), i("div", {staticClass: "article"}, [t._v(t._s(t.item.content))]), t._v(" "), i("div", {staticClass: "footer"}, [t.item.status ? i("span", {staticStyle: {color: "#19be6b"}}, [t._v("已接受")]) : i("Button", {
                    attrs: {
                        size: "small",
                        loading: t.loading
                    }, on: {click: t.accept}
                }, [t._v("接受任务")])], 1)])
            }, staticRenderFns: []
        };
        var c = i("VU/8")(s, a, !1, function (t) {
            i("gui7")
        }, null, null);
        e.a = c.exports
    }, gui7: function (t, e) {
    }, m0zx: function (t, e, i) {
        "use strict";
        Object.defineProperty(e, "__esModule", {value: !0});
        var n = i("2S+2"), s = i("a8Lk"), a = {
            data: function () {
                return {content: "", pageSize: 100, currentPage: 1, loading: !1, length: 0, list: []}
            }, components: {Loader: i("RQmB").a, Task: s.a}, methods: {
                search: function () {
                    var t = this;
                    this.content ? (this.list = [], this.loading = !0, Object(n.g)({
                        pageSize: this.pageSize,
                        currentPage: this.currentPage,
                        "task.title": this.content
                    }).then(function (e) {
                        console.log(e), t.list = e.data.data.data, t.loading = !1
                    })) : this.$Message.error("搜索内容不能为空")
                }
            }
        }, c = {
            render: function () {
                var t = this, e = t.$createElement, i = t._self._c || e;
                return i("div", {staticClass: "container my-shadow box"}, [i("div", {staticClass: "input-group"}, [i("i-input", {
                    staticClass: "input",
                    attrs: {placeholder: "输入任务标题关键字进行查询"},
                    model: {
                        value: t.content, callback: function (e) {
                            t.content = e
                        }, expression: "content"
                    }
                }), t._v(" "), i("Button", {
                    attrs: {shape: "circle", icon: "ios-search"},
                    on: {click: t.search}
                })], 1), t._v(" "), i("div", [t._l(t.list, function (t) {
                    return i("Task", {key: t.id, attrs: {item: t}})
                }), t._v(" "), i("Loader", {attrs: {loading: t.loading}}), t._v(" "), 0 !== t.list.length || t.loading ? t._e() : i("div", [t._v("空空如也~")])], 2)])
            }, staticRenderFns: []
        };
        var o = i("VU/8")(a, c, !1, function (t) {
            i("1rc9")
        }, "data-v-1f2a0451", null).exports;
        e.default = o
    }
});
//# sourceMappingURL=3.c8dbb81bccf6339e05db.js.map