package com.passengerinfo.app.THY.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long phoneNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passengerIdPhone", referencedColumnName = "id")
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Passenger getPassenger() { return passenger; }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ContactType getContactType() { return contactType; }

    public void setContactType(ContactType contactType) { this.contactType = contactType; }
}
