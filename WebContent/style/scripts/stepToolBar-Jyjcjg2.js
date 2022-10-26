function loadMenu(type, sqType) {
    //type=1说明有检验检测能力及仪器设备信息   type=0说明没有检验检测能力及仪器设备信息
    var stepListJson = "";
    if (type == "1") {
        //if(sqType==1){
        stepListJson = [{
            StepNum: 1,
            StepText: "填写说明",
            StepTitle: "填写说明",
            StepUrl: "1"
        }, {
            StepNum: 2,
            StepText: "封面信息",
            StepTitle: "封面信息",
            StepUrl: "2"
        }, {
            StepNum: 3,
            StepText: "概况",
            StepTitle: "概况",
            StepUrl: "3"
        }, {
            StepNum: 4,
            StepText: "申请类型",
            StepTitle: "申请类型",
            StepUrl: "4"
        }, {
            StepNum: 5,
            StepText: "机构资源",
            StepTitle: "机构资源",
            StepUrl: "5"
        }, {
            StepNum: 6,
            StepText: "检测能力及仪器设备",
            StepTitle: "检测能力及仪器设备",
            StepUrl: "11"
        }, {
            StepNum: 7,
            StepText: "签字人信息",
            StepTitle: "签字人信息",
            StepUrl: "7"
        }, {
            StepNum: 8,
            StepText: "组织机构图",
            StepTitle: "组织机构图",
            StepUrl: "10"
        }, {
            StepNum: 9,
            StepText: "检验检测人员",
            StepTitle: "检验检测人员",
            StepUrl: "6"
        }, {StepNum: 10, StepText: "证书领取方式", StepTitle: "证书领取方式", StepUrl: "12"}//,
            /*{
                StepNum : 11,
                StepText : "营业执照基本信息",
                StepTitle : "营业执照基本信息",
                StepUrl : "13"
            } */
        ];
        /*}else{
            stepListJson = [ {
                StepNum : 1,
                StepText : "填写说明",
                StepTitle : "填写说明",
                StepUrl : "1"
            }, {
                StepNum : 2,
                StepText : "封面信息",
                StepTitle : "封面信息",
                StepUrl : "2"
            }, {
                StepNum : 3,
                StepText : "概况",
                StepTitle : "概况",
                StepUrl : "3"
            }, {
                StepNum : 4,
                StepText : "申请类型",
                StepTitle : "申请类型",
                StepUrl : "4"
            }, {
                StepNum : 5,
                StepText : "机构资源",
                StepTitle : "机构资源",
                StepUrl : "5"
            },{
                StepNum : 6,
                StepText : "检测能力及仪器设备",
                StepTitle : "检测能力及仪器设备",
                StepUrl : "11"
            },{
                StepNum : 7,
                StepText : "签字人信息",
                StepTitle : "签字人信息",
                StepUrl : "7"
            },{
                StepNum : 8,
                StepText : "组织机构图",
                StepTitle : "组织机构图",
                StepUrl : "10"
            }, {
                StepNum : 9,
                StepText : "检验检测人员",
                StepTitle : "检验检测人员",
                StepUrl : "6"
            } , { StepNum: 10, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "12"}
            ];
        }*/


    } else {
        //if(sqType==1){
        stepListJson = [{
            StepNum: 1,
            StepText: "填写说明",
            StepTitle: "填写说明",
            StepUrl: "1"
        }, {
            StepNum: 2,
            StepText: "封面信息",
            StepTitle: "封面信息",
            StepUrl: "2"
        }, {
            StepNum: 3,
            StepText: "概况",
            StepTitle: "概况",
            StepUrl: "3"
        }, {
            StepNum: 4,
            StepText: "申请类型",
            StepTitle: "申请类型",
            StepUrl: "4"
        }, {
            StepNum: 5,
            StepText: "机构资源",
            StepTitle: "机构资源",
            StepUrl: "5"
        }, {
            StepNum: 6,
            StepText: "检测能力",
            StepTitle: "检测能力",
            StepUrl: "14"
        }, {
            StepNum: 7,
            StepText: "仪器设备",
            StepTitle: "仪器设备",
            StepUrl: "11"
        }, {
            StepNum: 8,
            StepText: "签字人信息",
            StepTitle: "签字人信息",
            StepUrl: "7"
        }, {
            StepNum: 9,
            StepText: "组织机构图",
            StepTitle: "组织机构图",
            StepUrl: "10"
        }, {
            StepNum: 10,
            StepText: "检验检测人员",
            StepTitle: "检验检测人员",
            StepUrl: "6"
        }, {
            StepNum: 11,
            StepText: "证书领取方式",
            StepTitle: "证书领取方式",
            StepUrl: "12"
        }/*, {
				StepNum : 12,
				StepText : "营业执照基本信息",
				StepTitle : "营业执照基本信息",
				StepUrl : "13"
			} */
        ];
        /*}else{
            stepListJson = [ {
                StepNum : 1,
                StepText : "填写说明",
                StepTitle : "填写说明",
                StepUrl : "1"
            }, {
                StepNum : 2,
                StepText : "封面信息",
                StepTitle : "封面信息",
                StepUrl : "2"
            }, {
                StepNum : 3,
                StepText : "概况",
                StepTitle : "概况",
                StepUrl : "3"
            }, {
                StepNum : 4,
                StepText : "申请类型",
                StepTitle : "申请类型",
                StepUrl : "4"
            }, {
                StepNum : 5,
                StepText : "机构资源",
                StepTitle : "机构资源",
                StepUrl : "5"
            },{
                StepNum : 6,
                StepText : "检测能力",
                StepTitle : "检测能力",
                StepUrl : "8"
            },{
                StepNum : 7,
                StepText : "签字人信息",
                StepTitle : "签字人信息",
                StepUrl : "7"
            }, {
                StepNum : 8,
                StepText : "组织机构图",
                StepTitle : "组织机构图",
                StepUrl : "10"
            }, {
                StepNum : 9,
                StepText : "检验检测人员",
                StepTitle : "检验检测人员",
                StepUrl : "6"
            } , {
                StepNum : 10,
                StepText : "仪器设备",
                StepTitle : "仪器设备",
                StepUrl : "9"
            } , {
                StepNum : 10,
                StepText : "仪器设备",
                StepTitle : "仪器设备",
                StepUrl : "9"
            } , { StepNum: 11, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "12"}
            ];
        }*/

    }
    return stepListJson;
}
