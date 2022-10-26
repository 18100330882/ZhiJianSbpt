/**
 * new js
 */

function loadMenu(sbfs){
	
	var	stepListJson="" ;
	//if(sbfs==1){
	stepListJson= [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "单位基本信息", StepTitle: "单位基本信息",StepUrl: "3" },
	                       { StepNum: 4, StepText: "申请许可类别", StepTitle: "申请许可类别",StepUrl: "4" },
	                       { StepNum: 5, StepText: "备注", StepTitle: "备注",StepUrl: "5" },
	                       { StepNum: 6, StepText: "施工数量", StepTitle: "近施工数量",StepUrl: "6"},
	                       /*{ StepNum: 7, StepText: "施工(不含管道)", StepTitle: "施工(不含管道)",StepUrl: "7"},*/
	                       { StepNum: 7, StepText: "压力管道情况", StepTitle: "压力管道情况",StepUrl: "8"},
	                       { StepNum: 8, StepText: "单位资源", StepTitle: "单位资源",StepUrl: "9"},
	                       { StepNum: 9, StepText: "作业/技术人员", StepTitle: "作业、技术人员",StepUrl: "10"},
	                       /*{ StepNum: 11, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "11"},*/
	                       { StepNum: 10, StepText: "生产设备状况", StepTitle: "生产设备状况",StepUrl: "12"},
	                       { StepNum: 11, StepText: "主要设备状况", StepTitle: "主要设备状况",StepUrl: "13"},
	                       { StepNum: 12, StepText: "施工与外包", StepTitle: "施工与外包",StepUrl: "14"},
	                       { StepNum: 13, StepText: "文件资料", StepTitle: "文件资料",StepUrl: "15"},
	                       /*{ StepNum: 16, StepText: "列表备注", StepTitle: "列表备注",StepUrl: "16"},*/
	                     /*  { StepNum: 14, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "17"},*/
	                       { StepNum: 14, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "18"}
	                       ];
	/*}else{
		stepListJson= [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
                       { StepNum: 3, StepText: "单位基本信息", StepTitle: "单位基本信息",StepUrl: "3" },
                       { StepNum: 4, StepText: "申请许可类别", StepTitle: "申请许可类别",StepUrl: "4" },
                       { StepNum: 5, StepText: "单位主管意见", StepTitle: "单位主管意见",StepUrl: "5" },
                       { StepNum: 6, StepText: "施工数量", StepTitle: "近施工数量",StepUrl: "6"},
                       { StepNum: 7, StepText: "施工(不含管道)", StepTitle: "施工(不含管道)",StepUrl: "7"},
                       { StepNum: 8, StepText: "压力管道情况", StepTitle: "压力管道情况",StepUrl: "8"},
                       { StepNum: 9, StepText: "单位资源", StepTitle: "单位资源",StepUrl: "9"},
                       { StepNum: 10, StepText: "作业人员", StepTitle: "作业人员",StepUrl: "10"},
                       { StepNum: 11, StepText: "技术人员", StepTitle: "技术人员",StepUrl: "11"},
                       { StepNum: 12, StepText: "生产设备状况", StepTitle: "生产设备状况",StepUrl: "12"},
                       { StepNum: 13, StepText: "主要设备状况", StepTitle: "主要设备状况",StepUrl: "13"},
                       { StepNum: 14, StepText: "施工与外包", StepTitle: "施工与外包",StepUrl: "14"},
                       { StepNum: 15, StepText: "文件资料", StepTitle: "文件资料",StepUrl: "15"},
                       { StepNum: 16, StepText: "列表备注", StepTitle: "列表备注",StepUrl: "16"} ,
                       { StepNum: 17, StepText: "证书领取方式", StepTitle: "证书领取方式",StepUrl: "18"}
                       
                       ];
		
	}*/
	return stepListJson;
}