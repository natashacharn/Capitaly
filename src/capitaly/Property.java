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
    public final int costNoHouse;
    public final int costWithHouse;
    public final int rentNoHouse;
    public final int rentWithHouse;
    
    public Property (String name) {
        super(name);
        this.owner = null;
        this.hasHouse = false;
        this.costNoHouse = 1000;
        this.costWithHouse = 4000;
        this.rentNoHouse = 500;
        this.rentWithHouse = 2000;
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
            if (p.canBuy(costNoHouse)) {
                p.pay(costNoHouse);
            }
            this.owner = p;
            p.addProperty(this);
        }
        else if (p == owner) {
            if (p.canBuy(costWithHouse) && !hasHouse){
                p.pay(costWithHouse);
                this.hasHouse = true;
            }
        }
        else {
            int rent = hasHouse ? rentWithHouse : rentNoHouse;
            p.payDues(rent, owner);
        }
        
    }
}
