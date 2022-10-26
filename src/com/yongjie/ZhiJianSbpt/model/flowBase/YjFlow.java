/**
 * @Author: 杨淑娟
 * @Createtime: 2016年8月11日 下午5:57:27
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.model.flowBase;

import java.util.Date;

public class YjFlow {
    private long flowId;
    private String flowName;
    private String version;
    private Long bizId;
    private String caoR;
    private Date caoDate;

    public YjFlow() {

    }

    public YjFlow(long id, long flowId, String flowName, String version,
                  String caoR, Date caoDate) {

        this.flowId = flowId;
        this.flowName = flowName;
        this.version = version;
        this.caoR = caoR;
        this.caoDate = caoDate;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
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

}
