/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */

function loadMenu(type, sqType){
	   var stepListJson="";
	   var fz=false;//发证
	   var fzNo=false;
	   var yx=false;//延续
	   var yxNo=false;
	   var qz=false;//迁址
	   var qzNo=false;
	   var zx=false;//增项
	   var zxNo=false;
	   var jx=false;//减项
	   var jxNo=false;
	   var mcbg=false;//名称变更
	   var mcbgNo=false;
	   var bl=false;//补领
	   var blNo=false;
	   var qt=false;//其他
	   var qtNo=false;//其他
	   if(type.substr(0, 1) == "1")
	   {
		   fz=true;
	   }
	   if(type.substr(0, 1) == "0")
	   {
		   fzNo=true;
	   }
	   if(type.substr(1, 1) == "1")
	   {
		   yx=true;
	   }
       if(type.substr(1, 1) == "0")
	   {
    	   yxNo=true;
	   }
       if(type.substr(2, 1) == "1")
	   {
    	   qz=true;
	   }
       if(type.substr(2, 1) == "0")
	   {
    	   qzNo=true;
	   }
       if(type.substr(3, 1) == "1")
	   {
    	   zx=true;
	   }
       if(type.substr(3, 1) == "0")
	   {
    	   zxNo=true;
	   }
       if(type.substr(4, 1) == "1")
       {
    	   jx=true;
       }
       if(type.substr(4,1)=="0"){
    	   jxNo=true;
       }
       if(type.substr(5, 1) == "1")
       {
    	   mcbg=true;
       }
       if(type.substr(5, 1) == "0")
       {
    	   mcbgNo=true;
       }
       if(type.substr(6, 1) == "1")
       {
    	   bl=true;
       }
       if(type.substr(6, 1) == "0")
       {
    	   blNo=true;
       }
       if(type.substr(7, 1) == "1")
       {
    	   qt=true;
       }
       if(type.substr(7, 1) == "0")
       {
    	   qtNo=true;
       }
       debugger;
		if ((fz||yx||qz)&&zxNo&&jxNo&&mcbgNo&&blNo&&qtNo)//发证、
		{
		    stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
		                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
		                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
		                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },       
		                       { StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },
		                       { StepNum: 6, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
		                   ];
		    //if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 7, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 7, StepText: "附件", StepTitle: "附件",StepUrl: "12"}
		    	);
		    /*}
		    else{
		    	stepListJson.push({ StepNum: 7, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		    }*/
		}
	 else if(fzNo&&yxNo&&qzNo&&(zx||jx||mcbg)&&blNo&&qtNo)
	 {
	 	stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },                       
	                       { StepNum: 3, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" },	                      
	                       { StepNum: 4, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },	                    
	                       { StepNum: 5, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
	 	//if(sqType == 1){
	    	stepListJson.push(/*{StepNum: 6, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
	    			{ StepNum: 6, StepText: "附件", StepTitle: "附件",StepUrl: "12"}
	    	);
	 	/*}
	 	else{
	 		stepListJson.push({ StepNum: 6, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
	 	}*/
	 }
	else if(fzNo&&yxNo&&qzNo&&zxNo&&jxNo&&mcbgNo&&bl&&qtNo)
	{
		  stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 8, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 9, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },
	                       { StepNum: 11, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
		  //if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 12, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 12, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  /*}else{
			  stepListJson.push({ StepNum: 12, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  }*/
	}
	else  if(fzNo&&(yx||qz||zx||jx||mcbg||qt)&&blNo)
	   {
		  stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },            
	                       { StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" },  
	                       { StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" }, 
	                       { StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
		  //if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 8, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  /*}else{
			  stepListJson.push({ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  }*/
	   }
	
	else if(fzNo&&(yx||qz)&&zxNo&&jxNo&&mcbgNo&&bl&&qtNo)
    	   {
		 stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },      
	                       { StepNum: 5, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },
	                       { StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
		 //if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 8, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		 /*}else{
			 stepListJson.push({ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		 }*/
    	   }
	else  if(fzNo&&yxNo&&qzNo&&(zx||jx||mcbg)&&bl&&qtNo)
    	   {

		 stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" }, 
	                       { StepNum: 4, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },
	                       { StepNum: 6, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
		// if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 7, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 7, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		 /*}else{
			 stepListJson.push({ StepNum: 7, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		 }*/
    }
		
	else if(fzNo&&(yx||qz)&&(zx||jx||mcbg||qt)&&bl)
	{
		  stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },
	                       { StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" },
	                       { StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" }, 
	                       { StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                      ];
		 // if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 9, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  /*}else{
			  stepListJson.push({ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  }*/
	}
	else if(fzNo&&yxNo&&qzNo&&zxNo&&jxNo&&mcbg&&bl&&qt)
	{
		  stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },
	                       { StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" },
	                       { StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" }, 
	                       { StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}
	                       ];
		 // if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 9, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			{ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  /*}else{
			  stepListJson.push({ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  }*/
	}
	else if(fzNo&&yxNo&&qzNo&&zxNo&&jxNo&&mcbgNo&&bl&&qt)
	{
		  stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
	                       { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
	                       { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
	                       { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },
	                       { StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况",StepUrl: "7" },
	                       { StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况",StepUrl: "8" },
	                       { StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" }, 
	                       { StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"},
	                      ];
		  // if(sqType == 1){
		    	stepListJson.push(/*{StepNum: 9, StepText: "营业执照信息", StepTitle: "营业执照信息",StepUrl: "13"},*/
		    			 { StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  /*}else{
			  stepListJson.push({ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "12"});
		  }*/
	}
	return stepListJson;
}

