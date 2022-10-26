/**
 * new js
 */

function loadMenu(flowId){
	
	var	stepListJson;
	if(flowId == 64) {
		stepListJson = [{ StepNum: 1, StepText: "基本信息", StepTitle: "基本信息",StepUrl: "1" },
	                    { StepNum: 2, StepText: "资格证书", StepTitle: "资格证书",StepUrl: "2" },
	                    { StepNum: 3, StepText: "计量专业项目", StepTitle: "计量专业项目",StepUrl: "3" },
	                    { StepNum: 4, StepText: "申请书", StepTitle: "申请书",StepUrl: "4" },
	                    { StepNum: 5, StepText: "附件", StepTitle: "附件",StepUrl: "5" },
	                    { StepNum: 6, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "6" }];
	} else {
		stepListJson = [{ StepNum: 1, StepText: "基本信息", StepTitle: "基本信息",StepUrl: "1" },
	                    { StepNum: 2, StepText: "申请书上传", StepTitle: "申请书上传",StepUrl: "4" },
	                    { StepNum: 3, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "6" },
	                    { StepNum: 4, StepText: "附件上传", StepTitle: "附件上传",StepUrl: "5" }
	                   ];
	}	
	
	return stepListJson;
}