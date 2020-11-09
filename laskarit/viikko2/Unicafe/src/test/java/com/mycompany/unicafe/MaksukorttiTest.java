package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void saldonLataaminen() {
        kortti.lataaRahaa(10);
        
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void rahanOttaminen() {
        kortti.otaRahaa(10);
        
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenJosEiSaldoa() {
        kortti.otaRahaa(20);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(5));
    }
    
    @Test
    public void otaRahaaPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(20));
    }
}
