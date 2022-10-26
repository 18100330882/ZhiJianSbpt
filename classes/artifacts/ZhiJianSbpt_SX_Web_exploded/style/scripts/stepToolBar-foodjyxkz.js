/**
 * 食品经营许可
 */

function loadMenu(){
	var	stepListJson = [ 
	                    { StepNum: 1, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "1" },
	                    { StepNum: 2, StepText: "法定代表人", StepTitle: "法定代表人",StepUrl: "2"},
	                    { StepNum: 3, StepText: "食品安全人员", StepTitle: "食品安全人员",StepUrl: "3"},
	                    { StepNum: 4, StepText: "从业人员", StepTitle: "从业人员",StepUrl: "4"},
	                    { StepNum: 5, StepText: "食品安全设施", StepTitle: "食品安全设施",StepUrl: "5"},
	                    { StepNum: 6, StepText: "附件", StepTitle: "附件",StepUrl: "6"},
	                    ];
	return stepListJson;
}