/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu() {
    debugger;
    var stepListJson = "";

    stepListJson = [{StepNum: 1, StepText: "培训机构信息", StepTitle: "培训机构信息", StepUrl: "1"},
        {StepNum: 2, StepText: "人员基本情况", StepTitle: "人员基本情况", StepUrl: "2"},
        {StepNum: 3, StepText: "附件", StepTitle: "附件", StepUrl: "3"}
    ];

    return stepListJson;
}

