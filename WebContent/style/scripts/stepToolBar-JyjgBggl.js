function loadMenu(type) {
    var stepListJson = "";
    stepListJson = [{StepNum: 1, StepText: "基本信息", StepTitle: "基本信息", StepUrl: "1"},
        {StepNum: 2, StepText: "申请书", StepTitle: "申请书", StepUrl: "2"},
        /*{ StepNum: 3, StepText: "附件", StepTitle: "附件",StepUrl: "3" },*/
        {StepNum: 3, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "4"}
    ];
    /*if (type=="法人变更") {
        stepListJson = [ {
            StepNum : 1,
            StepText : "封面信息",
            StepTitle : "封面信息",
            StepUrl : "1"
        },{
            StepNum : 2,
            StepText : "法人性质变更",
            StepTitle : "法人性质变更",
            StepUrl : "2"
        }
        ];
    }else if(type=="人员变更"){
        stepListJson = [ {
            StepNum : 1,
            StepText : "封面信息",
            StepTitle : "封面信息",
            StepUrl : "1"
        },{
            StepNum : 2,
            StepText : "人员变更",
            StepTitle : "人员变更",
            StepUrl : "3"
        }
        ];
    }else if(type=="标准变更"){
        stepListJson = [ {
            StepNum : 1,
            StepText : "封面信息",
            StepTitle : "封面信息",
            StepUrl : "1"
        },{
            StepNum : 2,
            StepText : "标准变更",
            StepTitle : "标准变更",
            StepUrl : "8"
        }
        ];
    }else if(type=="名称变更"){
        stepListJson = [ {
            StepNum : 1,
            StepText : "封面信息",
            StepTitle : "封面信息",
            StepUrl : "1"
        },{
            StepNum : 2,
            StepText : "名称变更",
            StepTitle : "名称变更",
            StepUrl : "5"
        }
        ];
    }else if(type=="取消检验检测能力"){
        stepListJson = [ {
            StepNum : 1,
            StepText : "封面信息",
            StepTitle : "封面信息",
            StepUrl : "1"
        },{
            StepNum : 2,
            StepText : "取消检测能力",
            StepTitle : "取消检测能力",
            StepUrl : "9"
        }
        ];
    }*/

    return stepListJson;
}
