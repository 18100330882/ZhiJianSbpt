/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(sbfs) {
    var stepListJson = "";
    // if(sbfs==1){
    stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
        {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
        {StepNum: 3, StepText: "基本情况表", StepTitle: "基本情况表", StepUrl: "3"},
        {StepNum: 4, StepText: "充装项目", StepTitle: "充装项目", StepUrl: "4"},
        {StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见", StepUrl: "5"},
        {StepNum: 6, StepText: "充装人员", StepTitle: "充装人员", StepUrl: "6"},
        {StepNum: 7, StepText: "设备情况", StepTitle: "设备情况", StepUrl: "7"},
        /*{ StepNum: 8, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "8"},*/
        {StepNum: 8, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "9"},
        {StepNum: 9, StepText: "其他附件", StepTitle: "其他附件", StepUrl: "10"}
    ];
    /*}else{
           stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
                           { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
                           { StepNum: 3, StepText: "基本情况表", StepTitle: "基本情况表",StepUrl: "3" },
                           { StepNum: 4, StepText: "充装项目", StepTitle: "充装项目",StepUrl: "4" },
                           { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
                           { StepNum: 6, StepText: "充装人员", StepTitle: "充装人员",StepUrl: "6" },
                           { StepNum: 7, StepText: "设备情况", StepTitle: "设备情况",StepUrl: "7" },
                           { StepNum: 8, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "9"}
                           ];
    }*/
    return stepListJson;
}
