package com.yongjie.ZhiJianSbpt.startFlow.model;

public class ApiFlow {
    /**
     * 主键
     */
    private Long id;
    /**
     * 流程ID
     */
    private Long flowId;
    /**
     * UUID 例如749099b4-8d28-4fa7-bcb6-47587d245226
     */
    private String uuid;
    /**
     * 流程名称 例如检验检测机构资质认定许可
     */
    private String flowName;
    /**
     * 说明
     */
    private String remark;
    /**
     * 状态1正常2停用
     */
    private String state;

    /**
     * 反射包全类名
     */
    private String clazz;

    /**
     * 反射调用方法名
     */
    private String method;

    public ApiFlow() {
        super();
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
