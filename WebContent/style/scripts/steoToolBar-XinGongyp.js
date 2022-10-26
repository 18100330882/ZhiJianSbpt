/**
 * 根据不同的申请书类型，获取执行步骤的Json
 */
debugger;

function loadMenu(type, sbfs) {
    debugger;
    var stepListJson = "";
    var yxYes = false;
    var yxNo = false;
    var xkfwbgYes = false;
    var xkfwbgNo = false;
    var mcbgYes = false;
    var mcbgNo = false;
    var blYes = false;
    var blNo = false;
    var qt = false;//其他
    var jtgs = false;
    if (type.substr(1, 1) == "1") {
        yxYes = true;
    }
    if (type.substr(1, 1) == "0") {
        yxNo = true;
    }
    if (type.substr(2, 1) == "1") {
        xkfwbgYes = true;
    }
    if (type.substr(2, 1) == "0") {
        xkfwbgNo = true;
    }
    if (type.substr(3, 1) == "1") {
        mcbgYes = true;
    }
    if (type.substr(3, 1) == "0") {
        mcbgNo = true;
    }
    if (type.substr(4, 1) == "1") {
        blYes = true;
    }
    if (type.substr(4, 1) == "0") {
        blNo = true;
    }
    if (type.substr(5, 1) == "1")//其他
    {
        qt = true;
    }
    if (type.substr(6, 1) == "1")//集团公司
    {
        jtgs = true;
    }
    if (type == "1000000" || type == "0000001" || type == "1000001")//发证、集团公司//||type == "0000010"
    {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 6, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[7]={ StepNum: 8, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[7] = {StepNum: 8, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[7]={ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/
    } else if (qt)//只要含有“其他”
    {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 7, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 8, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 9, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 10, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 11, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[11]={ StepNum: 12, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[11] = {StepNum: 12, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[11]={ StepNum: 12, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgYes && mcbgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 7, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 8, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 9, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 10, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 11, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[11]={ StepNum: 12, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[11] = {StepNum: 12, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}
        else if(sbfs==0){
            stepListJson[11]={ StepNum: 12, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgYes && mcbgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 7, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 8, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 9, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 10, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[10]={ StepNum: 11, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[10] = {StepNum: 11, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*} else if(sbfs==0){
            stepListJson[10]={ StepNum: 11, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgYes && mcbgNo && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 7, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 8, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 9, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 10, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[10]={ StepNum: 11, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[10] = {StepNum: 11, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[10]={ StepNum: 11, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgYes && mcbgNo && blNo) {

        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgNo && mcbgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 7, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 8, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 9, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 10, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[10]={ StepNum: 11, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[10] = {StepNum: 11, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[10]={ StepNum: 11, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgNo && mcbgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*	stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgNo && mcbgNo && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
             stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxYes && xkfwbgNo && mcbgNo && blNo) {

        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "延续基本情况", StepTitle: "延续基本情况", StepUrl: "5"},
            {StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 7, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[8]={ StepNum: 9, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[8] = {StepNum: 9, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[8]={ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgYes && mcbgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 6, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 7, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 8, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 9, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 10, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[10]={ StepNum: 11, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[10] = {StepNum: 11, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[10]={ StepNum: 11, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgYes && mcbgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 6, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgYes && mcbgNo && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
                stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
       }*/

    } else if (yxNo && xkfwbgYes && mcbgNo && blNo) {

        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "许可范围变更", StepTitle: "许可范围变更", StepUrl: "6"},
            {StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 7, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[8]={ StepNum: 9, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[8] = {StepNum: 9, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[8]={ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgNo && mcbgYes && blYes) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 6, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 7, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 8, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 9, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[9]={ StepNum: 10, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[9] = {StepNum: 10, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[9]={ StepNum: 10, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgNo && mcbgYes && blNo) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "名称变更情况", StepTitle: "名称变更情况", StepUrl: "7"},
            {StepNum: 6, StepText: "其他情况说明", StepTitle: "其他情况说明", StepUrl: "9"},
            {StepNum: 7, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 8, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[8]={ StepNum: 9, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[8] = {StepNum: 9, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[8]={ StepNum: 9, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgNo && mcbgNo && blYes && qt) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况", StepUrl: "3"},
            {StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况", StepUrl: "4"},
            {StepNum: 5, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 6, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        // if(sbfs==1){
        /*stepListJson[7]={ StepNum: 8, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[7] = {StepNum: 8, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[7]={ StepNum: 8, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/

    } else if (yxNo && xkfwbgNo && mcbgNo && blYes && !qt) {
        stepListJson = [{StepNum: 1, StepText: "填写说明", StepTitle: "填写说明", StepUrl: "1"},
            {StepNum: 2, StepText: "封面信息", StepTitle: "封面信息", StepUrl: "2"},
            {StepNum: 3, StepText: "补领基本情况", StepTitle: "补领基本情况", StepUrl: "8"},
            {StepNum: 4, StepText: "提交核查资料", StepTitle: "提交核查资料", StepUrl: "10"},
            {StepNum: 5, StepText: "提交资料目录", StepTitle: "提交资料目录", StepUrl: "11"}];
        //if(sbfs==1){
        /*stepListJson[5]={ StepNum: 6, StepText: "营业执照基本信息", StepTitle: "营业执照基本信息",StepUrl: "12"};*/
        stepListJson[5] = {StepNum: 6, StepText: "附件", StepTitle: "附件", StepUrl: "13"};
        /*}else if(sbfs==0){
            stepListJson[5]={ StepNum: 6, StepText: "附件", StepTitle: "附件",StepUrl: "13"};
        }*/
    }
    /*	 else if(qt&&yxNo&&xkfwbgNo&&mcbgNo&&blNo)
         {
             stepListJson = [{ StepNum: 1, StepText: "填写说明", StepTitle: "填写说明",StepUrl: "1" },
                               { StepNum: 2, StepText: "封面信息", StepTitle: "封面信息",StepUrl: "2" },
                               { StepNum: 3, StepText: "企业基本情况", StepTitle: "企业基本情况",StepUrl: "3" },
                               { StepNum: 4, StepText: "产品基本情况", StepTitle: "产品基本情况",StepUrl: "4" },
                               { StepNum: 5, StepText: "其他情况说明", StepTitle: "其他情况说明",StepUrl: "9" },
                               { StepNum: 6, StepText: "提交核查资料", StepTitle: "提交核查资料",StepUrl: "10" },
                               { StepNum: 7, StepText: "提交资料目录", StepTitle: "提交资料目录",StepUrl: "11"}];
         }*/
    return stepListJson;
}

