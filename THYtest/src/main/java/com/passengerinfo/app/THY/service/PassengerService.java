package com.passengerinfo.app.THY.service;

import com.passengerinfo.app.THY.dto.passenger.CreatePassengerDto;
import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PassengerService {

    CreatePassengerDto createPassenger(CreatePassengerDto createPassengerDto);
    List<FindPassengerDto> findPassenger();
    FindPassengerDto findPassenger(Long tcNumber);
    UpdatePassengerDto updatePassenger(Long tcNumber, UpdatePassengerDto passenger);
    Boolean deletePassenger(Long tcNumber);
}
