/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(){
	stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
					{ StepNum: 3, StepText: "考核基本情况", StepTitle:"考核基本情况",StepUrl: "3" },
					{ StepNum: 4, StepText: "承担法定任务", StepTitle: "承担法定任务",StepUrl: "4" },
					{ StepNum: 5, StepText: "检定项目", StepTitle: "检定项目",StepUrl: "5" },
					{ StepNum: 6, StepText: "校准/检测", StepTitle: "校准/检测",StepUrl: "6" },
					{ StepNum: 7, StepText: "商品量检测", StepTitle: "商品量检测",StepUrl: "7" }
	];	
	return stepListJson;
}

