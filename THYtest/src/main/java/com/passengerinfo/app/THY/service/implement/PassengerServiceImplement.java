package com.passengerinfo.app.THY.service.implement;

import com.passengerinfo.app.THY.dto.passenger.CreatePassengerDto;
import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.entity.Email;
import com.passengerinfo.app.THY.entity.Passenger;
import com.passengerinfo.app.THY.exception.ApiRequestException;
import com.passengerinfo.app.THY.repository.ContactTypeRepository;
import com.passengerinfo.app.THY.repository.EmailRepository;
import com.passengerinfo.app.THY.repository.PassengerRepository;
import com.passengerinfo.app.THY.repository.PhoneRepository;
import com.passengerinfo.app.THY.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerServiceImplement implements PassengerService {

    private final ModelMapper modelMapper;
    private final PassengerRepository passengerRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;

    @Override
    public CreatePassengerDto createPassenger(CreatePassengerDto createPassengerDto){
        Passenger passenger = modelMapper.map(createPassengerDto, Passenger.class);
        if(passenger.getTcNumber() < 10000000000L | passenger.getTcNumber() > 99999999999L ) {
            throw new ApiRequestException("TC Numarası 11 Haneli Olmalıdır.");
        }
        return modelMapper.map(passengerRepository.save(passenger), CreatePassengerDto.class);
    }

    @Override
    public List<FindPassengerDto> findPassenger() {
        List<Passenger> passengers = passengerRepository.findAll();
        List<FindPassengerDto> findPassengerDtos = passengers.stream().map(passenger -> modelMapper.map(passenger, FindPassengerDto.class)).collect(Collectors.toList());
        return findPassengerDtos;
    }

    @Override
    public FindPassengerDto findPassenger(Long tcNumber) {
        Passenger passenger = passengerRepository.findByTcNumber(tcNumber);
        if(passenger == null) {
            throw new ApiRequestException("TC Numarasına Ait Kayıt Bulunmamaktadır. Gösterim Başarısız");
        }
        FindPassengerDto findPassengerDtos = modelMapper.map(passenger, FindPassengerDto.class);
        return findPassengerDtos;
    }

    @Override
    public UpdatePassengerDto updatePassenger(Long tcNumber, UpdatePassengerDto passenger) {
        Passenger resultPassenger = passengerRepository.findByTcNumber(tcNumber);
        if(resultPassenger == null) {
            throw new ApiRequestException("TC Numarasına Ait Kayıt Bulunmamaktadır. Güncelleme Başarısız");
        }
        resultPassenger.setStatus(passenger.getStatus());
        resultPassenger.setName(passenger.getName());
        resultPassenger.setSurName(passenger.getSurName());
        return modelMapper.map(passengerRepository.save(resultPassenger), UpdatePassengerDto.class);
    }

    @Override
    public Boolean deletePassenger(Long tcNumber){
        Passenger passenger = passengerRepository.findByTcNumber(tcNumber);
        Email email = emailRepository.findByPassenger(passenger);
        if(passenger == null){
            return false;
        }
        emailRepository.deleteByPassenger(passenger);
        phoneRepository.deleteByPassenger(passenger);
        passengerRepository.deleteByTcNumber(tcNumber);

        return true;
    }


}
