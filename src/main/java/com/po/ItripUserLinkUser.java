package com.po;

import java.util.Date;

public class ItripUserLinkUser {
    private Long id;

    private String linkUserName;

    private String linkIdCard;

    private String linkPhone;

    private Long userId;

    private Date creationDate;

    private Long createdBy;

    private Date modifyDate;

    private Long modifiedBy;

    private Integer linkIdCardType;

    @Override
    public String toString() {
        return "ItripUserLinkUser{" +
                "id=" + id +
                ", linkUserName='" + linkUserName + '\'' +
                ", linkIdCard='" + linkIdCard + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                ", createdBy=" + createdBy +
                ", modifyDate=" + modifyDate +
                ", modifiedBy=" + modifiedBy +
                ", linkIdCardType=" + linkIdCardType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkUserName() {
        return linkUserName;
    }

    public void setLinkUserName(String linkUserName) {
        this.linkUserName = linkUserName;
    }

    public String getLinkIdCard() {
        return linkIdCard;
    }

    public void setLinkIdCard(String linkIdCard) {
        this.linkIdCard = linkIdCard;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getLinkIdCardType() {
        return linkIdCardType;
    }

    public void setLinkIdCardType(Integer linkIdCardType) {
        this.linkIdCardType = linkIdCardType;
    }

    public ItripUserLinkUser(Long id, String linkUserName, String linkIdCard, String linkPhone, Long userId, Date creationDate, Long createdBy, Date modifyDate, Long modifiedBy, Integer linkIdCardType) {

        this.id = id;
        this.linkUserName = linkUserName;
        this.linkIdCard = linkIdCard;
        this.linkPhone = linkPhone;
        this.userId = userId;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.modifyDate = modifyDate;
        this.modifiedBy = modifiedBy;
        this.linkIdCardType = linkIdCardType;
    }

    public ItripUserLinkUser() {

    }
}