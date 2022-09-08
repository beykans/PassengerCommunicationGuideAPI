package com.passengerinfo.app.THY.dto.email;

import com.passengerinfo.app.THY.dto.passenger.CreatePassengerDto;
import com.passengerinfo.app.THY.entity.ContactType;
import lombok.Data;

@Data
public class CreateEmailDto {
    private String emailAddress;
    private ContactType contactType;
}
