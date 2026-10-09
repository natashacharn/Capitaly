/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

import org.junit.Test;
import static org.junit.Assert.*;

public class CapitalyTest {

    @Test
    public void testGameSimulation() throws Exception {
        Capitaly game = new Capitaly();
        game.loadGame("gameinput.txt");
        game.play();
        Player winner = game.getWinner();

        assertEquals("Felisa", winner.getName());
        assertEquals(8250, winner.getBalance());

        int wealth = winner.getBalance();
        for (Property prop : winner.getProperties()) {
            wealth += Property.COST_NO_HOUSE;
            if (prop.hasHouse()) {
                wealth += Property.COST_HOUSE;
            }
        }
        assertEquals(11250, wealth);
        assertEquals(3, winner.getProperties().size());
        assertEquals("Silver_Estates", winner.getProperties().get(0).getName());
        assertEquals("Willow_Estates", winner.getProperties().get(1).getName());
        assertEquals("King_Estates", winner.getProperties().get(2).getName());
    }

    @Test
    public void testHouseAndWealth() throws Exception {
        Capitaly game = new Capitaly();
        game.loadGame("gameinput_house.txt");
        game.play();
        Player winner = game.getWinner();

        assertEquals("Alice", winner.getName());
        assertEquals(9500, winner.getBalance());

        int wealth = winner.getBalance();
        for (Property prop : winner.getProperties()) {
            wealth += Property.COST_NO_HOUSE;
            if (prop.hasHouse()) {
                wealth += Property.COST_HOUSE;
            }
        }
        assertEquals(10500, wealth);
        assertEquals(1, winner.getProperties().size());
        assertEquals("Boardwalk", winner.getProperties().get(0).getName());
    }

    @Test
    public void testBrokeElims() throws Exception {
        Capitaly game = new Capitaly();
        game.loadGame("gameinput_bankrupt.txt");
        game.play();
        Player winner = game.getWinner();

        assertEquals("Sarah", winner.getName());
        assertEquals(10000, winner.getBalance());

        int wealth = winner.getBalance();
        for (Property prop : winner.getProperties()) {
            wealth += Property.COST_NO_HOUSE;
            if (prop.hasHouse()) {
                wealth += Property.COST_HOUSE;
            }
        }
        assertEquals(10000, wealth);
        assertEquals(0, winner.getProperties().size());
    }

    @Test
    public void testTacticalBuy() throws Exception {
        Capitaly game = new Capitaly();
        game.loadGame("gameinput_tactical.txt");
        game.play();
        Player winner = game.getWinner();

        assertEquals("Jerry", winner.getName());
        assertEquals(9500, winner.getBalance());

        int wealth = winner.getBalance();
        for (Property prop : winner.getProperties()) {
            wealth += Property.COST_NO_HOUSE;
            if (prop.hasHouse()) {
                wealth += Property.COST_HOUSE;
            }
        }
        assertEquals(10500, wealth);
        assertEquals(1, winner.getProperties().size());
        assertEquals("First_Lane", winner.getProperties().get(0).getName());
    }
    
    @Test
    public void testHouseRentAndBrokeReset() throws Exception {
        Capitaly game = new Capitaly();
        game.loadGame("gameinput_reset.txt");
        game.play();
        Player winner = game.getWinner();

        assertEquals("Maya", winner.getName());
        assertEquals(7500, winner.getBalance());

        int wealth = winner.getBalance();
        for (Property prop : winner.getProperties()) {
            wealth += Property.COST_NO_HOUSE;
            if (prop.hasHouse()) {
                wealth += Property.COST_HOUSE;
            }
        }
        assertEquals(7500, wealth);
        assertEquals(0, winner.getProperties().size());
    }
    
    
}