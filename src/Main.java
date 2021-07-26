import javax.swing.*;

public class Main{
    public static void main(String[] args){
        Panel panel = new Panel();
        JFrame window = new JFrame(Panel.TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(panel);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}