package com.plivo.contactbook.resources;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.plivo.contactbook.ContactbookserviceApplication;
import com.plivo.contactbook.model.Contact;
import com.plivo.contactbook.repository.ContactBookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ContactbookserviceApplication.class)
@WebAppConfiguration
public class ContactBookResourceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ContactBookRepository contactBookRepository;

    private MockMvc mockMvc;
    private Contact contact;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        contact = new Contact();
        contact.setContactId(1);
        contact.setEmailId("bhargavdagumati@gmail.com");
        contact.setName("Bhargav");
    }

    @Test
    public void searchContactsByEmailIdShouldReturnContactList() throws Exception {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        given(contactBookRepository.findByEmailId("bhargavdagumati@gmail.com")).willReturn(contactList);
        this.mockMvc.perform(get("/contact-book/contacts?search=bhargavdagumati@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'contactId': 1,'name': 'Bhargav';'emailId': 'bhargavdagumati@gmail.com'}]"));

    }

    @Test
    public void searchContactsByNameShouldReturnContactList() throws Exception {
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        given(contactBookRepository.findByName("Bhargav", PageRequest.of(1,10))).willReturn(contactList);
        this.mockMvc.perform(get("/contact-book/contacts?search=Bhargav"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'contactId': 1,'name': 'Bhargav';'emailId': 'bhargavdagumati@gmail.com'}]"));

    }

}