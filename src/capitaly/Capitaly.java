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
    
    public void loadGame(String fp) throws FileNotFoundException {
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
                throw new InvalidDataException("# of fields must be greater than 0.")
            }
            
            for (int i = 0; i < amtFields; i++) {
                if (!scanner.next()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                String fieldType = scanner.next();
                
                if (fieldType.equalsIgnoreCase("Property")) {
                    String name = scanner.next();
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
                    board.add(new Service(name, fee));
                }
                
                else if (fieldType.equalsIgnoreCase("Lucky")) {
                    String name = scanner.next();
                    if (!scanner.hasNextInt()) {
                        throw new InvalidDataException("Lucky field:" + name + "is missing the reward parameter);
                    }
                    int reward = scanner.nextInt();
                    
                    if (reward <= 0 ) {
                        throw new InvalidDataException("Reward cannot be <= 0.");
                    }
                    board.add(new Lucky(name, reward));
                }
            
                else {
                    throw new InvalidDataException("Unknown field type: '" + type + "' at field number " + (i + 1));
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
                if (!scanner.next()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                
                String playerName = scanner.next().trim();
                
                if (!scanner.next()) {
                    throw new InvalidDataException("Unexpected EOF.");
                }
                
                String stratType = scanner.next().trim();
                
                Strategy strategy;
                switch (stratType.toLowerCase()) {
                    case "greedy" -> strategy = new Greedy();
                   
                    case "careful" -> strategy = new Careful();
                    
                    case "tactical" -> strategy = new Tactical();
                    
                    default -> throw new InvalidGameDataException("Invalid strategy '" + stratType + "' for player: " + name);
                }
                
                players.add(new Player(playerName, strategy));
            }
            
        }
    }
    
    public void play(){
    }
    
    public void winner(){
    }
    
    public static void main(String[] args) {
        
        
    }
    
}
