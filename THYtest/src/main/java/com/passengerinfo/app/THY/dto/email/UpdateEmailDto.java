package com.passengerinfo.app.THY.dto.email;

import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.entity.ContactType;
import lombok.Data;

@Data
public class UpdateEmailDto {
    private Long id;
    private String emailAddress;
    private ContactType contactType;
}
