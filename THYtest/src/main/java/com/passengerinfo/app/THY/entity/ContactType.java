package com.passengerinfo.app.THY.entity;

import javax.persistence.*;

@Entity
@Table(name = "contact_type")
public class ContactType {
    @Id
    private Long id;
    private String contactName;


    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}