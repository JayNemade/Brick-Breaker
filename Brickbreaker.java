import javax.swing.*;
public class Brickbreaker{
  public static void main(String[]args){
     JFrame obj = new JFrame();
     gameplay gameplay = new gameplay();
    
    obj.setBounds(10,10,700,600);
    obj.setTitle("Brick Breaker");
    obj.add(gameplay);
    obj.setResizable(false);
    obj.setVisible(true);
    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
