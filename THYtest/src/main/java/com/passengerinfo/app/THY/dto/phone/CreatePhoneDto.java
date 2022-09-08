package com.passengerinfo.app.THY.dto.phone;

import com.passengerinfo.app.THY.entity.ContactType;
import lombok.Data;

@Data
public class CreatePhoneDto {
    private Long phoneNumber;
    private ContactType contactType;
}
