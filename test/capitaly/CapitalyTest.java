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
}