/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu() {
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
        {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
        {StepNum: 3, StepText: "基本情况", StepTitle: "基本情况", StepUrl: "3"},
        {StepNum: 4, StepText: "申报项目", StepTitle: "申报项目", StepUrl: "4"},
        {StepNum: 5, StepText: "产品技术标准", StepTitle: "产品技术标准", StepUrl: "5"},
        {StepNum: 6, StepText: "产品质量情况", StepTitle: "产品质量情况", StepUrl: "6"},
        {StepNum: 7, StepText: "关键生产设备", StepTitle: "关键生产设备", StepUrl: "7"},
        {StepNum: 8, StepText: "关键检测设备", StepTitle: "关键检测设备", StepUrl: "8"}
    ];
    return stepListJson;
}

