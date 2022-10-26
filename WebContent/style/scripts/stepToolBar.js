/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(type) {
    var stepListJson = "";
    var fzYes = false;
    var fzNo = false;
    var bgYes = false;
    var bgNo = false;
    var blYes = false;
    var blNo = false;
    if (type.substr(1, 1) == "1" || type.substr(2, 1) == "1") {
        fzYes = true;
    }
    if (type.substr(1, 1) == "0" && type.substr(2, 1) == "0") {
        fzNo = true;
    }
    if (type.substr(3, 3).indexOf("1") > -1 || type.substr(7, 1) == "1") {
        bgYes = true;
    }
    if (type.substr(3, 3).indexOf("1") < 0 && type.substr(7, 1) == "0") {
        bgNo = true;
    }
    if (type.substr(6, 1) == "1") {
        blYes = true;
    }
    if (type.substr(6, 1) == "0") {
        blNo = true;
    }
    if (type == "10000000") {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 6, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];
    } else if (fzYes && bgNo && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 6, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];
    } else if (fzYes && bgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "变更基本情况", StepTitle: "变更基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];
    } else if (fzYes && bgNo && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "4"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "5"},
            {StepNum: 5, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "6"},
            {StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];
    } else if (fzYes && bgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "变更基本情况", StepTitle: "变更基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "6"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];
    } else if (fzNo && bgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "变更基本情况", StepTitle: "变更基本情况", StepUrl: "5"},
            {StepNum: 4, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "6"},
            {StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 6, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];

    } else if (fzNo && bgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "变更基本情况", StepTitle: "变更基本情况", StepUrl: "5"},
            {StepNum: 4, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 5, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];

    } else if (fzNo && bgNo && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "6"},
            {StepNum: 4, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "7"},
            {StepNum: 5, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "8"}];

    }
    return stepListJson;
}

