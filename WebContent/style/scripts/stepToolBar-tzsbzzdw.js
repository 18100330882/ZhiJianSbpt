/**
 * new js
 */

function loadMenu(sbfs) {

    debugger;
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
        {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
        {StepNum: 3, StepText: "单位基本信息", StepTitle: "单位基本信息", StepUrl: "3"},
        {StepNum: 4, StepText: "申请许可类别", StepTitle: "申请许可类别", StepUrl: "4"},
        {StepNum: 5, StepText: "备注", StepTitle: "备注", StepUrl: "5"},
        {StepNum: 6, StepText: "工作情况", StepTitle: "工作情况", StepUrl: "6"},
        {StepNum: 7, StepText: "申请单位资源", StepTitle: "申请单位资源", StepUrl: "7"},
        {StepNum: 8, StepText: "作业人员", StepTitle: "作业人员", StepUrl: "8"},
        {StepNum: 9, StepText: "技术人员", StepTitle: "技术人员", StepUrl: "9"},
        {StepNum: 10, StepText: "设计人员", StepTitle: "设计人员", StepUrl: "17"},
        {StepNum: 11, StepText: "生产设备状况", StepTitle: "生产设备状况", StepUrl: "10"},
        {StepNum: 12, StepText: "仪器设备状况", StepTitle: "仪器设备状况", StepUrl: "11"},
        {StepNum: 13, StepText: "分包外协", StepTitle: "分包外协", StepUrl: "12"},
        /*{ StepNum: 13, StepText: "备注", StepTitle: "备注",StepUrl: "13"},*/
        {StepNum: 14, StepText: "提交文件材料", StepTitle: "提交文件材料", StepUrl: "14"},
        /*  { StepNum: 15, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "15"},*/
        {StepNum: 15, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "16"}
    ];
    return stepListJson;
}