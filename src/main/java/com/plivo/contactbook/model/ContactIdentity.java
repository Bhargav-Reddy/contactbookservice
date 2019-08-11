package com.plivo.contactbook.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class ContactIdentity implements Serializable{
    @GeneratedValue
    private Integer contactId;
    private String emailId;

    public ContactIdentity() {
    }

    public ContactIdentity(Integer contactId, String emailId) {
        this.contactId = contactId;
        this.emailId = emailId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
