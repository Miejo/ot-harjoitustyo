# Käyttöohje

## Lataus

Sovelluksen voi ladata [tästä](). Jotta sovellus toimisi, sovelluksen hakemiston juuressa täytyy olla tiedosto config.properties, jonka sisällön täytyy olla seuraavanlainen:

```
settingsFile=settings.txt
leaderboardFile=leaderboard.db
```

## Käyttäminen

Sovellus avautuu alkuvalikkoon, josta voi aloittaa uuden pelin, katsella ja vaihtaa asetuksia sekä katsella tulostaulua.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/menu.png" width="400">

### Asetukset

Asetuksista voi vaihtaa pelaajan nimeä, pelihahmon väriä sekä valintaa siitä, haluaako käyttää liikkumiseen "A" ja "D" -näppäimiä vai nuolinäppäimiä.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/settings_2.png" width="400">

### Pelaaminen

Peli alkaa Start-painiketta painamalla. Pelissä liikutaan vasemmalle ja oikealle painamalla asetusten mukaisia näppäimiä sekä ammutaan välilyöntiä painamalla. Tarkoituksena on ampua ylhäältä tippuvia "meteoreja". Peli loppuu, kun meteori osuu alareunaan.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/gameplay.png" width="400">
