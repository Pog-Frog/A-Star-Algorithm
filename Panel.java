import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Panel extends Canvas implements Runnable , ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
    private static int WIDTH = 800;
    private static int HEIGHT = (int)(WIDTH * 0.75);
    private static int SCALE = 1;
    public static int SIZE = 100;
    public static String TITLE = "A* cooming";
    private Graphics2D g;
    private BufferedImage image;
    private Thread thread;
    private boolean running;
    private long startTime, URDTimeMilli, waitTime;
    private int FPS = 30;
    private Block start_blk , end_blk;

    public Panel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        running = true;
        image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        long targetTime = 1000 / FPS;
        while (running) {
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

    public void update() {

    }

    public void render() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        g.setColor(Color.GRAY);
        for (int j = 0; j < HEIGHT * SCALE; j += SIZE) {
            for (int i = 0; i < WIDTH * SCALE; i += SIZE) {
                g.drawRect(i, j, SIZE, SIZE);
            }
        }

    }

    public void draw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void Key_listener(MouseEvent e, KeyEvent key){
        int keyCode = key.getKeyCode();
        if(SwingUtilities.isLeftMouseButton(e)){
            if(keyCode == KeyEvent.VK_S){
                int mouse_X = e.getX() % SIZE;
                int mouse_Y = e.getY() % SIZE;
                if(start_blk == null){
                    start_blk = new Block(e.getX() - mouse_X , e.getY() - mouse_Y);
                }
            }
        }
    }

    @Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

    public void keyTyped(KeyEvent key) {
    }

    public void keyPressed(KeyEvent key) {
        int ketCode = key.getKeyCode();
        
    }

    public void keyReleased(KeyEvent key) {
        int ketCode = key.getKeyCode();
        

    }
}
