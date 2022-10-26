function loadMenu() {
	// type=1说明有检验检测能力及仪器设备信息 type=0说明没有检验检测能力及仪器设备信息
	var stepListJson = "";
	stepListJson = [ {
		StepNum : 1,
		StepText : "基本情况",
		StepTitle : "基本情况",
		StepUrl : "1"
	}, {
		StepNum : 2,
		StepText : "资源信息",
		StepTitle : "资源信息",
		StepUrl : "2"
	}, {
		StepNum : 3,
		StepText : "证书情况",
		StepTitle : "证书情况",
		StepUrl : "3"
	}, {
		StepNum : 4,
		StepText : "检验检测人员",
		StepTitle : "检验检测人员",
		StepUrl : "4"
	}, {
		StepNum : 5,
		StepText : "授权签字人员",
		StepTitle : "授权签字人员",
		StepUrl : "5"
	}, {
		StepNum : 6,
		StepText : "检验能力信息",
		StepTitle : "检验能力信息",
		StepUrl : "6"
	}, {
		StepNum : 7,
		StepText : "仪器设备信息",
		StepTitle : "仪器设备信息",
		StepUrl : "7"
	}/*, {
		StepNum : 8,
		StepText : "监督检查信息",
		StepTitle : "监督检查信息",
		StepUrl : "8"
	}, {
		StepNum : 9,
		StepText : "多媒体信息",
		StepTitle : "多媒体信息",
		StepUrl : "9"
	}, {
		StepNum : 10,
		StepText : "地理信息",
		StepTitle : "地理信息",
		StepUrl : "10"
	}*/ ];
	return stepListJson;
}
