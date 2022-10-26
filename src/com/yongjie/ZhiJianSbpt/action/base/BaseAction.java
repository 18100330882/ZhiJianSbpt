package com.yongjie.ZhiJianSbpt.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yongjie.ZhiJianSbpt.utilities.HashmapAndEntityTransfer;
import com.yongjie.ZhiJianSbpt.utilities.JSON;
import com.yongjie.ZhiJianSbpt.utilities.SessionStatic;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description: TODO(公共的Action方法, 包含传参的基本参数, json转对象方法)
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected static final long serialVersionUID = 1L;
    protected HttpServletRequest req = ServletActionContext.getRequest();
    protected HttpServletResponse response = ServletActionContext.getResponse();

    /*
     * 这里参数可以灵活使用,只要前台参数和里面参数名称相同,其他Action继承BaseAction
     * 就可以在Action中直接使用传进来的参数
     */
    protected T model; // 当前对象 getModel()
    protected String json; // json || 删除多条数据 || json转对象 || 其他参数
    protected String key;  // 查询条件||其他参数
    protected int pageIndex; // 第几页 || 其他参数
    protected int pageSize; // 每页条数 || 其他参数

    protected String userName = (String) req.getSession().getAttribute(SessionStatic.SYSTEM_SESSION_NAME);

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取当前实体对象
     */
    @Override
    public T getModel() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取泛型父类
        Class clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个参数的真实类型
        try {
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    /**
     * @return T  返回当前对象
     * @Title: getModelValue
     * @Description: TODO(把json 值转换为当前对象值, 时间格式为 2017 - 12 - 12)
     */
    public T getModelValue() {
        if (json != "") {
            ArrayList rows = (ArrayList) new JSON().Decode(json);
            try {
                model = (T) HashmapAndEntityTransfer.hashmapTransferToEntity(model, (HashMap) rows.get(0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return model;
    }

    /**
     * @return T  返回当前对象
     * @Title: getModelValue
     * @Description: TODO(把json 值转换为当前对象值, 时间格式为 2017 - 12 - 12 12 : 12 : 12)
     */
    public T getModelValueDate() {
        if (json != "") {
            ArrayList rows = (ArrayList) new JSON().Decode(json);
            try {
                model = (T) HashmapAndEntityTransfer.hashmapTransferToEntity(model, (HashMap) rows.get(0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return model;
    }
}
