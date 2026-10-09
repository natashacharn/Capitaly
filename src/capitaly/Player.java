/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

import java.util.*;

/**
 *
 * @author natas
 */
public class Player {
    private final String name;
    private int balance;

    public List<Property> properties = new ArrayList<>();

    public Strategy strat;
    private boolean isBroke;
    private int position;
    
    public Player(String n, Strategy strat) {
        this.isBroke = false;
        this.balance = 10000;
        this.name = n;
        this.strat = strat;
        this.position = 0;
    }
    
    /**
     * Returns the name of the player
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Adds a property to the player's owned properties
     * @param p is the targeted property field
     */
    public void addProperty(Property p) {
        properties.add(p);
    }
    
    /**
     * Returns a list of the player's owned properties
     * @return 
     */
    public List<Property> getProperties() {
        return this.properties;
    }
    
    /**
     * Returns the player's position
     * @return
     */
    public int getPosition() {
        return this.position;
    }
    
    /**
     * Sets the player's position to a specific spot
     * @param pos the target position
     */
    public void setPosition(int pos) {
        this.position = pos;
    }
    
    /**
     * Returns the player's balance
     * @return
     */
    public int getBalance() {
        return this.balance;
    }
    
    /**
     * Sets the player's balance to a specific cost
     * @param c the target amount
     */
    public void setBalance(int c) {
        this.balance = c;
    }
    
    /**
     * Returns the player's strategy
     * @return
     */
    public Strategy getStrategy() {
        return this.strat;
    }
    
    /**
     * Sets the player's strategy to a specific strategy
     * @param s the target strategy
     */
    public void setStrategy(Strategy s) {
        this.strat = s;
    }
    
    /**
     * Returns true if the player is broke, otherwise false.
     * @return
     */
    public boolean isBroke() {
        return this.isBroke;
    }
    
    /**
     * Sets the player’s money to 0 and resets the owned properties
     */
    public void goBroke() {
        this.isBroke = true;
        this.balance = 0;
        for (Property prop : properties) {
            prop.reset();
        }
        properties.clear();
    }
    
    /**
     * Checks if the player has enough money to make a purchase
     * @param cost the price of the target
     * @return true if the player can afford it and their strategy supports it, otherwise false.
     */
    public boolean canBuy(int cost) {
        if (balance < cost) return false;
        
        return strat.willBuy(this, cost);
    }
    
    /**
     * Adds money to the player’s balance
     * @param amt number of money to be added
     */
    public void deposit(int amt) {
        this.balance += amt;
    }
    
    /**
     * Removes money from the player’s balance
     * @param cost amount of money to be paid
     */
    public void pay(int cost){
        if (this.getBalance() < cost) {
            this.goBroke();
        }
        else {
            this.balance -= cost;
        }
    }
    
    /**
     * Pays a certain opponent the due amount
     * If broke, it pays the player’s remaining balance to the opponent and triggers goBroke().
     * @param amt the amount of money to pay to the recipient
     * @param recip the player to be owed money to
     */
    public void payDues(int amt, Player recip){
        if (this.getBalance() < amt) {
            recip.deposit(balance);
            this.goBroke();
        }
        else {
            balance -= amt;
            recip.deposit(amt);
        }
        
    }
   
}

