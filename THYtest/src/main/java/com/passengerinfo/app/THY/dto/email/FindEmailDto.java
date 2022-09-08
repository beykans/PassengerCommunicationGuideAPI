package com.passengerinfo.app.THY.dto.email;

import com.passengerinfo.app.THY.entity.ContactType;
import lombok.Data;

@Data
public class FindEmailDto {
    private Long id;
    private String emailAddress;
    private ContactType contactType;
}
