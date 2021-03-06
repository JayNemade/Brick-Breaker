import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.util.Random;

public class gameplay extends JPanel implements KeyListener, ActionListener{
      private Random rand = new Random();

      private boolean play = false;
      private int score = 0;

      private int totalBricks = 21;

      private Timer timer;
      private int delay = 10;

      private int playerX = 310;


      private int  ballposX  = rand.nextInt(700);
      private int ballposY = rand.nextInt(350);
      private int ballXdir = -1;
      private int ballYdir = -2;

      private Mapgenerator map;

      public gameplay(){
           map = new Mapgenerator(3,7);
          addKeyListener(this);
          setFocusable(true);
          setFocusTraversalKeysEnabled(false);
          timer = new Timer(this.delay, this);
          timer.start();
      }
      public void paint(Graphics g){

           //Background
           g.setColor(Color.black);
           g.fillRect(1,1,692,592);

          //Bricks
          map.draw((Graphics2D)g);

          //Border
          g.setColor(Color.yellow);
          g.fillRect(0,0,3,592);
          g.fillRect(0,0,692,3);
          g.fillRect(691,0,3,592);
          //score

          g.setColor(Color.white);
          g.setFont(new Font("serif",Font.ITALIC,25));
          g.drawString("SCORE :"+score,540,30);
          g.drawString("DELAY :"+delay,0,30);

         //playerX
         g.setColor(Color.green);
         g.fillRect(playerX,550,100,8);

         //ball
         g.setColor(Color.red);
         g.fillOval(ballposX,ballposY,20,20);

      //  g.dispose();
           if(totalBricks <=0){
             this.delay--;
             play = false;

             ballposX = rand.nextInt(700);
             ballposY = rand.nextInt(350);
             ballXdir = -1;
             ballYdir = -2;
             playerX = 310;

             totalBricks = 21;
             map = new Mapgenerator(3,7);

             repaint();
           }
           if(ballposY >550){

            play = false;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.ITALIC,25));
            g.drawString("GAME OVER "+this.score,300,400);
            g.drawString("Press ENTER to continue",300,500);
         }

}


         @Override
         public  void actionPerformed(ActionEvent e){
          timer.start();
          if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
              ballYdir = -ballYdir;
            }
            for(int i = 0;i <map.map.length;i++){
               for(int j = 0;j<map.map[0].length;j++){
                 if(map.map[i][j] > 0){
                   int brickX = j* map.brickWidth + 80;
                   int brickY = i* map.brickHeight+50;
                    int brickHeight = map.brickHeight;
                    int brickWidth = map.brickWidth;
                 Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                 Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
                Rectangle brickRect = rect;
                if(ballrect.intersects(brickRect)){
                  map.setBrickValue(0,i,j);
                  totalBricks--;
                  score += 5;

                  if(ballposX+19 <=brickRect.x||ballposX +1>= brickRect.x+brickRect.width){
                    ballXdir = -ballXdir;
                  }else{
                    ballYdir = -ballYdir;
                  }
                }
                 }
               }
            }
             ballposX += ballXdir;
             ballposY += ballYdir;
             if(ballposX <0){
               ballXdir =-ballXdir;
             }
             if(ballposY <0){
               ballYdir =-ballYdir;
             }
             if(ballposX >670){
               ballXdir =-ballXdir;
             }

        }
          repaint();
         }

      @Override
      public void keyPressed(KeyEvent e){

          if(e.getKeyCode() == KeyEvent.VK_RIGHT){
             play = true;
             if(playerX >= 600){
                 playerX = 600;
             }else{

                playerX +=40;
             }
          }if(e.getKeyCode() == KeyEvent.VK_LEFT){
            play = true;
            if(playerX < 10){
               playerX = 10;
            }else{
               playerX -=40;
            }
          }
          if(e.getKeyCode()==KeyEvent.VK_ENTER){
               if(!play && totalBricks >=1){
                 play = true;
                 ballposX = rand.nextInt(700);
                 ballposY = rand.nextInt(350);
                 ballXdir = -1;
                 ballYdir = -2;
                 playerX = 310;
                if(totalBricks > 0){
                  score = 0;
                  delay = 10;
                }
                 else {score = this.score;
                  delay = delay-1;
                 }
                 totalBricks = 21;
                 map = new Mapgenerator(3,7);

                 repaint();
               }
          }
}

      @Override
      public void keyReleased(KeyEvent e){

      }

      @Override
      public void keyTyped(KeyEvent e){

      }
/*  public static long rand(double r){
    double  f = Math.random();
    long R = Math.round(f);
    return R;
  }
*/
}
