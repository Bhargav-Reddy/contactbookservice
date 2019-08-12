package com.plivo.contactbook.repository;

import java.util.List;

import com.plivo.contactbook.model.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactBookRepository extends JpaRepository<Contact, Integer>{
    List<Contact> findByName(String name, Pageable pageable);
    List<Contact> findByEmailId(String emailId);
}
