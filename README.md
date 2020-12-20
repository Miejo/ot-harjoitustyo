# Spacer

Sovellus on peli, jossa ammutaan ylhäältä tippuvia vihollisia.

## Dokumentaatio

[Käyttöohje](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuuri](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Testaus](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/Miejo/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Loppupalautus](https://github.com/Miejo/ot-harjoitustyo/releases/tag/v1.0)

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

Jar-tiedoston luodaan komennolla

```
mvn package
```

Luo target-hakemistoon jar-tiedoston _Spacer-1.0-SNAPSHOT.jar_. **HUOM** config.properties -tiedosto pitää olla samassa hakemistossa, jotta jar-tiedosto toimisi.

### JavaDoc

JavaDoc raportin saa komennolla

```
mvn javadoc:javadoc
```

### Checkstyle

Checkstyle suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
