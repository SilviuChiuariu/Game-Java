package main;
import javax.swing.*;
//in UI in draw metod
//in AssetSetter in setObj
//in ColiziuneFrate in checkObj


public class Main {
    public static JFrame window;
    
    public static void main(String[] args) {
        window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //inchidere joc
        window.setResizable(false);
        window.setTitle("Gabur si Elixirul Vietii");
        //window.setUndecorated(true);

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();              //pentru obiecte
        gamePanel.startGameThread();
    }
}