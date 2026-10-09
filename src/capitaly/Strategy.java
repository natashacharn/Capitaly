/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public interface Strategy {

    /**
     * Decides if a player will buy it based on their strategy
     * @param p the player who has this strategy
     * @param cost the cost of the property
     * @return true if they can, false if not.
     */
    boolean willBuy(Player p, int cost);
}
