/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu() {
    debugger;
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "基本信息", StepTitle: "基本信息", StepUrl: "1"},
        {StepNum: 2, StepText: "申请信息", StepTitle: "申请信息", StepUrl: "2"},
        {StepNum: 3, StepText: "商品目录", StepTitle: "商品目录", StepUrl: "3"},
        {StepNum: 4, StepText: "申请书管理", StepTitle: "申请书管理", StepUrl: "4"},
        {StepNum: 5, StepText: "附件", StepTitle: "附件", StepUrl: "5"},
        {StepNum: 6, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "6"},
        {StepNum: 7, StepText: "营业执照信息", StepTitle: "营业执照信息", StepUrl: "7"}];
    return stepListJson;
}

