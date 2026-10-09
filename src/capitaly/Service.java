/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Service extends Field{
    private final int cost;
    
    public Service(String n, int cost) {
        super(n);
        this.cost = cost;
    }
    
    /**
     * Charges the player a service fee with p.pay(cost) to the bank.
     * @param p player that steps on the field
     */
    @Override
    public void stepOn(Player p){
        p.pay(cost);
    }
}
