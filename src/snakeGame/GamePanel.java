package snakeGame;



import javax.swing.*; 
import java.util.Random; 
import java.awt.*; 
import java.awt.event.*; 


public class GamePanel extends JPanel implements ActionListener{
    
    static final int SCREEN_WIDTH = 600; 
    static final int SCREEN_HEIGHT = 600; 
    
    static final int UNIT_SIZE = 25; 
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE*UNIT_SIZE; 
    
    static final int DELAY = 75; 
    
    final int x[] = new int[GAME_UNITS]; 
    final int y[] = new int[GAME_UNITS]; 
    
    
    
    int bodyParts = 6; 
    int appleEaten; 
    int appleX; 
    int appleY; 
    char direction = 'R';  
    boolean running = false; 
    Timer timer; 
    Random random;
    
    GamePanel(){
        random = new Random(); 
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); 
        this.setBackground(Color.BLACK);  
        this.setFocusable(true); 
        this.addKeyListener(new MyKeyAdapter()); 
        startGame();
    } 
    
    public void startGame(){
         newApple(); 
         running = true;  
         timer = new Timer(DELAY,this); 
         timer.start(); 
    } 
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
//        for(int i=0;i<SCREEN_WIDTH/UNIT_SIZE;i++){
//            g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_WIDTH);
//            g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
//        }
        if(running){
            g.setColor(Color.RED);  
            g.fillOval(appleX, appleY,25 , 25); 
            
            for(int i=0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(Color.GREEN); 
                }else{
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); 
                }
                g.fillRect(x[i], y[i], UNIT_SIZE,UNIT_SIZE);
            }
            
            g.setColor(Color.RED); 
            g.setFont(new Font("Raleway",Font.BOLD,25)); 
            FontMetrics metrics = getFontMetrics(g.getFont()); 
            String str = "SCORE:"+appleEaten;
            g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, 25);
        }else{
            gameOver(g);
        }
    } 
    
    public void newApple(){
        appleX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;  
    }
    
    public void move(){
        for(int i=bodyParts-1;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1]; 
        } 
        
        switch(direction){
            case 'U':
                if(y[0]==0){
                    y[0] = SCREEN_HEIGHT-25; 
                }else{
                    y[0] = (y[0] - UNIT_SIZE); 
                }
                break; 
                
                
            case 'D':
                y[0] = (y[0] + UNIT_SIZE)%SCREEN_HEIGHT; 
                break; 
                
                
            case 'L': 
                if(x[0]==0){
                    x[0] = SCREEN_WIDTH-25;
                }else{
                    x[0] = (x[0] - UNIT_SIZE); 
                }
                
                break; 
            case 'R': 
                x[0] = (x[0] + UNIT_SIZE)%SCREEN_WIDTH; 
                break;
        }
        
    } 
    
    public void checkApple(){
        if(x[0]==appleX && y[0] == appleY){
            bodyParts++;  
            appleEaten++; 
            newApple();
        }
    } 
    
    public void checkCollisions(){
        for(int i=bodyParts-1;i>0;i--){
            if(x[0]==x[i] && y[0]==y[i]){
                running =false;
            }
        }
        
        if(!running){
            timer.stop();
        }
    } 
    
    public void gameOver(Graphics g){
        
        
        g.setColor(Color.RED); 
        g.setFont(new Font("Raleway",Font.BOLD,25)); 
        FontMetrics metrics1 = getFontMetrics(g.getFont()); 
        String str1 = "SCORE:"+appleEaten;
        
        g.drawString(str1, (SCREEN_WIDTH - metrics1.stringWidth(str1))/2, 50);
        g.setColor(Color.RED); 
        g.setFont(new Font("Raleway",Font.BOLD,75)); 
        FontMetrics metrics2 = getFontMetrics(g.getFont()); 
        String str2 = "GAME OVER";
        g.drawString(str2, (SCREEN_WIDTH - metrics2.stringWidth(str2))/2, SCREEN_HEIGHT/2);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(running){
             move(); 
             checkApple(); 
             checkCollisions(); 
         } 
         repaint(); 
    }
    
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction!='R'){
                        direction='L'; 
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!='D'){
                        direction='U';
                    }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if(direction!='U'){
                        direction='D';
                    }
                    break;
            }
        }
    }
    
}
