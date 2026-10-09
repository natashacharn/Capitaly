/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Greedy implements Strategy {

    /**
     * Player will always buy if they can afford it
     * @param p the player who has this strategy
     * @param cost the cost of the property
     * @return true if they can afford it and false otherwise.
     */
    @Override
    public boolean willBuy(Player p, int cost) {
        return p.getBalance() >= cost;
    }
}
