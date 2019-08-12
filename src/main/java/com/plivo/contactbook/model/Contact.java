package com.plivo.contactbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts", catalog = "contact_book")
public class Contact {
    @Id
    @GeneratedValue
    @Column(name="contact_id")
    private Integer contactId;

    @Column(name = "name")
    private String name;


    @Column(name = "email_id", unique = true)
    private String emailId;

    public Contact() {
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
