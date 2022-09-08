package com.passengerinfo.app.THY;

import com.passengerinfo.app.THY.controller.EmailController;
import com.passengerinfo.app.THY.controller.PassengerController;
import com.passengerinfo.app.THY.controller.PhoneController;
import com.passengerinfo.app.THY.dto.email.CreateEmailDto;
import com.passengerinfo.app.THY.dto.email.FindEmailDto;
import com.passengerinfo.app.THY.dto.passenger.CreatePassengerDto;
import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.dto.phone.CreatePhoneDto;
import com.passengerinfo.app.THY.entity.ContactType;
import com.passengerinfo.app.THY.entity.Email;
import com.passengerinfo.app.THY.entity.Passenger;
import com.passengerinfo.app.THY.entity.Phone;
import com.passengerinfo.app.THY.repository.ContactTypeRepository;
import com.passengerinfo.app.THY.repository.EmailRepository;
import com.passengerinfo.app.THY.repository.PassengerRepository;
import com.passengerinfo.app.THY.repository.PhoneRepository;
import com.sun.jdi.LongValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ThyApiApplicationTests {

	@Autowired
	PassengerController passengerController;
	@Autowired
	PhoneController phoneController;
	@Autowired
	EmailController emailController;
	@MockBean
	PassengerRepository passengerRepository;
	@MockBean
	PhoneRepository phoneRepository;
	@MockBean
	EmailRepository emailRepository;
	@MockBean
	ContactTypeRepository contactTypeRepository;



	@Captor
	ArgumentCaptor<Passenger> passengerCaptor;
	@Captor
	ArgumentCaptor<Phone> phoneCaptor;
	@Captor
	ArgumentCaptor<Email> emailCaptor;

	@Test
	void createPassengerTest() {
		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926225L);
		createPassengerDto.setStatus("active");
		createPassengerDto.setName("ali");
		createPassengerDto.setSurName("uçar");

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setStatus(createPassengerDto.getStatus());
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		passenger.setName(createPassengerDto.getName());
		passenger.setSurName(createPassengerDto.getSurName());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		 ResponseEntity<CreatePassengerDto>  responseEntity =  passengerController.createPassenger(createPassengerDto);

		 CreatePassengerDto actualCreatePassengerDto = responseEntity.getBody();

		Assertions.assertEquals(createPassengerDto.getTcNumber(), actualCreatePassengerDto.getTcNumber());

	}
	@Test
	void findPassengers() {

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setTcNumber(43312926228L);
		passenger.setStatus("active");
		passenger.setName("ali");
		passenger.setSurName("uçar");

		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		ResponseEntity<List<FindPassengerDto>>  responseEntity =  passengerController.getPassengers();

		List<FindPassengerDto> actualFindPassengerDto = responseEntity.getBody();

		Assertions.assertEquals(passenger.getTcNumber(), actualFindPassengerDto);
	}
	@Test
	void findPassenger() {

		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926228L);
		createPassengerDto.setStatus("active");
		createPassengerDto.setName("ali");
		createPassengerDto.setSurName("uçar");

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setStatus(createPassengerDto.getStatus());
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		passenger.setName(createPassengerDto.getName());
		passenger.setSurName(createPassengerDto.getSurName());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		ResponseEntity<FindPassengerDto>  responseEntity =  passengerController.getPassenger(passenger.getTcNumber());

		FindPassengerDto actualFindPassengerDto = responseEntity.getBody();

		Assertions.assertEquals(passenger.getTcNumber(), actualFindPassengerDto.getTcNumber());
	}
	@Test
	void updatePassenger() {
		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926228L);
		createPassengerDto.setStatus("active");
		createPassengerDto.setName("ali");
		createPassengerDto.setSurName("uçar");

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setStatus(createPassengerDto.getStatus());
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		passenger.setName(createPassengerDto.getName());
		passenger.setSurName(createPassengerDto.getSurName());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		UpdatePassengerDto updatePassengerDto = new UpdatePassengerDto();
		updatePassengerDto.setId(passenger.getId());
		updatePassengerDto.setTcNumber(passenger.getTcNumber());
		updatePassengerDto.setStatus(passenger.getStatus());
		updatePassengerDto.setName(passenger.getName());
		updatePassengerDto.setSurName(passenger.getSurName());

		ResponseEntity<UpdatePassengerDto>  responseEntity =  passengerController.updatePassenger(passenger.getTcNumber() ,updatePassengerDto);

		UpdatePassengerDto actualUpdatePassengerDto = responseEntity.getBody();

		Assertions.assertEquals(passenger.getTcNumber(), actualUpdatePassengerDto.getTcNumber());
	}
	@Test
	void deletePassenger() {
		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926228L);
		createPassengerDto.setStatus("active");
		createPassengerDto.setName("ali");
		createPassengerDto.setSurName("uçar");

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setStatus(createPassengerDto.getStatus());
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		passenger.setName(createPassengerDto.getName());
		passenger.setSurName(createPassengerDto.getSurName());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		ResponseEntity<Boolean> responseEntity =  passengerController.deletePassenger(passenger.getTcNumber());

		Boolean actualUpdatePassengerDto = responseEntity.getBody();

		Assertions.assertEquals(false, actualUpdatePassengerDto);
	}
	@Test
	void createPhoneTest() {
		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926228L);

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		ContactType contactType = new ContactType();
		contactType.setContactName("kendisi");
		Mockito.when(contactTypeRepository.save(Mockito.any())).thenReturn(contactType);

		CreatePhoneDto createPhoneDto = new CreatePhoneDto();
		createPhoneDto.setPhoneNumber(905396725400L);

		Phone phone = new Phone();
		phone.setId(124L);
		phone.setPhoneNumber(createPhoneDto.getPhoneNumber());
		phone.setContactType(createPhoneDto.getContactType());
		Mockito.when(phoneRepository.save(Mockito.any())).thenReturn(phone);

		ResponseEntity<CreatePhoneDto>  responseEntity =  phoneController.createPhone(passenger.getTcNumber(), createPhoneDto, contactType.getContactName());

		CreatePhoneDto actualCreatePhoneDto = responseEntity.getBody();

		Assertions.assertEquals(createPhoneDto.getPhoneNumber(), actualCreatePhoneDto.getPhoneNumber());
	}


	@Test
	void createEmailTest() {
		CreatePassengerDto createPassengerDto = new CreatePassengerDto();
		createPassengerDto.setTcNumber(43312926228L);

		Passenger passenger = new Passenger();
		passenger.setId(123L);
		passenger.setTcNumber(createPassengerDto.getTcNumber());
		Mockito.when(passengerRepository.save(Mockito.any())).thenReturn(passenger);

		ContactType contactType = new ContactType();
		contactType.setContactName("kendisi");
		Mockito.when(contactTypeRepository.save(Mockito.any())).thenReturn(contactType);

		CreateEmailDto createEmailDto = new CreateEmailDto();
		createEmailDto.setEmailAddress("aliuzun@gmail.com");

		Email email = new Email();
		email.setId(124L);
		email.setEmailAddress(createEmailDto.getEmailAddress());
		email.setContactType(createEmailDto.getContactType());
		Mockito.when(emailRepository.save(Mockito.any())).thenReturn(email);

		ResponseEntity<CreateEmailDto>  responseEntity =  emailController.createEmail(passenger.getTcNumber(), createEmailDto, contactType.getContactName());

		CreateEmailDto actualCreateEmailDto = responseEntity.getBody();

		Assertions.assertEquals(createEmailDto.getEmailAddress(), actualCreateEmailDto.getEmailAddress());
	}
}
