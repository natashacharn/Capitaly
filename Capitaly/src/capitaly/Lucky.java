/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capitaly;

/**
 *
 * @author natas
 */
public class Lucky extends Field{
    private final int reward;
    
    public Lucky(String n, int reward) {
        super(n);
        this.reward = reward;
    }
    
    @Override
    public void stepOn(Player p){
        p.deposit(reward);
    }
}
