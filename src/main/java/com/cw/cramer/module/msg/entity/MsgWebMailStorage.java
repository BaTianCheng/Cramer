package com.cw.cramer.module.msg.entity;

public class MsgWebMailStorage {
    private Integer id;

    private Integer type;

    private Integer status;

    private Integer tag;

    private Integer owner;

    private Integer mailId;

    private Integer method;

    private String remarks;
    
    private MsgWebMail msgWebMail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public MsgWebMail getMsgWebMail() {
		return msgWebMail;
	}

	public void setMsgWebMail(MsgWebMail msgWebMail) {
		this.msgWebMail = msgWebMail;
	}
    
}