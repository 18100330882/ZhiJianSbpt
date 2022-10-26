/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(bglx2) {
    var stepListJson = "";
    if (bglx2 == "名称变更") {
        stepListJson = [{StepNum: 1, StepText: "名称变更", StepTitle: "名称变更", StepUrl: "1"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "标准变更") {
        stepListJson = [{StepNum: 1, StepText: "标准变更", StepTitle: "标准变更", StepUrl: "3"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "法人变更") {
        stepListJson = [{StepNum: 1, StepText: "法人变更", StepTitle: "法人变更", StepUrl: "4"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "地址变更") {
        stepListJson = [{StepNum: 1, StepText: "地址变更", StepTitle: "地址变更", StepUrl: "5"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "人员变更") {
        stepListJson = [{StepNum: 1, StepText: "人员变更", StepTitle: "人员变更", StepUrl: "6"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "授权签字人变更") {
        stepListJson = [{StepNum: 1, StepText: "授权签字人变更", StepTitle: "授权签字人变更", StepUrl: "7"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    } else if (bglx2 == "取消检验检测能力") {
        stepListJson = [{StepNum: 1, StepText: "取消检验能力", StepTitle: "取消检验能力", StepUrl: "8"},
            {StepNum: 2, StepText: "附件", StepTitle: "附件", StepUrl: "2"}
        ];
    }
    return stepListJson;
}

