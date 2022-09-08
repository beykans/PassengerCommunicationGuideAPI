package com.passengerinfo.app.THY.controller;

import com.passengerinfo.app.THY.dto.email.CreateEmailDto;
import com.passengerinfo.app.THY.dto.email.FindEmailDto;
import com.passengerinfo.app.THY.dto.email.UpdateEmailDto;
import com.passengerinfo.app.THY.dto.phone.FindPhoneDto;
import com.passengerinfo.app.THY.dto.phone.UpdatePhoneDto;
import com.passengerinfo.app.THY.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {this.emailService = emailService; }

    @PostMapping("/create/{tcNumber}/{contactType}")
    public ResponseEntity<CreateEmailDto> createEmail(@PathVariable Long tcNumber, @RequestBody CreateEmailDto email, @PathVariable String contactType) {
        CreateEmailDto resultEmail = emailService.createEmail(tcNumber, email, contactType);
        return ResponseEntity.ok(resultEmail);
    }
    @GetMapping("/find")
    public ResponseEntity<List<FindEmailDto>> getEmails() {
        List<FindEmailDto> emails = emailService.findEmails();
        return ResponseEntity.ok(emails);
    }
    @GetMapping("/find/{tcNumber}")
    public ResponseEntity<List<FindEmailDto>> getPhone(@PathVariable Long tcNumber) {
        List<FindEmailDto> emails = emailService.findEmail(tcNumber);
        return ResponseEntity.ok(emails);
    }
    @PutMapping("/update/{emailAddress}/{contactName}")
    public ResponseEntity<UpdateEmailDto> updateEmail (@PathVariable String emailAddress, @RequestBody UpdateEmailDto email, @PathVariable String contactName) {
        UpdateEmailDto resultEmail = emailService.updateEmail(emailAddress, email, contactName);
        return ResponseEntity.ok(resultEmail);
    }
    @DeleteMapping("/delete/{emailAddress}")
    public ResponseEntity<Boolean> deleteEmail (@PathVariable ("emailAddress") String emailAddress){
        Boolean status = emailService.deleteEmail(emailAddress);
        return ResponseEntity.ok(status);
    }
}
