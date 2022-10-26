/**
 * @Author: 朱卫士
 * @Createtime: 2020年10月23日 上午9:44:53
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class ApiQywj {

    private List buslist;
    private JSON jsonDate;

    public JSON getJsonDate() {
        return jsonDate;
    }

    public void setJsonDate(JSON jsonDate) {
        this.jsonDate = jsonDate;
    }

    public List getBuslist() {
        return buslist;
    }

    public void setBuslist(List buslist) {
        this.buslist = buslist;
    }
}
