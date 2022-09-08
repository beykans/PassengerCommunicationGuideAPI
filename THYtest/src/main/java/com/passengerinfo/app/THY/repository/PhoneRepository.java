package com.passengerinfo.app.THY.repository;


import com.passengerinfo.app.THY.dto.phone.CreatePhoneDto;
import com.passengerinfo.app.THY.dto.phone.UpdatePhoneDto;
import com.passengerinfo.app.THY.entity.ContactType;
import com.passengerinfo.app.THY.entity.Passenger;
import com.passengerinfo.app.THY.entity.Phone;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findByPhoneNumber(Long PhoneNumber);
    Long deleteByPassenger(Passenger passenger);
    Long deleteByPhoneNumber(Long PhoneNumber);
    List<Phone> findAllByPassengerIn(List<Passenger> passenger);



}