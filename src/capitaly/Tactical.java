/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Tactical implements Strategy {
    private boolean skip = false;
    
    /**
     * Player skips every second chance they could buy
     * @param p the player who has this strategy
     * @param cost the cost of the property
     * @return true if they can afford it and it's every other turn, false if otherwise.
     */
    @Override
    public boolean willBuy(Player p, int cost) {
        if (skip) {
            skip = false;
            return false;
        }
        else {
            skip = true;
            return p.getBalance() >= cost;
        }
    }
}
