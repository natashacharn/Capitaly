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
    
    /**
     * Returns true if the property field has a house and false if not.
     * @return
     */
    public boolean hasHouse() {
        return this.hasHouse;
    }
    
    /**
     * Returns the owner of the property
     * @return
     */
    public Player getOwner() {
        return this.owner;
    }
    
    /**
     * Sets the owner of the property to a target player
     * @param p the target player
     */
    public void setOwner(Player p) {
        this.owner = p;
    }
    
    /**
     * Clears the ownership and removes houses when the owner goes bankrupt.
     */
    public void reset() {
        this.owner = null;
        this.hasHouse = false;
    }
    
    /**
     * Checks for an owner, and asks if player can buy it
     * If owned, asks if the player wants to purchase a house
     * and charges the rent if owned, with or without a house.
     * @param p the target player stepping on the current field
     */
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
