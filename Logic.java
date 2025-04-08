import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Logic {
    private Random random;
    private List<Player> players;
    private List<Integer> availableNumbers; //creates an Integer list

    public Logic() {
        random = new Random();
        players = new ArrayList<>();
        availableNumbers = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            availableNumbers.add(i);
        }
    }

    public void addPlayer() {
        players.add(new Player());
        updateAvailableNumbers();
    }

    public void removePlayer() {
        if (!players.isEmpty()) {
            players.remove(players.size() - 1);
            updateAvailableNumbers();
        }
    }

    private void updateAvailableNumbers() {
        availableNumbers.clear(); // Lösche alle vorhandenen Zahlen
    
        // Füge neue Zahlen basierend auf der Anzahl der Spieler hinzu
        for (int i = 1; i <= players.size(); i++) {
            availableNumbers.add(i);
        }
    }

    public int getPlayerCount() {
        return players.size();
    }

    public void updatePlayerNames(List<String> names) {
        if (names.size() != players.size()) {
            throw new IllegalArgumentException("Number of names does not match number of players.");
        }
    
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setName(names.get(i));
        }
    }
    

    public void generateRandomNumbers() {
        List<Integer> numbersToAssign = new ArrayList<>(availableNumbers);

        if (players.size() > availableNumbers.size()) {
            throw new IllegalStateException("Not enough available numbers to assign.");
        }
        
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            if (!currentPlayer.getStatus().equals("P")) {
                if (!numbersToAssign.isEmpty()) {
                    int index = random.nextInt(numbersToAssign.size());
                    currentPlayer.setNumber(numbersToAssign.get(index));
                    numbersToAssign.remove(index);
                } else {
                    // Handle the case when there are not enough numbers available
                    // You can throw an exception or handle it based on your requirements
                    // For example:
                    throw new IllegalStateException("Not enough available numbers to assign.");
                }
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
    
}