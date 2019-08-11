package com.plivo.contactbook.resources;

import java.util.Collections;
import java.util.List;

import com.plivo.contactbook.model.Contact;
import com.plivo.contactbook.repository.ContactBookRepository;
import com.plivo.contactbook.util.ContactBookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-book")
public class ContactBookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactBookResource.class);

    @Autowired
    private ContactBookRepository contactBookRepository;

    @GetMapping("/contacts")
    public List<Contact> searchContacts(@RequestParam String searchString) {
        List<Contact> contactList = Collections.emptyList();
        if (!searchString.isEmpty()) {
            if (ContactBookUtil.isValidEmail(searchString)) {
                contactList = contactBookRepository.findByEmailId(searchString);
            } else {
                contactList = contactBookRepository.findByName(searchString);
            }
        }
        return contactList;
    }

    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact){
        return null;
    }

    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(@PathVariable Integer contactId){
        return null;
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Contact> deleteContact(@PathVariable Integer contactId){
        return null;
    }


}
