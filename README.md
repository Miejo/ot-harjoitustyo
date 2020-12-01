# Spacer

Sovellus on peli, jossa ammutaan ylhäältä tippuvia vihollisia.

Viikko 5 uudet ominaisuudet:
- Tulostaulu lisätty
- Vaihdettu sovelluslogiikkaa perustumaan aikaan eikä ruutupäivitykseen

## Dokumentaatio

[Arkkitehtuuri](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/Miejo/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

### Jar-tiedoston luonti

Jar-tiedoston luodaan komennollaa

```
mvn package
```

Luo target-hakemistoon jar-tiedoston _Spacer-1.0-SNAPSHOT.jar_. **HUOM** config.properties -tiedosto pitää olla samassa hakemistossa, jotta jar-tiedosto toimisi.

### Checkstyle

Checkstyle suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
