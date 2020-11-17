# Spacer

Sovellus on peli, jossa ammutaan ylhäältä tippuvia vihollisia. Tällä hetkellä sovelluksen avautuu alkuvalikkoon, jossa vain "Start"-nappula toimii. Nappula aloittaa pelin, jossa on yksi vihollinen, jonka voi tuhota. Sovellus on suljettava manuaalisesti, eikä "peli" siis lopu koskaan.

## Dokumentaatio

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
