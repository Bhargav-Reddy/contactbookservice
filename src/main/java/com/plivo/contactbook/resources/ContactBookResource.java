package com.plivo.contactbook.resources;

import java.util.ArrayList;
import java.util.List;

import com.plivo.contactbook.exception.ResourceNotFoundException;
import com.plivo.contactbook.model.Contact;
import com.plivo.contactbook.repository.ContactBookRepository;
import com.plivo.contactbook.util.ContactBookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Contact> searchContacts(@RequestParam String search, Pageable pageable) {
        List<Contact> contactList = new ArrayList<>();
        if (!search.isEmpty()) {
            if (ContactBookUtil.isValidEmail(search)) {
                LOGGER.info("Search contacts with emailId : "+search);
                contactList = contactBookRepository.findByEmailId(search);
            } else {
                LOGGER.info("Search contacts with name : "+search);
                contactList = contactBookRepository.findByName(search, pageable);
            }
        }
        return contactList;
    }

    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact){
        return contactBookRepository.save(contact);
    }

    @PutMapping("/contacts/{contactId}")
    public Contact updateContact(@PathVariable Integer contactId, @RequestBody Contact contactRequest){
        return contactBookRepository.findById(contactId)
                .map(contact -> {
                    contact.setEmailId(contactRequest.getEmailId());
                    contact.setName(contactRequest.getName());
                    return contactBookRepository.save(contact);
                }).orElseThrow(() -> new ResourceNotFoundException("Contact not found with Id : "+contactId));
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable Integer contactId){
        return contactBookRepository.findById(contactId)
                .map(contact -> {
                    contactBookRepository.delete(contact);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> (new ResourceNotFoundException("Contact not found with Id : "+contactId)));
    }


}
