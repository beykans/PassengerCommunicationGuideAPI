package com.passengerinfo.app.THY.repository;

import com.passengerinfo.app.THY.entity.Email;
import com.passengerinfo.app.THY.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findByEmailAddress(final String emailAddress);
    Long deleteByPassenger(Passenger passenger);
    Email findByPassenger(Passenger passenger);
    Long deleteByEmailAddress(String emailAddress);
    List<Email> findAllByPassengerIn(List<Passenger> passenger);
}
