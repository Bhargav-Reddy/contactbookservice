package com.plivo.contactbook.resources;

import java.util.List;

import com.plivo.contactbook.model.Contact;
import com.plivo.contactbook.repository.ContactBookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-book")
public class ContactBookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactBookResource.class);

    private ContactBookRepository contactBookRepository;

    @GetMapping("/contacts")
    public List<Contact> searchContacts(@RequestParam String searchString){
        return null;
    }

}
