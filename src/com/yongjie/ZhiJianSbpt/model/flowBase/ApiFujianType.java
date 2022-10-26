/**
 * @Author: 朱卫士
 * @Createtime: 2020年6月15日 下午10:56:29
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description: 附件
 */
package com.yongjie.ZhiJianSbpt.model.flowBase;

public class ApiFujianType {

    /**
     * 主键
     */
    private Long id;
    /**
     * 流程ID
     */
    private Long flowId;

    /**
     * 名字
     */
    private String name;

    /**
     * flag状态
     */
    private String flag;
    /**
     * statue 0可用 1不可用
     */
    private String statue;

    /**
     * 限制文件类型
     */
    private String fileExtense;

    /**
     * 申请书类型
     */
    private String sqsType;

    /**
     * groupId
     */
    private Integer groupId;

    /**
     * 排序
     */
    private Integer paiXu;

    public ApiFujianType() {
        super();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getFileExtense() {
        return fileExtense;
    }

    public void setFileExtense(String fileExtense) {
        this.fileExtense = fileExtense;
    }

    public String getSqsType() {
        return sqsType;
    }

    public void setSqsType(String sqsType) {
        this.sqsType = sqsType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPaiXu() {
        return paiXu;
    }

    public void setPaiXu(Integer paiXu) {
        this.paiXu = paiXu;
    }
}
