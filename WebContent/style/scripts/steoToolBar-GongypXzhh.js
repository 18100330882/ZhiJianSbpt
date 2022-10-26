/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */
function loadMenu(type, sbfs) {
    debugger;
    stepListJson = ""
    if (type == 0 && sbfs == 0) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "申请单<br/>(申请类别)", StepTitle: "申请单(申请类别)", StepUrl: "2"},
            {StepNum: 3, StepText: "申请单<br/>(基本信息)", StepTitle: "申请单(基本信息)", StepUrl: "3"},
            {StepNum: 4, StepText: "申请单<br/>(产品信息)", StepTitle: "申请单(产品信息)", StepUrl: "4"},
            /*{ StepNum: 5, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "7" },*/
            {StepNum: 5, StepText: "申请单<br/>(附件信息)", StepTitle: "申请单(附件信息)", StepUrl: "5"}];
    }
    if (type == 0 && sbfs == 1) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "申请单<br/>(申请类别)", StepTitle: "申请单(申请类别)", StepUrl: "2"},
            {StepNum: 3, StepText: "申请单<br/>(基本信息)", StepTitle: "申请单(基本信息)", StepUrl: "3"},
            {StepNum: 4, StepText: "申请单<br/>(产品信息)", StepTitle: "申请单(产品信息)", StepUrl: "4"},
            /* { StepNum: 5, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "7" },*/
            {StepNum: 5, StepText: "申请单<br/>(附件信息)", StepTitle: "申请单(附件信息)", StepUrl: "5"}

        ];
    } else if (type == 1 && sbfs == 0) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "申请单<br/>(申请类别)", StepTitle: "申请单(申请类别)", StepUrl: "2"},
            {StepNum: 3, StepText: "申请单<br/>(基本信息)", StepTitle: "申请单(基本信息)", StepUrl: "3"},
            {StepNum: 4, StepText: "申请单<br/>(分公司信息)", StepTitle: "申请单(分公司信息)", StepUrl: "6"},
            {StepNum: 5, StepText: "申请单<br/>(产品信息)", StepTitle: "申请单(产品信息)", StepUrl: "4"},
            /* { StepNum: 6, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "7" },*/
            {StepNum: 6, StepText: "申请单<br/>(附件信息)", StepTitle: "申请单(附件信息)", StepUrl: "5"}];
    } else if (type == 1 && sbfs == 1) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "申请单<br/>(申请类别)", StepTitle: "申请单(申请类别)", StepUrl: "2"},
            {StepNum: 3, StepText: "申请单<br/>(基本信息)", StepTitle: "申请单(基本信息)", StepUrl: "3"},
            {StepNum: 4, StepText: "申请单<br/>(分公司信息)", StepTitle: "申请单(分公司信息)", StepUrl: "6"},
            {StepNum: 5, StepText: "申请单<br/>(产品信息)", StepTitle: "申请单(产品信息)", StepUrl: "4"},
            /* { StepNum: 6, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "7" },*/
            {StepNum: 6, StepText: "申请单<br/>(附件信息)", StepTitle: "申请单(附件信息)", StepUrl: "5"}

        ];
    }
    return stepListJson;
}

