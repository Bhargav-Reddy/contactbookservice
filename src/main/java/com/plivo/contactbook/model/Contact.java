package com.plivo.contactbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts", catalog = "contact_book")
public class Contact {
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "email_id")
    private String emailId;

    public Contact() {
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
