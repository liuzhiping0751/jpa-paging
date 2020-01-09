$.extend({
    startInit: function (page, pageNum, dataUrl) {
        var defaults = {
            page: page === null ? 1 : page,
            pageNum: pageNum === null ? 3 : pageNum,
            dataUrl: dataUrl === null ? null : dataUrl
        };
        if (defaults.dataUrl === null) {
            return "请求地址错误";
        }
        this.newVue();
        this.getDataList(defaults);
    },
    setCommVar: function (daVm) {
        this._daVm = daVm;
    },
    getCommVar: function () {
        return this._daVm;
    },
    getDataList: function (para) {

        var vm = this.getCommVar();
        var arrSum = null;
        $.ajax({
            type: "GET",
            url: para.dataUrl,//后台接口地址
            dataType: "json",
            success: function (msg) {
                var userBean = msg.userBeanList;

                //var pages = Math.ceil(msg.data / 5);//data是数据总量

                arrSum = ArrSlice(userBean, para.pageNum);
                var pages = arrSum.length;

                vm.listData = arrSum[para.page - 1];

                var element = $('#bp-3-element-test');//对应ul的id
                element.bootstrapPaginator({
                    bootstrapMajorVersion: 3,//bootstrap版本
                    currentPage: para.page,//当前页面
                    numberOfPages: 5,//一页显示几个按钮（在ul里面生成5个li）
                    totalPages: pages, //总页数
                    shouldShowPage: true,
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;
                        }
                    },
                    onPageClicked: function (e, originalEvent, type, page) {
                        vm.listData = arrSum[page - 1];
                    }
                });
            }
        });


        $('#goto_page_demo_pag1').bind('keydown', function (event) {
            var event = window.event || arguments.callee.caller.arguments[0];
            if (event.keyCode == 13) {
                jumpPage($(this).val());
            }
        });

        function ArrSlice(arrData, dataNumber) {
            var result = [];
            for (var i = 0; i < arrData.length; i += dataNumber) {
                result.push(arrData.slice(i, i + dataNumber));
            }
            return result;
        }

        function jumpPage(pageNumber) {
            $('#bp-3-element-test').bootstrapPaginator("show", pageNumber);
            vm.listData = arrSum[pageNumber - 1];
        }
    },
    newVue: function () {
        var vm = new Vue({
            el: '#vue_det',
            data: {
                listData: []
            }
        });
        return this.setCommVar(vm);
    }

});