/**
 * 根据不同的申请书类型,,,,,,,,,,,,,,,,,，获取执行步骤的Json
 */

function loadMenu(sbfs) {
    debugger;
    var stepListJson = "";
    //if(sbfs==1){
    stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
        {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
        {StepNum: 3, StepText: "基本情况", StepTitle: "基本情况", StepUrl: "3"},
        {StepNum: 4, StepText: "设计许可类别", StepTitle: "设计许可类别", StepUrl: "4"},
        {StepNum: 5, StepText: "备注", StepTitle: "备注", StepUrl: "5"},
        {StepNum: 6, StepText: "设计数量", StepTitle: "设计数量", StepUrl: "6"},
        {StepNum: 7, StepText: "典型设计产品", StepTitle: "典型设计产品", StepUrl: "7"},
        {StepNum: 8, StepText: "设计人员概况", StepTitle: "设计人员概况", StepUrl: "8"},
        {StepNum: 9, StepText: "设计技术装备", StepTitle: "设计技术装备", StepUrl: "9"},

        /*  { StepNum: 9, StepText: "提交资料", StepTitle: "提交资料",StepUrl: "9"},*/
        //{ StepNum: 10, StepText: "备注", StepTitle: "备注",StepUrl: "10"},
        {StepNum: 10, StepText: "提交文件资料", StepTitle: "提交文件资料", StepUrl: "11"},
        /* { StepNum: 12, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"},*/
        {StepNum: 11, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "13"}
    ];
    /*}else{
        stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
                           { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
                           { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
                           { StepNum: 4, StepText: "设计许可类别", StepTitle: "设计许可类别",StepUrl: "4" },
                           { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
                           { StepNum: 6, StepText: "设计数量", StepTitle: "设计数量",StepUrl: "6" },
                           { StepNum: 7, StepText: "典型设计产品", StepTitle: "典型设计产品",StepUrl: "7"},
                           { StepNum: 8, StepText: "设计人员概况", StepTitle: "设计人员概况",StepUrl: "8"},
                           { StepNum: 9, StepText: "设计技术装备", StepTitle: "设计技术装备",StepUrl: "9"},
                           { StepNum: 9, StepText: "提交资料", StepTitle: "提交资料",StepUrl: "9"},
                           { StepNum: 10, StepText: "备注", StepTitle: "备注",StepUrl: "10"},
                           { StepNum: 11, StepText: "提交文件资料", StepTitle: "提交文件资料",StepUrl: "11"},
                           { StepNum: 12, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "13"}
                           ];
    }*/
    return stepListJson;
}

