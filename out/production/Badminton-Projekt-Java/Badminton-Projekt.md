# Java-Applikation

## Thema

3 Felder, a 4 Leute

pro Spiel 12 Minuten (irrelevant für Implementierung)

### Verteilung der Spieler auf dem Feld:



<img src="file:///C:/Users/kevin/Dropbox/3.%20Semester/Badminton-Projekt/Felder-Badminton.png" title="" alt="Felder-Badminton.png" width="342">

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
- [ ]  Spieler sollen hinzugefügt / entfernt werden

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

# Funktionierender Code für undefinierte Spieleranzahl

Logic.java:

```java

```

GUI.java:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame{
    private JFrame frame;
    private JPanel playerPanel;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private Logic logic;

    public GUI(){
        frame = new JFrame("Random Number Generator for Badminton-Players");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(playerPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addPlayer();
            }
        });

        removeButton = new JButton("Remove Player");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePlayer();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        logic = new Logic();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void addPlayer(){
        JPanel player = new JPanel(new FlowLayout());

        JLabel playerLabel = new JLabel("Player "+(logic.getPlayerCount() +1));
        JTextField nameTextField = new JTextField(10);
        JTextField randomNumberField = new JTextField(3);
        randomNumberField.setEditable(false);

        player.add(playerLabel);
        player.add(nameTextField);
        player.add(randomNumberField);

        playerPanel.add(player);
        playerPanel.revalidate(); //Wofür brauche ich die? Nötig?

        logic.setPlayerCount(logic.getPlayerCount() + 1); // Increase player counter by 1
        updateRandomNumberFields();
    }

    private void removePlayer(){
        if (logic.getPlayerCount() > 0) {
            logic.setPlayerCount(logic.getPlayerCount() - 1);
            playerPanel.remove(playerPanel.getComponentCount() - 1);
            playerPanel.revalidate();
            playerPanel.repaint();
            updateRandomNumberFields();
        }
    }

    private void updateRandomNumberFields(){
        int playerCount = logic.getPlayerCount();
        for (int i = 1; i <= playerCount; i++) {
            JPanel player = (JPanel) playerPanel.getComponent(i - 1);
            JTextField randomNumberField = (JTextField) player.getComponent(2);
            randomNumberField.setText(String.valueOf(logic.getPlayerNumber(i)));


        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new GUI();
            }
        });
    }

    /*
    private JTextField[] playerTextfields;
    private JButton generateRandomNumberButton;

    public GUI(int numberOfPlayer){

    }


    public static void main(String[] args) {
        App app = new App();

        for(int pos = 0; pos < 12; pos++){
            int randomPosition = app.generateRandomPosition();
            //System.out.println("The generated random number between 1 and 12 is: "+ randomPosition);
            System.out.println("Zufallszahl für Personen " + (pos+1)+ ": " + randomPosition);
        }
    }
    */
}
```

# Funktionierender Code für definierte Spieleranzahl (12)

GUI.java

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI extends JFrame{
    private JFrame frame;
    private JPanel playerPanel;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private Logic logic;

    public GUI(){
        frame = new JFrame("Random Number Generator for Badminton-Players");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        playerPanel = new JPanel(new GridLayout(0, 2));
        scrollPane = new JScrollPane(playerPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addPlayer();
            }
        });

        removeButton = new JButton("Remove Player");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePlayer();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        logic = new Logic();

        createInitialPlayers(); // starts with 12 players

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void addPlayer(){
        logic.setPlayerCount(logic.getPlayerCount() + 1);

        JPanel player = new JPanel(new FlowLayout());

        JLabel playerLabel = new JLabel("Player "+(logic.getPlayerCount()));
        JTextField nameTextField = new JTextField(10);
        JTextField randomNumberField = new JTextField(3);
        randomNumberField.setEditable(false);

        player.add(playerLabel);
        player.add(nameTextField);
        player.add(randomNumberField);

        playerPanel.add(player);
        playerPanel.revalidate(); //Wofür brauche ich die? Nötig?

        updateRandomNumberFields();
    }

    private void removePlayer(){
        if (logic.getPlayerCount() > 0) {
            logic.setPlayerCount(logic.getPlayerCount() - 1);
            playerPanel.remove(playerPanel.getComponentCount() - 1);
            playerPanel.revalidate();
            playerPanel.repaint();
            updateRandomNumberFields();
        }
    }

    private void updateRandomNumberFields(){
        int playerCount = logic.getPlayerCount();
        for (int i = 1; i <= playerCount; i++) {
            JPanel player = (JPanel) playerPanel.getComponent(i - 1);
            JTextField randomNumberField = (JTextField) player.getComponent(2);
            randomNumberField.setText(String.valueOf(logic.getPlayerNumber(i)));


        }

    }

    private void createInitialPlayers(){
        for(int i = 0; i < 12; i++){
            addPlayer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new GUI();
            }
        });
    }

    /*
    private JTextField[] playerTextfields;
    private JButton generateRandomNumberButton;

    public GUI(int numberOfPlayer){

    }


    public static void main(String[] args) {
        App app = new App();

        for(int pos = 0; pos < 12; pos++){
            int randomPosition = app.generateRandomPosition();
            //System.out.println("The generated random number between 1 and 12 is: "+ randomPosition);
            System.out.println("Zufallszahl für Personen " + (pos+1)+ ": " + randomPosition);
        }
    }
    */
}
```

