package com.passengerinfo.app.THY.entity;

import javax.persistence.*;

@Entity
@Table(name = "email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passengerIdEmail", referencedColumnName = "id")
    private Passenger passenger;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contactTypeId", referencedColumnName = "id")
    private ContactType contactType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ContactType getContactType() { return contactType; }
    public void setContactType(ContactType contactType) { this.contactType = contactType; }
}
