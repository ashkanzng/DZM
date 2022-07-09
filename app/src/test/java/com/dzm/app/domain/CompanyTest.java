package com.dzm.app.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void testEquals() {
        Company company1 = Company.builder().companyName("A").build();
        Company company2 = Company.builder().companyName("B").build();
        company1.setId(1L);
        company2.setId(1L);
        assertEquals(company1,company2);
        company2.setId(2L);
        assertNotEquals(company1,company2);
    }
}