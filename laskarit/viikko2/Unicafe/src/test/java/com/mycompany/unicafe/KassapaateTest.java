package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void kassanRahamaaraAlussa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraAlussa() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty() + kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiTarpeeksiRahaaVaihtoraha() {
        assertEquals(10, kassa.syoEdullisesti(250));
    }
    
    @Test
    public void syoEdullisestiTarpeeksiRahaaKassa() {
        kassa.syoEdullisesti(250);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiTarpeeksiRahaaMyyty() {
        kassa.syoEdullisesti(250);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiTarpeeksiRahaaVaihtoraha() {
        assertEquals(10, kassa.syoMaukkaasti(410));
    }
    
    @Test
    public void syoMaukkaastiTarpeeksiRahaaKassa() {
        kassa.syoMaukkaasti(410);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiTarpeeksiRahaaMyyty() {
        kassa.syoMaukkaasti(410);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void syoEdullisestiEiTarpeeksiRahaaVaihtoraha() {
        assertEquals(230, kassa.syoEdullisesti(230));
    }
    
    @Test
    public void syoEdullisestiEiTarpeeksiRahaaKassa() {
        kassa.syoEdullisesti(230);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiEiTarpeeksiRahaaMyyty() {
        kassa.syoEdullisesti(230);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiTarpeeksiRahaaVaihtoraha() {
        assertEquals(390, kassa.syoMaukkaasti(390));
    }
    
    @Test
    public void syoMaukkaastiEiTarpeeksiRahaaKassa() {
        kassa.syoMaukkaasti(390);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiEiTarpeeksiRahaaMyyty() {
        kassa.syoMaukkaasti(390);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaRahaaVeloitus() {
        kortti = new Maksukortti(400);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(160, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaRahaaVeloitus() {
        kortti = new Maksukortti(400);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaRahaaMyyty() {
        kortti = new Maksukortti(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaRahaaMyyty() {
        kortti = new Maksukortti(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaEiRahaaVeloitus() {
        kortti = new Maksukortti(200);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaEiRahaaVeloitus() {
        kortti = new Maksukortti(200);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaEiRahaaMyyty() {
        kortti = new Maksukortti(200);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaEiRahaaMyyty() {
        kortti = new Maksukortti(200);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaOstoEiMuutaKassaa() {
        kortti = new Maksukortti(800);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());       
    }
    
    @Test
    public void rahanLatausKortinSaldo() {
        kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 400);
        assertEquals(400, kortti.saldo());
    }
    
    @Test
    public void rahanLatausKassassaRahaa() {
        kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausNegatiivinen() {
        kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(0, kortti.saldo());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
