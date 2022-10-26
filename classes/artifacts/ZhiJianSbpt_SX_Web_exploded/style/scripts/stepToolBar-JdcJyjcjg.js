function loadMenu(type){
	var stepListJson="";
	var bgYes=false;	
	if((type.substr(1,1)=="1")){
		bgYes=true;
	}
	if (!bgYes)
	{
	    stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "人员信息", StepTitle: "人员信息",StepUrl: "4" },
	                       { StepNum: 5, StepText: "签字人信息", StepTitle: "签字人信息",StepUrl: "5" },
	                       { StepNum: 6, StepText: "资质信息", StepTitle: "资质信息",StepUrl: "6"},
	                       { StepNum: 7, StepText: "设备信息", StepTitle: "设备信息",StepUrl: "7"},
	                       { StepNum: 8, StepText: "设备一览表", StepTitle: "设备一览表",StepUrl: "8"},
	                       { StepNum: 9, StepText: "取消检验检测", StepTitle: "取消检验检测",StepUrl: "13"}];
	}

	else if(bgYes)
	{
		stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "人员信息", StepTitle: "人员信息",StepUrl: "4" },
	                       { StepNum: 5, StepText: "签字人信息", StepTitle: "签字人信息",StepUrl: "5" },
	                       { StepNum: 6, StepText: "资质信息", StepTitle: "资质信息",StepUrl: "6"},
	                       { StepNum: 7, StepText: "设备信息", StepTitle: "设备信息",StepUrl: "7"},
	                       { StepNum: 8, StepText: "设备一览表", StepTitle: "设备一览表",StepUrl: "8"},
	                       { StepNum: 9, StepText: "法人变更", StepTitle: "法人变更",StepUrl: "9"},
	                       { StepNum: 10, StepText: "人员变更", StepTitle: "人员变更",StepUrl: "10"},
	                       { StepNum: 11, StepText: "标准变更", StepTitle: "标准变更",StepUrl: "11"},
	                       { StepNum: 12, StepText: "名称变更", StepTitle: "名称变更",StepUrl: "12"},
	                       { StepNum: 13, StepText: "取消检验检测", StepTitle: "取消检验检测",StepUrl: "13"}];
	}
	return stepListJson;
}

