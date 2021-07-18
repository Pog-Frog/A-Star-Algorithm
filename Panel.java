import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;

public class Panel extends Canvas implements Runnable{
    private static int WIDTH = 800;
    private static int HEIGHT = WIDTH / 12 * 9;
    private static int SCALE = 1;
    public static String TITLE  = "A* cooming";
    private Graphics2D g;
    private BufferedImage image;
    private Thread thread;
    private boolean running;
    private long startTime, URDTimeMilli, waitTime;
    private int FPS = 30;

    public Panel(){
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run(){
        running = true;
        image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
        g= (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        long targetTime = 1000 / FPS;
        while(running){
            startTime = System.nanoTime();
            update();
            render();
            draw();
            URDTimeMilli = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - URDTimeMilli;
            try {
                Thread.sleep(Math.abs(waitTime));
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }

    public void update(){

    }

    public void render(){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        String s = "Faggot";
        int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, WIDTH / 2 - length / 2, HEIGHT / 2);
    }

    public void draw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}
