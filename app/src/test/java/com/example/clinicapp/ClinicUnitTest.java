package com.example.clinicapp;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class ClinicUnitTest {
    @Test
    public void clinicNameTest(){
        Clinic p = new Clinic( "Standard", "23 creekly way", "2269244399", "Health", "cerdit");
        assertEquals(p.getClinicName(),"Standard");
    }

}
