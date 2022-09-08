package com.passengerinfo.app.THY.repository;

import com.passengerinfo.app.THY.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
    ContactType findByContactName(String contactName);
}
