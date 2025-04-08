# Java-Applikation

## Thema: Badminton - Zufällige Spielerverteilung

3 Felder, a 4 Leute

pro Spiel 12 Minuten (irrelevant für Implementierung)

### Verteilung der Spieler auf dem Feld:
- siehe Felder-Badminton.png

## Funktionalität

# Tasks:

1. Entwurf-GUI- Wie soll sie aussehen?

# GUI

### Entwurf:

* siehe Aufzeichnung

* [ ] rechts Scroll-Bar

* [ ] Schließ-Button

* [ ] sollen schon 12 Felder vorhanden sein

* [ ] ein schon genutztes Feld darf nicht bei einer Besetzung (Index "besetzt")
- [ ] Spieler sollen hinzugefügt / entfernt werden

# Funktionalität

* Wenn Spieler schon auf dem Feld war darf er auch wieder auf dieses Feld

* Wenn möglich sollen anfangs mit gleicher Wahrscheinlichkeit zu den Feldern zugewiesen werden 
  
  * wenn Spieler xy schon zugewiesen wurde, dann erhöht sich Wahrscheinlichkeit, dass er eine Pause machen muss (bzw. zum extra Raum gehen muss)

* alle Spieler bekommen eine zufällige Nummer sollen zu den Feldern hinzugefügt werden

* Die Spieler, die schon gespielt haben, sollen im Hintergrund gespeichert werden und mit höherer Wahrscheinlichkeit eine Pause machen müssen.

# Wichtig:

* in Praxis können nicht alle immer Pause machen, wenn mehr als 12 Leute vorhanden sind, da nur maximal 3 oder 4 Spiele gemacht werden können

# Lösungsvorschläge

### 1. LV

Speicherung im Array z.B. [Vorname, Feld, Anzahl der Besetzung]

+ für jeden neuen Eintrag wird das Array gespeichert und die Wahrscheinlichkeit steigt für die Pause der Personen mit den höchsten Besetzungen auf den Feldern

---

# Codedoku + Verständnis

## App.java:

1. ich erstelle einen Counter = 0 für seed

`random seed` = Zufallsauswahl

`seed` ist Parameter im bei  random.nextInt

Ich initialisiere einen Random-Generator mit eindeutigem Seed,der aus der aktuellen Systemzeit (die dynamisch und fortlaufend ist, daher UNIQUE!) + Zählerwert (`seedCounter`) generiert wird

Dadurch wird gewährleistet, dass bei verschiedenen Instanzen des `ZufallszahlGenerator`-Objekts und verschiedenen Aufrufen der Zufallszahlmethode (`generiereZufallszahl()`) die erzeugten Zufallszahlen unterschiedlich sind.

---