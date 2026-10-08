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
    
    public Player(String n) {
        this.isBroke = false;
        this.balance = 10000;
        this.name = n;
        this.position = 0;
    }
    
    public void addProperty(Property p) {
        properties.add(p);
    }
    
    public List<Property> getProperties() {
        return this.properties;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public void setPosition(int pos) {
        this.position = pos;
    }
    
    public int getBalance() {
        return this.balance;
    }
    
    public void setBalance(int c) {
        this.balance = c;
    }
    
    public Strategy getStrategy() {
        return this.strat;
    }
    
    public void setStrategy(Strategy s) {
        this.strat = s;
    }
    
    public boolean isBroke() {
        return this.isBroke;
    }
    
    public void goBroke() {
        this.isBroke = true;
        this.balance = 0;
        for (Property prop : properties) {
            prop.reset();
        }
        properties.clear();
    }
    
    public boolean canBuy(int cost) {
        if (balance < cost) return false;
        
        return strat.willBuy(this, cost);
    }
    
    public void deposit(int amt) {
        this.balance += amt;
    }
    
    public void pay(int cost){
        if (this.getBalance() < cost) {
            this.goBroke();
        }
        else {
            this.balance -= cost;
        }
    }
    
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

