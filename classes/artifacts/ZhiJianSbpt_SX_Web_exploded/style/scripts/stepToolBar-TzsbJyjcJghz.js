/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(jglb,sqType){
	debugger;
	if(jglb=="气瓶检验"){
		var stepListJson="";
		//if(sqType==1){
			stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "申请核准项目", StepTitle: "申请核准项目",StepUrl: "4" },
		                       { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
		                       { StepNum: 6, StepText: "检验检测情况", StepTitle: "检验检测情况",StepUrl: "6" },
		                       { StepNum: 7, StepText: "申请机构资源", StepTitle: "申请机构资源",StepUrl: "7"},
		                       { StepNum: 8, StepText: "持证人员", StepTitle: "持证人员",StepUrl: "8"},
		                       //{ StepNum: 9, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "9"},
		                       { StepNum: 9, StepText: "仪器装备情况", StepTitle: "仪器装备情况",StepUrl: "10"},
		                       //{ StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "11"},
		                       //{ StepNum:11,StepText:"确认证明",StepTitle:"确认证明",StepUrl:"12"},
		                      /* { StepNum: 13, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "13"},*/
		                       { StepNum: 10, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "14"}
		                       ];
		/*}else{
			stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "申请核准项目", StepTitle: "申请核准项目",StepUrl: "4" },
		                       { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
		                       { StepNum: 6, StepText: "检验检测情况", StepTitle: "检验检测情况",StepUrl: "6" },
		                       { StepNum: 7, StepText: "申请机构资源", StepTitle: "申请机构资源",StepUrl: "7"},
		                       { StepNum: 8, StepText: "作业人员", StepTitle: "作业人员",StepUrl: "8"},
		                       { StepNum: 9, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "9"},
		                       { StepNum: 10, StepText: "仪器装备情况", StepTitle: "仪器装备情况",StepUrl: "10"},
		                       { StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "11"},
		                       { StepNum:12,StepText:"确认证明",StepTitle:"确认证明",StepUrl:"12"},
		                       { StepNum: 13, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "14"}
		                       ];
		}*/
	    
	
	    return stepListJson;
	}else{
		var stepListJson="";
		//if(sqType==1){
			stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "申请核准项目", StepTitle: "申请核准项目",StepUrl: "4" },
		                       { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
		                       { StepNum: 6, StepText: "检验检测情况", StepTitle: "检验检测情况",StepUrl: "6" },
		                       { StepNum: 7, StepText: "申请机构资源", StepTitle: "申请机构资源",StepUrl: "7"},
		                       { StepNum: 8, StepText: "持证人员", StepTitle: "持证人员",StepUrl: "8"},
		                       //{ StepNum: 9, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "9"},
		                       { StepNum: 9, StepText: "仪器装备情况", StepTitle: "仪器装备情况",StepUrl: "10"},
		                       //{ StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "11"},
		                       { StepNum:10,StepText:"提交文件资料",StepTitle:"提交文件资料",StepUrl:"13"},
		                      /* { StepNum: 13, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "13"},*/
		                       { StepNum: 11, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "14"}
		                       ];
		/*}else{
			stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "申请核准项目", StepTitle: "申请核准项目",StepUrl: "4" },
		                       { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
		                       { StepNum: 6, StepText: "检验检测情况", StepTitle: "检验检测情况",StepUrl: "6" },
		                       { StepNum: 7, StepText: "申请机构资源", StepTitle: "申请机构资源",StepUrl: "7"},
		                       { StepNum: 8, StepText: "作业人员", StepTitle: "作业人员",StepUrl: "8"},
		                       { StepNum: 9, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "9"},
		                       { StepNum: 10, StepText: "仪器装备情况", StepTitle: "仪器装备情况",StepUrl: "10"},
		                       { StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "11"},
		                       { StepNum:12,StepText:"提交文件资料",StepTitle:"提交文件资料",StepUrl:"12"},
		                       { StepNum: 13, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "14"}
		                       ];
		}*/
	    
	
	    return stepListJson;
	}
}

