package com.passengerinfo.app.THY.service;

import com.passengerinfo.app.THY.dto.email.CreateEmailDto;
import com.passengerinfo.app.THY.dto.email.FindEmailDto;
import com.passengerinfo.app.THY.dto.email.UpdateEmailDto;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EmailService {

    CreateEmailDto createEmail(Long tcNumber, CreateEmailDto createEmailDto, String contactType);
    List<FindEmailDto> findEmails();
    List<FindEmailDto> findEmail(Long tcNumber);
    UpdateEmailDto updateEmail(String emailAddress, UpdateEmailDto email, String contactName);
    Boolean deleteEmail(String emailAddress);
}
