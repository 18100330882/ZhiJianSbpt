/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(djType){
	debugger;
	var stepListJson="";
	if(djType=="0"){
	    stepListJson = [ { StepNum: 1, StepText: "设备使用情况", StepTitle: "设备使用情况",StepUrl: "1" },
	                       { StepNum:2, StepText: "设备基本情况", StepTitle: "设备基本情况",StepUrl: "2" },
	                       { StepNum: 3, StepText: "设备检验情况", StepTitle: "设备检验情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "附件", StepTitle: "附件",StepUrl: "4" }];
	}else if(djType=="1"){
		 stepListJson = [{ StepNum: 1, StepText: "设备使用情况", StepTitle: "设备使用情况",StepUrl: "1" },
	                       { StepNum: 2, StepText: "设备基本情况", StepTitle: "设备基本情况",StepUrl: "2" },
	                       { StepNum: 3, StepText: "附件", StepTitle: "附件",StepUrl: "3" }];
	}else if(djType=="2"){
		 stepListJson = [ { StepNum: 1, StepText: "设备使用情况", StepTitle: "设备使用情况",StepUrl: "1" },
	                       { StepNum: 2, StepText: "设备基本情况", StepTitle: "设备基本情况",StepUrl: "2" },
		 					{ StepNum: 3, StepText: "气瓶基本信息", StepTitle: "气瓶基本信息",StepUrl: "3" },
		 					{ StepNum: 4, StepText: "压力管道信息", StepTitle: "压力管道信息",StepUrl: "4"},
		 					{ StepNum: 5, StepText: "附件", StepTitle: "附件",StepUrl: "5"}];
	}
	return stepListJson;
}

