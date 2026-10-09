/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public abstract class Field {
    protected String name;
    
    public Field(String n) {
        this.name = n;
    }
    
    /**
     * Return the name of the field.
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets the name of the field.
     * @param n the name of the field
     */
    public void setName(String n) {
        this.name = n;
    }
    
    /**
     * Carries out actions if a player steps on a certain field
     * @param p player that steps on the field
     */
    public void stepOn(Player p) {
    }
}
