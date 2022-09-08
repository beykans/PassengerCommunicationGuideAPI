package com.passengerinfo.app.THY.service;

import com.passengerinfo.app.THY.dto.phone.CreatePhoneDto;
import com.passengerinfo.app.THY.dto.phone.FindPhoneDto;
import com.passengerinfo.app.THY.dto.phone.UpdatePhoneDto;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PhoneService {

    CreatePhoneDto createPhone(Long tcNumber ,CreatePhoneDto createPhoneDto, String contactName);
    List<FindPhoneDto> findPhones();
    List<FindPhoneDto> findPhone(Long tcNumber);
    UpdatePhoneDto updatePhone(Long phoneNumber, UpdatePhoneDto phone, String contactName);
    Boolean deletePhone(Long phoneNumber);
}
