package com.plivo.contactbook.resources;


import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;

import java.util.Optional;

import com.plivo.contactbook.model.Contact;
import com.plivo.contactbook.repository.ContactBookRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ContactBookResourceTest {

    @Mock
    private ContactBookRepository contactBookRepository;

    @Autowired
    private ContactBookResource contactBookResource;
    private Contact contact;

    @BeforeTest
    public void setup(){
        contact = new Contact();
        contact.setEmailId("bhargavdagumati@gmail.com");
        contact.setName("Bhargav");
    }

    @Test
    void searchContactsShouldReturnContactList() {
        Optional<Contact> optional = Optional.of(contact);
        when(contactBookRepository.findById("bhargavdagumati@gmail.com")).thenReturn(optional);
        assertFalse(contactBookResource.searchContacts("bhargbhargavdagumati@gmail.com").isEmpty());
    }

}