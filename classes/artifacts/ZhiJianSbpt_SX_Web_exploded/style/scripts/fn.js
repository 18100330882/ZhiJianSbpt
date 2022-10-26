/*
 *功能： 模拟form表单post的提交跳转到指定url
 *参数： URL 跳转地址 PARAMTERS 参数
 *返回值：void
 *创建时间：20180206
 *创建人： 刘重洋 
 *demo:调用实例 
 *var URL="跳转的url";
 *var parames = new Array();//参数数组
 *parames.push({ name: 参数名1, value: 参数值1});
 *parames.push({ name: 参数名2, value: 参数值2});
 *Post(URL,parames);//跳转
 */
function Post(URL, PARAMTERS) {
	// 创建form表单
	var temp_form = document.createElement("form");
	temp_form.action = URL;
	// 如需打开新窗口，form的target属性要设置为'_blank'
	temp_form.target = "_self";
	temp_form.method = "post";
	temp_form.style.display = "none";
	// 添加参数
	for ( var item in PARAMTERS) {
		var opt = document.createElement("textarea");
		opt.name = PARAMTERS[item].name;
		opt.value = PARAMTERS[item].value;
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);
	// 提交数据
	temp_form.submit();
}

function Post2(PARAMTERS) {
	// 创建form表单
	var temp_form = document.createElement("form");
	// 如需打开新窗口，form的target属性要设置为'_blank'
	temp_form.target = "_self";
	temp_form.method = "post";
	temp_form.style.display = "none";
	// 添加参数
	for ( var item in PARAMTERS) {
		var opt = document.createElement("textarea");
		//var opt;
		opt.id = 'parent';
		opt.name = PARAMTERS[item].name;
		opt.value = PARAMTERS[item].value;
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);
}

function OpenNew(URL, PARAMTERS) {
	var temp_form = document.createElement("form");
	temp_form.action = URL;
	// 如需打开新窗口，form的target属性要设置为'_blank'  在新窗口中打开。
	temp_form.target = "_blank";
	temp_form.method = "post";
	temp_form.style.display = "none";
	// 添加参数
	for ( var item in PARAMTERS) {
		debugger;
		var opt = document.createElement("textarea");
		opt.name = PARAMTERS[item].name;
		opt.value = PARAMTERS[item].value;
		temp_form.appendChild(opt);
	}
	document.body.appendChild(temp_form);
	// 提交数据
	temp_form.submit();
}