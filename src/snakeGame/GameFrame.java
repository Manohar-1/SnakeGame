 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakeGame;

/**
 *
 * @author mudha
 */ 
import javax.swing.JFrame; 
import java.awt.*;

public class GameFrame extends JFrame{
    GameFrame(){
        this.add(new GamePanel());  
        this.setTitle("Snake"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false);  
        this.pack(); 
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
    }
    
   
}
