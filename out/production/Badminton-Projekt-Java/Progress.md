GUI.java

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

```
