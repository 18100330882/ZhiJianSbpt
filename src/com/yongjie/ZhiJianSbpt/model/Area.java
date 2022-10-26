package com.yongjie.ZhiJianSbpt.model;

import java.util.Date;

public class Area {

    private long id;
    private String areaName;
    private String areaNumber;
    private Integer zoomLevel;
    private String abbreviation;
    private String longitude;
    private String latitude;
    private long parentId;
    private String parentPath;
    private int depth;
    private int paiXu;
    private int isEnabled;
    private String caoR;
    private Date caoDate;

    public Area() {
    }

    public Area(long id, String areaName, String areaNumber,
                long parentId, String parentPath, int depth, int paiXu,
                int isEnabled, String caoR, Date caoDate) {
        this.id = id;
        this.areaName = areaName;
        this.areaNumber = areaNumber;
        this.parentId = parentId;
        this.parentPath = parentPath;
        this.depth = depth;
        this.paiXu = paiXu;
        this.isEnabled = isEnabled;
        this.caoR = caoR;
        this.caoDate = caoDate;
    }

    public Area(long id, String areaName, String areaNumber,
                Integer zoomLevel, String abbreviation,
                String longitude, String latitude, long parentId,
                String parentPath, int depth, int paiXu, int isEnabled,
                String caoR, Date caoDate) {
        this.id = id;
        this.areaName = areaName;
        this.areaNumber = areaNumber;
        this.zoomLevel = zoomLevel;
        this.abbreviation = abbreviation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.parentId = parentId;
        this.parentPath = parentPath;
        this.depth = depth;
        this.paiXu = paiXu;
        this.isEnabled = isEnabled;
        this.caoR = caoR;
        this.caoDate = caoDate;
    }

    /**
     * @param id
     * @param areaName
     * @author 黄煜豪
     * @createTime 20160801
     */
    public Area(long id, String areaName) {
        this.id = id;
        this.areaName = areaName;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaNumber() {
        return this.areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public Integer getZoomLevel() {
        return this.zoomLevel;
    }

    public void setZoomLevel(Integer zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getParentId() {
        return this.parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getPaiXu() {
        return this.paiXu;
    }

    public void setPaiXu(int paiXu) {
        this.paiXu = paiXu;
    }

    public int getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(int isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getCaoR() {
        return this.caoR;
    }

    public void setCaoR(String caoR) {
        this.caoR = caoR;
    }

    public Date getCaoDate() {
        return this.caoDate;
    }

    public void setCaoDate(Date caoDate) {
        this.caoDate = caoDate;
    }

}
