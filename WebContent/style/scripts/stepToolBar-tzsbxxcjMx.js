function loadMenu(type) {
    //type=1说明有检验检测能力及仪器设备信息   type=0说明没有检验检测能力及仪器设备信息
    var stepListJson = "";
    if (type == "1") {

        stepListJson = [{
            StepNum: 1,
            StepText: "企业基本信息",
            StepTitle: "企业基本信息",
            StepUrl: "1"
        }, {
            StepNum: 2,
            StepText: "证书信息",
            StepTitle: "证书信息",
            StepUrl: "2"
        }, {
            StepNum: 3,
            StepText: "人员信息",
            StepTitle: "人员信息",
            StepUrl: "3"
        }, {
            StepNum: 4,
            StepText: "设备信息",
            StepTitle: "设备信息",
            StepUrl: "4"
        }
        ];
    } else {
        stepListJson = [{
            StepNum: 1,
            StepText: "企业基本信息",
            StepTitle: "企业基本信息",
            StepUrl: "1"
        }, {
            StepNum: 2,
            StepText: "证书信息",
            StepTitle: "证书信息",
            StepUrl: "2"
        }, {
            StepNum: 3,
            StepText: "人员信息",
            StepTitle: "人员信息",
            StepUrl: "3"
        }, {
            StepNum: 4,
            StepText: "设备信息",
            StepTitle: "设备信息",
            StepUrl: "4"
        }
        ];
    }
    return stepListJson;
}
