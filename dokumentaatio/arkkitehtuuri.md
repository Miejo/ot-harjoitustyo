# ARKKITEHTUURI

## Sovelluksen rakenne

Sovellus koostuu kolmesta pakkauksesta 
- ui: käyttöliittymä
- domain: logiikka
- dao: tiedon tallennus

### Käyttöliittymä

Käyttöliittymä on ui-pakkauksen sisällä. Käyttöliittymä koostuu viidestä eri scene-oliosta:
- startMenuScene: aloitusruutu
- settingScene: asetusten säätö
- leaderboardScene: tulostaulu
- endScene: Pelin loppumisruutu
- gameScene: Peli

### Sovelluslogiikka

Sovelluslogiikka on domain-pakkauksen alla. Logiikasta pitää huolta Game-luokka, josta kutsutaan myös muita luokkia. Game-luokassa on metodi update, jota kutsutaan JavaFX:n AnimationTimer:in avulla kerran jokaisen ruudunpäivityksen aikana.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/class_diagram.png" width="400">

Domain-pakkauksessa on myös luokka Leaderboard, joka toimii rajapintana käyttöliittymän ja tiedon tallennuksen välillä.

### Tiedon tallennus

Tiedon tallennus sisältyy dao-pakkaukseen. Tietoa tallennetaan luokkien FileLeaderboardDao ja FileSettingsDao. Tulostaulun (leaderboard) tallennus tapahtuu sqlite-tietokantaan ja asetusten (settings) .txt tiedostoon.

## Toiminnallisuudet

### Asetukset

Asetukset luetaan sovelluksen käynnistyessä. Niitä voi muokata Settings-valikon kautta, jolloin uudet valinnat tallennetaan settings.txt tiedostoon.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/settings.png" width="600">

### Pelin toiminnallisuus

#### Game loop

Pelin siirtyessä gameScene-olioon, käynnistyy käyttöliittymässä AnimationTimer, joka luo uuden instanssin Game-luokasta.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/game_loop.png" width="600">

#### Pelihahmon liikkuminen

Game loopin ollessa käynnissä ja käyttäjän antaessa syöte, liikkuu pelaaja vasemmalle tai oikealle. Riippuen asetuksista, näppäimet ovat joko "A" ja "D" tai nuolinäppäimet "<" ja ">".

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/movement.png" width="600">

#### Ampuminen

Kun game loop on käynnissä, välilyöntiä painamalla luodaan uusi Bullet-olio.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/shooting.png" width="600">

### Tulostaulu

Tulostaulu haetaan tietokannasta, kun siirrytään leaderboardScene-olioon.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/leaderboard_1.png" width="600">

Pelin loppuessa, tulos talletetaan uutena tietueena tietokantaan.

<img src="https://raw.githubusercontent.com/Miejo/ot-harjoitustyo/master/dokumentaatio/kuvat/leaderboard_2.png" width="600">
