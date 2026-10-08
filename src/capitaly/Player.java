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
    private final int income;
    public List<Property> properties = new ArrayList<>();
    public Strategy strat;
    private boolean isBroke;
    
    public Player(String n) {
        this.isBroke = false;
        this.income = 10000;
        this.name = n;
    }
    
    public void addProperty(Property p) {
        properties.add(p);
    }
    
    public List<Property> getProperties() {
        return this.properties;
    }
    
    public Strategy getStrategy() {
        return this.strat;
    }
    
    public void setStrategy(Strategy s) {
        this.strat = s;
    }
    
    public boolean getBroke() {
        return this.isBroke;
    }
    
    public void goBroke() {
        this.isBroke = true;
    }
    
    
}

