package com.plivo.contactbook.repository;

import java.util.List;

import com.plivo.contactbook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactBookRepository extends JpaRepository<Contact, String>{
    List<Contact> findByName(String searchString);
}
