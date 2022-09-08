package com.passengerinfo.app.THY.controller;

import com.passengerinfo.app.THY.dto.passenger.CreatePassengerDto;
import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {this.passengerService = passengerService; }

    @PostMapping("/create")
    public ResponseEntity<CreatePassengerDto> createPassenger(@RequestBody CreatePassengerDto passenger) {
        CreatePassengerDto resultPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.ok(resultPassenger);
    }
    @GetMapping("/find")
    public ResponseEntity<List<FindPassengerDto>> getPassengers() {
        List<FindPassengerDto> passengers = passengerService.findPassenger();
        return ResponseEntity.ok(passengers);
    }
    @GetMapping("/find/{tcNumber}")
    public ResponseEntity<FindPassengerDto> getPassenger(@PathVariable Long tcNumber) {
        FindPassengerDto passenger = passengerService.findPassenger(tcNumber);
        return ResponseEntity.ok(passenger);
    }
    @PutMapping("/update/{tcNumber}")
    public ResponseEntity<UpdatePassengerDto> updatePassenger (@PathVariable ("tcNumber") Long tcNumber, @RequestBody UpdatePassengerDto passenger) {
        UpdatePassengerDto resultPassenger = passengerService.updatePassenger(tcNumber, passenger);
        return ResponseEntity.ok(resultPassenger);
    }
    @DeleteMapping("/delete/{tcNumber}")
    public ResponseEntity<Boolean> deletePassenger (@PathVariable ("tcNumber") Long tcNumber){
        Boolean status = passengerService.deletePassenger(tcNumber);
        return ResponseEntity.ok(status);
    }
}
