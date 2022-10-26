/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(type){
	var stepListJson="";
	var scYes=false;	
	if((type.substr(1,1)=="1")){
		scYes=true;
	}
	if(scYes){
	stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "申请许可项目", StepTitle: "申请许可项目",StepUrl: "4" },
	                       { StepNum: 5, StepText: "备注", StepTitle: "备注",StepUrl: "5" },
	                       { StepNum: 6, StepText: "工作情况", StepTitle: "工作情况",StepUrl: "6" },
	                       { StepNum: 7, StepText: "申请单位资源", StepTitle: "申请单位资源",StepUrl: "7"},
	                       { StepNum: 8, StepText: "人员情况", StepTitle: "人员情况",StepUrl: "8"},
	                       { StepNum: 9, StepText: "充装设备状况", StepTitle: "充装设备状况",StepUrl: "9"},
	                       { StepNum: 10, StepText: "仪器设备状况", StepTitle: "仪器设备状况",StepUrl: "10"},
	                      /* { StepNum: 10, StepText: "文件资料", StepTitle: "文件资料",StepUrl: "10"},*/
	                     /*  { StepNum: 11, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "11"},*/
	                      /* { StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "12"},*/
	                       { StepNum: 11, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "11"}];
	}
	if(!scYes){
		stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "基本情况", StepTitle: "基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "申请许可项目", StepTitle: "申请许可项目",StepUrl: "4" },
		                       { StepNum: 5, StepText: "备注", StepTitle: "备注",StepUrl: "5" },
		                       { StepNum: 6, StepText: "工作情况", StepTitle: "工作情况",StepUrl: "6" },
		                       { StepNum: 7, StepText: "申请单位资源", StepTitle: "申请单位资源",StepUrl: "7"},
		                       { StepNum: 8, StepText: "人员情况", StepTitle: "人员情况",StepUrl: "8"},
		                       { StepNum: 9, StepText: "充装设备状况", StepTitle: "充装设备状况",StepUrl: "9"},
		                       { StepNum: 10, StepText: "仪器设备状况", StepTitle: "仪器设备状况",StepUrl: "10"},
		                      /* { StepNum: 10, StepText: "文件资料", StepTitle: "文件资料",StepUrl: "10"},*/
		                       /*{ StepNum: 11, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "11"},*/
		                      /* { StepNum: 11, StepText: "备注", StepTitle: "备注",StepUrl: "12"},*/
		                       { StepNum: 11, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "11"}];
		}
	return stepListJson;
}
