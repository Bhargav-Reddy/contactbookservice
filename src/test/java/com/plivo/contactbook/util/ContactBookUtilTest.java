package com.plivo.contactbook.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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