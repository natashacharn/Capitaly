/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package capitaly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author natas
 */
public class Capitaly {
    
    private final List<Player> players = new ArrayList<>();
    private final List<Field> board = new ArrayList<>();
    private final List<Integer> diceRolls = new ArrayList<>();
    
    private Player winner;
    
    public Player getWinner() {
        return this.winner;
    }
    
    public void loadGame(String fp) throws FileNotFoundException, InvalidDataException {
        File file = new File(fp);
        if (!file.exists() || !file.isFile()){
            throw new FileNotFoundException("File cannot be found or opened: " + fp);
        }
        
        try (Scanner scanner = new Scanner (file)) {
            // Parse number of fields and into their subclasses
            
            if (!scanner.hasNextInt()) {
                throw new InvalidDataException("Expected total # of fields.");
            }
            
            int amtFields = scanner.nextInt();
            
            if (amtFields <= 0){
                throw new InvalidDataException("# of fields must be greater than 0.");
            }            
            
            for (int i = 0; i < amtFields; i++) {
                if (!scanner.hasNext()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                String fieldType = scanner.next();
                
                if (fieldType.equalsIgnoreCase("Property")) {
                    String name = scanner.next();
                    if (name.isEmpty()) {
                        throw new InvalidDataException("Missing property name.");
                    }
                    board.add(new Property(name));
                }
                
                else if (fieldType.equalsIgnoreCase("Service")) {
                    String name = scanner.next();

                    if (!scanner.hasNextInt()) {
                        throw new InvalidDataException("Service field:" + name + "is missing the fee parameter.");
                    }
                    
                    int fee = scanner.nextInt();
                    
                    if (fee <= 0 ) {
                        throw new InvalidDataException("Fee cannot be <= 0.");
                    }
                    scanner.nextLine();
                    board.add(new Service(name, fee));
                }
                
                else if (fieldType.equalsIgnoreCase("Lucky")) {
                    String name = scanner.next();
                    if (!scanner.hasNextInt()) {
                        throw new InvalidDataException("Lucky field:" + name + "is missing the reward parameter");
                    }
                    int reward = scanner.nextInt();
                    
                    if (reward <= 0 ) {
                        throw new InvalidDataException("Reward cannot be <= 0.");
                    }
                    scanner.nextLine();
                    board.add(new Lucky(name, reward));
                }
            
                else {
                    throw new InvalidDataException("Unknown field type: '" + fieldType + "' at field " + (i + 1));
                }
            }
            
            // players
            if (!scanner.hasNextInt()) {
                throw new InvalidDataException("Expected total # of players.");
            }

            int amtPlayers = scanner.nextInt();
            
            if (amtPlayers < 2) {
                throw new InvalidDataException("Requires at least 2 players.");
            }
            
            for (int i = 0; i < amtPlayers; i++){
                if (!scanner.hasNext()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                
                String playerName = scanner.next().trim();
                
                if (!scanner.hasNext()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                
                String stratType = scanner.next().trim();
                
                Strategy strategy;
                switch (stratType.toLowerCase()) {
                    case "greedy" -> strategy = new Greedy();
                   
                    case "careful" -> strategy = new Careful();
                    
                    case "tactical" -> strategy = new Tactical();
                    
                    default -> throw new InvalidDataException("Invalid strategy '" + stratType + "' for player: " + playerName);
                }
                
                players.add(new Player(playerName, strategy));
            }
            
            // Parse dice rolls
            while (scanner.hasNext()) {
                if (!scanner.hasNextInt()) {
                    throw new InvalidDataException("Expected integer for dice roll.");
                }
                int roll = scanner.nextInt();
                if (roll <= 0) {
                    throw new InvalidDataException("Dice roll cannot be <= 0.");
                }
                diceRolls.add(roll);
            }

            if (diceRolls.isEmpty()) {
                throw new InvalidDataException("Missing atleast 1 dice roll.");
            }
        }
    }
    
    public void play(){
        int boardSize = board.size();
        int rollIndex = 0;
        int diceSize = diceRolls.size();
        
        while (rollIndex < diceRolls.size()) {
            long remainingPlayers = players
                    .stream()
                    .filter(
                    p -> !p.isBroke()).count();
            if (remainingPlayers <= 1) break;
            
            for (Player p : players) {
                if (!p.isBroke() && rollIndex < diceSize){
                    int roll = diceRolls.get(rollIndex++);
                    int newPosition = (p.getPosition() + roll) % boardSize;
                    p.setPosition(newPosition);
                    board.get(newPosition).stepOn(p);
                    
                    if (remainingPlayers <= 1) break;
                }
            }

        }
        
        
        
        printWinner();
    }
    
    public void printWinner(){
        Player winner = null;
        int max = -100;
        
        for (Player p : players) {
            if (!p.isBroke()) {
                int wealth = p.getBalance();
                for (Property prop : p.getProperties()) {
                    wealth += Property.COST_NO_HOUSE;
                    if (prop.hasHouse()) {
                        wealth += Property.COST_HOUSE;
                    }
                }
                if (max < wealth) {
                    winner = p;
                    max = wealth;
                }
            }
        
        }
        
        if (winner == null) {
            System.out.println("All players went bankrupt.");
            return;
        }
        
        System.out.println("The winner is: " + winner.getName());
        System.out.println(winner.getName() + "'s balance: " + max);
        
        String propNames = winner.getProperties().stream()
                         .map(Property::getName)
                         .reduce((a, b) -> a + ", " + b)
                         .orElse("None");
        
        System.out.println(winner.getName() + "'s properties: " + propNames);
    }
    
    public static void main(String[] args) {
        Capitaly game = new Capitaly();
        String fp;
        if (args.length > 0) {
                fp = args[0];        // Use the file passed in the command line
        } else fp = "gameinput.txt" ;

        try {
            game.loadGame(fp);
            game.play();
        } catch (FileNotFoundException e) {
            System.err.println("File Error: " + e.getMessage());
        } catch (InvalidDataException e) {
            System.err.println("Validation Error in Game Data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected runtime error: " + e.getMessage());
        }
        
    }
    
}
