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
