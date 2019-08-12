package com.plivo.contactbook.resources;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
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
    private List<Contact> contactList;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        contact = new Contact();
        contact.setContactId(1);
        contact.setEmailId("bhargavdagumati@gmail.com");
        contact.setName("Bhargav");
        contactList = new ArrayList<>();
        contactList.add(contact);
    }

    @Test
    public void searchContactsByEmailIdShouldReturnContactList() throws Exception {
        when(contactBookRepository.findByEmailId("bhargavdagumati@gmail.com")).thenReturn(contactList);
        this.mockMvc.perform(get("/contact-book/contacts?search=bhargavdagumati@gmail.com")).andExpect(status().isOk()).andExpect(content().json("[{'contactId': 1,'name': 'Bhargav','emailId': 'bhargavdagumati@gmail.com'}]"));

    }

    @Test
    public void searchContactsByNameShouldReturnContactList() throws Exception {
        when(contactBookRepository.findByName("Bhargav", PageRequest.of(0, 10))).thenReturn(contactList);
        this.mockMvc.perform(get("/contact-book/contacts?search=Bhargav&page=0&size=10")).andExpect(status().isOk()).andExpect(content().json("[{'contactId': 1,'name': 'Bhargav','emailId': 'bhargavdagumati@gmail.com'}]"));

    }

    @Test
    public void addContactShouldReturnContact() throws Exception {
        when(contactBookRepository.save(contact)).thenReturn(contact);
        this.mockMvc.perform(post("/contact-book/contacts").contentType(MediaType.APPLICATION_JSON).content(mapToJson(contact))).andExpect(status().isOk());

    }

    @Test
    public void updateContactShouldReturnContact() throws Exception {
        when(contactBookRepository.findById(1)).thenReturn(Optional.of(contact));
        when(contactBookRepository.save(contact)).thenReturn(contact);
        this.mockMvc.perform(put("/contact-book/contacts/1").contentType(MediaType.APPLICATION_JSON).content(mapToJson(contact))).andExpect(status().isOk()).andExpect(content().string("{\"contactId\":1,\"name\":\"Bhargav\",\"emailId\":\"bhargavdagumati@gmail.com\"}"));
    }

    @Test
    public void deleteContactShouldReturnOK() throws Exception {
        when(contactBookRepository.findById(1)).thenReturn(Optional.of(contact));
        this.mockMvc.perform(delete("/contact-book/contacts/1")).andExpect(status().isOk());
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}