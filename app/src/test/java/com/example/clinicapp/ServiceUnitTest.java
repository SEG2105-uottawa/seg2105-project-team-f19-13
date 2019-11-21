package com.example.clinicapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceUnitTest {
    @Test
    public void serviceNameTest(){
        Service p = new Service( "22345", "Band Aid", "Help");
        assertEquals(p.getId(),"22345");
    }
}
