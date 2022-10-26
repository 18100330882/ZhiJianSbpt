/**
 * 食品生产许可
 */

function loadMenu(){
	var	stepListJson = [ 
	                    { StepNum: 1, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "1" },
	                    { StepNum: 2, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "2" },
	                    { StepNum: 3, StepText: "专业技术人员", StepTitle: "专业技术人员",StepUrl: "3"},
	                    { StepNum: 4, StepText: "产品信息表", StepTitle: "产品信息表",StepUrl: "4"},
	                    { StepNum: 5, StepText: "加工场所信息", StepTitle: "加工场所信息",StepUrl: "5"},
	                    { StepNum: 6, StepText: "设备设施清单", StepTitle: "设备设施清单",StepUrl: "6"},
	                    { StepNum: 7, StepText: "管理制度清单", StepTitle: "管理制度清单",StepUrl: "7"},
	                    { StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "8"},
	                    ];
	return stepListJson;
}