/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Property extends Field{
    private Player owner;
    private boolean hasHouse;
    public static final int COST_NO_HOUSE = 1000;
    public static final int COST_HOUSE = 4000;
    public static final int RENT_NO_HOUSE = 500;
    public static final int RENT_HOUSE = 2000;
    
    public Property (String name) {
        super(name);
        this.owner = null;
        this.hasHouse = false;
    }
    
    public boolean hasHouse() {
        return this.hasHouse;
    }
    
    public Player getOwner() {
        return this.owner;
    }
    
    public void setOwner(Player p) {
        this.owner = p;
    }
    
    public void reset() {
        this.owner = null;
        this.hasHouse = false;
    }
    
    @Override 
    public void stepOn(Player p) {
        if (owner == null) {
            if (p.canBuy(COST_NO_HOUSE)) {
                p.pay(COST_NO_HOUSE);
                this.owner = p;
                p.addProperty(this);
            }
            
        }
        else if (p == owner) {
            if (p.canBuy(COST_HOUSE) && !hasHouse){
                p.pay(COST_HOUSE);
                this.hasHouse = true;
            }
        }
        else {
            int rent = hasHouse ? RENT_HOUSE : RENT_NO_HOUSE;
            p.payDues(rent, owner);
        }
        
    }
}
