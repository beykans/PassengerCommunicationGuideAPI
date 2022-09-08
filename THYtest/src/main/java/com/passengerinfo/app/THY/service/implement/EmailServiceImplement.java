package com.passengerinfo.app.THY.service.implement;

import com.passengerinfo.app.THY.dto.email.CreateEmailDto;
import com.passengerinfo.app.THY.dto.email.FindEmailDto;
import com.passengerinfo.app.THY.dto.email.UpdateEmailDto;
import com.passengerinfo.app.THY.entity.ContactType;
import com.passengerinfo.app.THY.entity.Email;
import com.passengerinfo.app.THY.entity.Passenger;
import com.passengerinfo.app.THY.exception.ApiRequestException;
import com.passengerinfo.app.THY.repository.ContactTypeRepository;
import com.passengerinfo.app.THY.repository.PassengerRepository;
import com.passengerinfo.app.THY.repository.EmailRepository;
import com.passengerinfo.app.THY.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailServiceImplement implements EmailService {

    private final ModelMapper modelMapper;
    private final PassengerRepository passengerRepository;
    private final EmailRepository emailRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public CreateEmailDto createEmail(Long tcNumber, CreateEmailDto createEmailDto, String contactType) {
        Email email = modelMapper.map(createEmailDto, Email.class);
        if(!isValid(email.getEmailAddress())) {
            throw new ApiRequestException("Geçersiz Email Adresi.");
        }
        modelMapper.map(emailRepository.save(email), CreateEmailDto.class);
        Long EmailId = email.getId();
        Email emailValue = emailRepository.findById(EmailId).get();
        Passenger passengerValue = passengerRepository.findByTcNumber(tcNumber);
        ContactType contactValue = contactTypeRepository.findByContactName(contactType);
        if(passengerValue == null) {
            throw new ApiRequestException("Böyle Bir TC Numarası Kaydı Bulunmamaktadır. Ekleme Başarısız");
        }
        if(contactValue == null) {
            throw new ApiRequestException("Geçersiz Bağlantı Tipi. Ekleme Başarısız");
        }
        emailValue.setPassenger(passengerValue);
        emailValue.setContactType(contactValue);
        modelMapper.map(Collections.singletonList(emailRepository.save(emailValue)), CreateEmailDto.class);
        return modelMapper.map(emailRepository.save(email), CreateEmailDto.class);
    }

    @Override
    public List<FindEmailDto> findEmails() {
        List<Email> emails = emailRepository.findAll();
        List<FindEmailDto> emailDtos = emails.stream().map(email -> modelMapper.map(email, FindEmailDto.class)).collect(Collectors.toList());
        return emailDtos;
    }

    @Override
    public List<FindEmailDto> findEmail(Long tcNumber) {
        List<Passenger> passengers = passengerRepository.findAllByTcNumber(tcNumber);
        if(passengers.isEmpty()) {
            throw new ApiRequestException("TC Numarasına Ait Kayıt Bulunmamaktadır. Filitreleme Başarısız");
        }
        List<Email> emails = emailRepository.findAllByPassengerIn(passengers);
        List<FindEmailDto> findEmailDtos = emails.stream().map(email -> modelMapper.map(email, FindEmailDto.class)).collect(Collectors.toList());
        return findEmailDtos;
    }

    @Override
    public UpdateEmailDto updateEmail(String emailAddress, UpdateEmailDto email, String contactName) {
        Email resultEmail = emailRepository.findByEmailAddress(emailAddress);
        ContactType resultContact = contactTypeRepository.findByContactName(contactName);
        if(resultEmail == null) {
            throw new ApiRequestException("Böyle Bir Email Adresi Bulunmamaktadır. Güncelleme Başarısız");
        }
        resultEmail.setEmailAddress(email.getEmailAddress());
        resultEmail.setContactType(resultContact);
        return modelMapper.map(emailRepository.save(resultEmail), UpdateEmailDto.class);
    }

    @Override
    public Boolean deleteEmail(String emailAddress){
        Email email = emailRepository.findByEmailAddress(emailAddress);
        if(email == null){
            return false;
        }
        emailRepository.deleteByEmailAddress(emailAddress);

        return true;
    }
    public static boolean isValid(String emailAddress)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailAddress).matches();
    }
}
