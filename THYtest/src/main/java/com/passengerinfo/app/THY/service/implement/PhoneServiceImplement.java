package com.passengerinfo.app.THY.service.implement;

import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.dto.phone.CreatePhoneDto;
import com.passengerinfo.app.THY.dto.phone.FindPhoneDto;
import com.passengerinfo.app.THY.dto.phone.UpdatePhoneDto;
import com.passengerinfo.app.THY.entity.ContactType;
import com.passengerinfo.app.THY.entity.Email;
import com.passengerinfo.app.THY.entity.Passenger;
import com.passengerinfo.app.THY.entity.Phone;
import com.passengerinfo.app.THY.exception.ApiRequestException;
import com.passengerinfo.app.THY.repository.ContactTypeRepository;
import com.passengerinfo.app.THY.repository.PassengerRepository;
import com.passengerinfo.app.THY.repository.PhoneRepository;
import com.passengerinfo.app.THY.service.PhoneService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Optional;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PhoneServiceImplement implements PhoneService {

    private final ModelMapper modelMapper;
    private final PassengerRepository passengerRepository;
    private final PhoneRepository phoneRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public CreatePhoneDto createPhone(Long tcNumber, CreatePhoneDto createPhoneDto, String contactName){
        Phone phone = modelMapper.map(createPhoneDto, Phone.class);
        modelMapper.map(phoneRepository.save(phone), CreatePhoneDto.class);
        Long PhoneId = phone.getId();
        Phone phoneValue = phoneRepository.findById(PhoneId).get();
        Passenger passengerValue = passengerRepository.findByTcNumber(tcNumber);
        ContactType contactValue = contactTypeRepository.findByContactName(contactName);
        if(passengerValue == null) {
            throw new ApiRequestException("Böyle Bir TC Numarası Kaydı Bulunmamaktadır. Ekleme Başarısız");
        }
        if(contactValue == null) {
            throw new ApiRequestException("Geçersiz Bağlantı Tipi. Ekleme Başarısız");
        }
        phoneValue.setPassenger(passengerValue);
        phoneValue.setContactType(contactValue);
        modelMapper.map(Collections.singletonList(phoneRepository.save(phoneValue)), CreatePhoneDto.class);
        return modelMapper.map(phoneRepository.save(phone), CreatePhoneDto.class);
    }

    @Override
    public List<FindPhoneDto> findPhones() {
        List<Phone> phones = phoneRepository.findAll();
        List<FindPhoneDto> phoneDtos = phones.stream().map(phone -> modelMapper.map(phone, FindPhoneDto.class)).collect(Collectors.toList());
        return phoneDtos;
    }

    @Override
    public List<FindPhoneDto> findPhone(Long tcNumber) {
        List<Passenger> passengers = passengerRepository.findAllByTcNumber(tcNumber);
        if(passengers.isEmpty()) {
            throw new ApiRequestException("TC Numarasına Ait Kayıt Bulunmamaktadır. Filitreleme Başarısız");
        }
        List<Phone> phones = phoneRepository.findAllByPassengerIn(passengers);
        List<FindPhoneDto> findPhoneDtos = phones.stream().map(phone -> modelMapper.map(phone, FindPhoneDto.class)).collect(Collectors.toList());
        return findPhoneDtos;
    }

    @Override
    public UpdatePhoneDto updatePhone(Long phoneNumber, UpdatePhoneDto phone, String contactName) {
        Phone resultPhone = phoneRepository.findByPhoneNumber(phoneNumber);
        ContactType resultContact = contactTypeRepository.findByContactName(contactName);
        if(resultPhone == null) {
            throw new ApiRequestException("Böyle Bir Telefon Numarası Bulunmamaktadır. Güncelleme Başarısız");
        }
        resultPhone.setPhoneNumber(phone.getPhoneNumber());
        resultPhone.setContactType(resultContact);
        return modelMapper.map(phoneRepository.save(resultPhone), UpdatePhoneDto.class);
    }

    @Override
    public Boolean deletePhone(Long phoneNumber){
        Phone phone = phoneRepository.findByPhoneNumber(phoneNumber);
        if(phone == null){
            return false;
        }
        phoneRepository.deleteByPhoneNumber(phoneNumber);
        return true;
    }
}
