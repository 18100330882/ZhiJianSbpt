/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(){
	stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
					{ StepNum: 3, StepText: "计量器具信息", StepTitle:"计量器具信息",StepUrl: "3" },
					{ StepNum: 4, StepText: "产品简要说明", StepTitle: "产品简要说明",StepUrl: "4" },
                    { StepNum: 5, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "5"},
					{ StepNum: 6, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "6"}
					/*{ StepNum: 5, StepText: "受理单位意见", StepTitle: "受理单位意见",StepUrl: "5" }*/ 
	];	
	return stepListJson;
}

