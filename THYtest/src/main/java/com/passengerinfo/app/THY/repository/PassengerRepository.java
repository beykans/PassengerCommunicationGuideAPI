package com.passengerinfo.app.THY.repository;

import com.passengerinfo.app.THY.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByTcNumber(Long tcNumber);
    Long deleteByTcNumber(Long tcNumber);
    List<Passenger> findAllByTcNumber(Long tcNumber);

}
