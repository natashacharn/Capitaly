/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Careful implements Strategy {

    /**
     * Player only buys if the cost is at most half their money
     * @param p the player who has this strategy
     * @param cost the cost of the property
     * @return true if they can afford it and the cost is at most half their balance, false otherwise.
     */
    @Override
    public boolean willBuy(Player p, int cost) {
        return (p.getBalance() / 2 ) >= cost;
    }
}
