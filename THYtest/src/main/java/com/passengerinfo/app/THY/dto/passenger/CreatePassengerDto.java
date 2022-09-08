package com.passengerinfo.app.THY.dto.passenger;

import lombok.Data;

@Data
public class CreatePassengerDto{

    private Long tcNumber;
    private String status;
    private String name;
    private String surName;
}
