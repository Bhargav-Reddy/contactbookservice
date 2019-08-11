package com.plivo.contactbook.util;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

@Test
public class ContactBookUtilTest {

    @Test
    public void isVaildEmailShouldReturnTrue() {
        assertTrue(ContactBookUtil.isValidEmail("bhargavdagumati@gmail.com"));
    }

    @Test
    public void isVaildEmailShouldReturnFalse(){
        assertFalse(ContactBookUtil.isValidEmail("wrongemailid.com"));
    }

}