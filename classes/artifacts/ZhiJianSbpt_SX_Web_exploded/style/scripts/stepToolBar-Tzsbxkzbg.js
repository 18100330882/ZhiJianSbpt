/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(){
	stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                { StepNum: 2, StepText: "申请书信息", StepTitle: "申请书信息",StepUrl: "2" }                    
	];	
	return stepListJson;
}

