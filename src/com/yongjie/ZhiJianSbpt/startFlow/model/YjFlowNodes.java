package com.yongjie.ZhiJianSbpt.startFlow.model;

import java.util.Date;

public class YjFlowNodes {
    private long id; //主键自增,标识种子
    private String nodeId;//是个字符串
    private long flowId;//流程id
    private String nodeMc; //节点名称
    private long nodeWeight;//节点权重
    private float timeLimit;//活动定义时限（几天）
    private int participantType;//参与者类型0：角色 1：部门 2：人员 3：活动
    private String participant;//需要用都好隔开，多选
    private int startOrEnd;//start：1，end：2
    private Integer isHuiQian;//isHuiQian 是否为会签节点
    private Integer isAreaFilter;//0，依据区域过滤；1，不依据区域过滤
    private String xdksrqSql;
    private String caoR;//作者
    private Date caoDate;//更新日期
    private Integer isEnable;//字段是否可用

    public YjFlowNodes() {
        super();
    }

    public YjFlowNodes(long id, String nodeId, long flowId, String nodeMc, long nodeWeight, float timeLimit,
                       int participantType, String participant, int startOrEnd, Integer isHuiQian, Integer isAreaFilter,
                       String xdksrqSql, String caoR, Date caoDate, Integer isEnable) {
        super();
        this.id = id;
        this.nodeId = nodeId;
        this.flowId = flowId;
        this.nodeMc = nodeMc;
        this.nodeWeight = nodeWeight;
        this.timeLimit = timeLimit;
        this.participantType = participantType;
        this.participant = participant;
        this.startOrEnd = startOrEnd;
        this.isHuiQian = isHuiQian;
        this.isAreaFilter = isAreaFilter;
        this.xdksrqSql = xdksrqSql;
        this.caoR = caoR;
        this.caoDate = caoDate;
        this.isEnable = isEnable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 流程id
     */
    public long getFlowId() {
        return flowId;
    }

    /**
     * 流程id
     */
    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getNodeMc() {
        return nodeMc;
    }

    public void setNodeMc(String nodeMc) {
        this.nodeMc = nodeMc;
    }

    public long getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(long nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    public float getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(float timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getParticipantType() {
        return participantType;
    }

    public void setParticipantType(int participantType) {
        this.participantType = participantType;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public int getStartOrEnd() {
        return startOrEnd;
    }

    public void setStartOrEnd(int startOrEnd) {
        this.startOrEnd = startOrEnd;
    }

    public Integer getIsHuiQian() {
        return isHuiQian;
    }

    public void setIsHuiQian(Integer isHuiQian) {
        this.isHuiQian = isHuiQian;
    }

    public Integer getIsAreaFilter() {
        return isAreaFilter;
    }

    public void setIsAreaFilter(Integer isAreaFilter) {
        this.isAreaFilter = isAreaFilter;
    }

    public String getXdksrqSql() {
        return xdksrqSql;
    }

    public void setXdksrqSql(String xdksrqSql) {
        this.xdksrqSql = xdksrqSql;
    }

    public String getCaoR() {
        return caoR;
    }

    public void setCaoR(String caoR) {
        this.caoR = caoR;
    }

    public Date getCaoDate() {
        return caoDate;
    }

    public void setCaoDate(Date caoDate) {
        this.caoDate = caoDate;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

}
