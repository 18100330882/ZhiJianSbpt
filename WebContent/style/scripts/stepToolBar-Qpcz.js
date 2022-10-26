/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu() {
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
        {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
        {StepNum: 3, StepText: "基本情况表(一)", StepTitle: "基本情况表(一)", StepUrl: "3"},
        {StepNum: 4, StepText: "基本情况表(二)", StepTitle: "基本情况表(二)", StepUrl: "4"},
        {StepNum: 5, StepText: "主要人员", StepTitle: "主要人员", StepUrl: "5"},
        {StepNum: 6, StepText: "设备仪器", StepTitle: "设备仪器", StepUrl: "6"},
        {StepNum: 7, StepText: "分包和文件", StepTitle: "分包和文件", StepUrl: "7"}
    ];
    return stepListJson;
}