---

Logic.java

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Logic{
    //Klassenvariablen
    private Random random;
    private List<Integer> pausedPlayers;
    private List<Integer> playingPlayers;

    public Logic(){
        random = new Random();
        playingPlayers = new ArrayList<>();
        pausedPlayers = new ArrayList<>();
    }
    public void addPlayer(){
        int newPlayer = playingPlayers.size() + pausedPlayers.size() + 1;
        playingPlayers.add(newPlayer);
    }
    public void removePlayer(){
        if (playingPlayers.size() > 0) {
            playingPlayers.remove(playingPlayers.size() - 1);            
        }
    }
    public int getPlayerCount(){
        return playingPlayers.size() + pausedPlayers.size();
    }

    public void setPlayerCount(int count){
        while(getPlayerCount() < count) {
            addPlayer();
        }
        while (getPlayerCount() > count) {
            removePlayer();
        }
    }

    public int getPlayerNumber(){
        int maxRange = (playingPlayers.size() > 12) ? 12 : playingPlayers.size();
        int selectedPlayer = playingPlayers.get(random.nextInt(maxRange));
        updatePlayerStatus(selectedPlayer);
        return selectedPlayer;
    }

    private void updatePlayerStatus(int player){
        if (playingPlayers.contains(player)){
            playingPlayers.remove(Integer.valueOf(player));
            if(pausedPlayers.size() < 12){
                pausedPlayers.add(player);
            }
        }else if(pausedPlayers.contains(player)){
            pausedPlayers.remove(Integer.valueOf(player));
            playingPlayers.add(player);
        }
    }
    public boolean isPlayerPlaying(int player){
        return playingPlayers.contains(player);
    }

    public String getPlayerStatus(int player){
        return pausedPlayers.contains(player) ? "P" : ""; //returns P, if player is paused
    }
}
```

# Stand: 13.12.

GUI.java:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI {
    private JFrame frame;
    private JPanel playerPanel;
    private JButton addButton;
    private JButton removeButton;
    private JButton genNewNumButton;
    private JScrollPane scrollPane;
    private Logic logic;

    public GUI(){
        frame = new JFrame("Random Number Generator for Badminton-Players");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));

        playerPanel = new JPanel(new GridLayout(0, 2));
        scrollPane = new JScrollPane(playerPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addPlayer();
            }
        });

        removeButton = new JButton("Remove Player");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePlayer();
            }
        });

        genNewNumButton =  new JButton("Generate New Number");
        genNewNumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                generateRandomNumbers();
                updatePlayerPanels();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(genNewNumButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        logic = new Logic();

        createInitialPlayers(); // starts with 12 players

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void addPlayer(){
        logic.addPlayer();
        updatePlayerPanels();
    }

    private void removePlayer(){
        logic.removePlayer();
        updatePlayerPanels();

    }

    private void generateRandomNumbers(){
       logic.generateRandomNumbers();
    }
    private void updatePlayerPanels(){
        playerPanel.removeAll();
        for(Player player : logic.getPlayers()){
            JPanel playerPanel = new JPanel(new FlowLayout());
            JLabel playerLabel = new JLabel("Player "+(logic.getPlayers().indexOf(player) + 1));

            JTextField statusField = new JTextField(3);
            statusField.setEditable(false);
            statusField.setText(player.getStatus());

            JTextField numberField = new JTextField(3);
            numberField.setEditable(false);
            numberField.setText((player.getStatus().equals("P")) ? "" :  String.valueOf(player.getNumber()));


            playerPanel.add(playerLabel);
            playerPanel.add(statusField);
            playerPanel.add(numberField);

            this.playerPanel.add(playerPanel);

        }
        frame.revalidate();
        frame.repaint();
    }
        /* 
        int playerCount = logic.getPlayerCount();
        for (int i = 1; i <= playerCount; i++) {
            JPanel player = (JPanel) playerPanel.getComponent(i - 1);
            JTextField randomNumberField = (JTextField) player.getComponent(2);
            randomNumberField.setText(String.valueOf(logic.getPlayerNumber(i)));
        */



    private void createInitialPlayers(){
        for(int i = 0; i < 12; i++){
            addPlayer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new GUI();
            }
        });
    }

    /*
    private JTextField[] playerTextfields;
    private JButton generateRandomNumberButton;

    public GUI(int numberOfPlayer){

    }


    public static void main(String[] args) {
        App app = new App();

        for(int pos = 0; pos < 12; pos++){
            int randomPosition = app.generateRandomPosition();
            //System.out.println("The generated random number between 1 and 12 is: "+ randomPosition);
            System.out.println("Zufallszahl für Personen " + (pos+1)+ ": " + randomPosition);
        }
    }
    */
}
```

Player.java

```java
class Player {
    private String status; //"P" for pause, empty if player is playing
    private int number; // random number or 0 for paused player

    public Player(){
        this.status = "";
        this.number = 0;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }


}
```

Logic.java

```java

```

---

# Stand: 18.12.
