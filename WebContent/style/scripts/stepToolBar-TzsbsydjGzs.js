/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu() {
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "告知书信息", StepTitle: "告知书信息", StepUrl: "1"},
        {StepNum: 2, StepText: "持证人员信息", StepTitle: "持证人员信息", StepUrl: "2"},
        {StepNum: 3, StepText: "附件上传", StepTitle: "附件上传", StepUrl: "3"}
    ];
    return stepListJson;
}

