package com.passengerinfo.app.THY.controller;

import com.passengerinfo.app.THY.dto.passenger.FindPassengerDto;
import com.passengerinfo.app.THY.dto.passenger.UpdatePassengerDto;
import com.passengerinfo.app.THY.dto.phone.CreatePhoneDto;
import com.passengerinfo.app.THY.dto.phone.FindPhoneDto;
import com.passengerinfo.app.THY.dto.phone.UpdatePhoneDto;
import com.passengerinfo.app.THY.service.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {this.phoneService = phoneService; }

    @PostMapping("/create/{tcNumber}/{contactName}")
    public ResponseEntity<CreatePhoneDto> createPhone(@PathVariable Long tcNumber, @RequestBody CreatePhoneDto phone, @PathVariable String contactName) {
        CreatePhoneDto resultPhone = phoneService.createPhone(tcNumber, phone, contactName);
        return ResponseEntity.ok(resultPhone);
    }
    @GetMapping("/find")
    public ResponseEntity<List<FindPhoneDto>> getPhones() {
        List<FindPhoneDto> phones = phoneService.findPhones();
        return ResponseEntity.ok(phones);
    }
    @GetMapping("/find/{tcNumber}")
    public ResponseEntity<List<FindPhoneDto>> getPhone(@PathVariable Long tcNumber) {
        List<FindPhoneDto> phones = phoneService.findPhone(tcNumber);
        return ResponseEntity.ok(phones);
    }
    @PutMapping("/update/{phoneNumber}/{contactName}")
    public ResponseEntity<UpdatePhoneDto> updatePhone (@PathVariable Long phoneNumber, @RequestBody UpdatePhoneDto phone, @PathVariable String contactName) {
        UpdatePhoneDto resultPhone = phoneService.updatePhone(phoneNumber, phone, contactName);
        return ResponseEntity.ok(resultPhone);
    }
    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<Boolean> deletePhone (@PathVariable ("phoneNumber") Long phoneNumber){
        Boolean status = phoneService.deletePhone(phoneNumber);
        return ResponseEntity.ok(status);
    }
}
