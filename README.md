# Spacer

Sovellus on peli, jossa ammutaan ylhäältä tippuvia vihollisia.

Viikko 4 uudet ominaisuudet:
- Ammuntatiheyteen tauko
- Vihollisia useita, satunnainen sijainti
- Pistesysteemi
- Asetukset; tiedosto ja toiminnallisuus
- Värin ja nimen vaihto

## Dokumentaatio

[Arkkitehtuuri](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Komentorivitoiminnot

### Suoritus

Ohjelma suorittuu komentoriviltä komennolla

```
mvn compile exec:java -Dexec.mainClass=ui.GameUI
```

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

### Checkstyle

Checkstyle suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
