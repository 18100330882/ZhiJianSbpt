function loadMenu(type) {
	//type=1说明有检验检测能力及仪器设备信息   type=0说明没有检验检测能力及仪器设备信息
	var stepListJson = "";
	if(type=="1"){
		/*stepListJson = [  {
			StepNum : 1,
			StepText : "概况",
			StepTitle : "概况",
			StepUrl : "1"
		},  {
			StepNum : 2,
			StepText : "机构资源",
			StepTitle : "机构资源",
			StepUrl : "2"
		},{
			StepNum : 3,
			StepText : "证书信息",
			StepTitle : "证书信息",
			StepUrl : "3"
		},{
			StepNum : 4,
			StepText : "检测能力及仪器设备",
			StepTitle : "检测能力及仪器设备",
			StepUrl : "4"
		},{
			StepNum : 5,
			StepText : "签字人信息",
			StepTitle : "签字人信息",
			StepUrl : "5"
		}, {
			StepNum : 6,
			StepText : "检验检测人员",
			StepTitle : "检验检测人员",
			StepUrl : "6"
		} 
		];
		*/
		stepListJson = [ {
			StepNum : 1,
			StepText : "概况",
			StepTitle : "概况",
			StepUrl : "1"
		},  {
			StepNum : 2,
			StepText : "机构资源",
			StepTitle : "机构资源",
			StepUrl : "2"
		},{
			StepNum : 3,
			StepText : "证书信息",
			StepTitle : "证书信息",
			StepUrl : "3"
		},{
			StepNum : 4,
			StepText : "检测能力",
			StepTitle : "检测能力",
			StepUrl : "7"
		},{
			StepNum : 5,
			StepText : "仪器设备",
			StepTitle : "仪器设备",
			StepUrl : "4"
		},{
			StepNum : 6,
			StepText : "签字人信息",
			StepTitle : "签字人信息",
			StepUrl : "5"
		}, {
			StepNum : 7,
			StepText : "检验检测人员",
			StepTitle : "检验检测人员",
			StepUrl : "6"
		} 
		];
	}else{
		stepListJson = [ {
			StepNum : 1,
			StepText : "概况",
			StepTitle : "概况",
			StepUrl : "1"
		},  {
			StepNum : 2,
			StepText : "机构资源",
			StepTitle : "机构资源",
			StepUrl : "2"
		},{
			StepNum : 3,
			StepText : "证书信息",
			StepTitle : "证书信息",
			StepUrl : "3"
		},{
			StepNum : 4,
			StepText : "检测能力",
			StepTitle : "检测能力",
			StepUrl : "7"
		},{
			StepNum : 5,
			StepText : "仪器设备",
			StepTitle : "仪器设备",
			StepUrl : "4"
		},{
			StepNum : 6,
			StepText : "签字人信息",
			StepTitle : "签字人信息",
			StepUrl : "5"
		}, {
			StepNum : 7,
			StepText : "检验检测人员",
			StepTitle : "检验检测人员",
			StepUrl : "6"
		} 
		];
	}
	return stepListJson;
}
