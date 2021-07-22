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

public class Panel extends Canvas
        implements Runnable, ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
    public static int WIDTH = 800;
    public static int HEIGHT = (int) (WIDTH * 0.75);
    public static int SCALE = 1;
    public static int SIZE = 100;
    public static String TITLE = "A* DEMO";
    private Graphics2D g;
    private BufferedImage image;
    private Thread thread;
    private boolean running;
    private long startTime, URDTimeMilli, waitTime;
    private int FPS = 30;
    private MouseEvent e;
    private KeyEvent k;

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
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
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
        if(Apath.running){
            Apath.findPath();
        }
    }

    public void render() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        g.setColor(Color.GRAY);
        if (Apath.setup) {
            Apath.draw(g);
        } else {
            for (int j = 0; j < HEIGHT * SCALE; j += SIZE) {
                for (int i = 0; i < WIDTH * SCALE; i += SIZE) {
                    g.drawRect(j, i, SIZE, SIZE);
                    Apath.blocks[(int)j / SIZE][(int)i / SIZE] = new Block(i, j);
                }
            }
            Apath.setup = true;
        }

    }

    public void draw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void Key_listener(MouseEvent e, KeyEvent k) {
        try{
            int keyCode = k.getKeyCode();
            if (SwingUtilities.isLeftMouseButton(e)) {
                int mouse_X = e.getX() % SIZE;
                int mouse_Y = e.getY() % SIZE;
                if (keyCode == KeyEvent.VK_S) {
                    if(Apath.end_blk == null || !(Apath.end_blk.getX() == (e.getX() - mouse_X) && Apath.end_blk.getY() == (e.getY() - mouse_Y))){
                        if (Apath.start_blk == null) {
                            Apath.start_blk = new Block(e.getX() - mouse_X, e.getY() - mouse_Y);
                        } else {
                            Apath.start_blk.setX(e.getX() - mouse_X);
                            Apath.start_blk.setY(e.getY() - mouse_Y);
                            Apath.start_blk.resetNeighbours();
                        }
                        this.k = null;
                        this.e = null;
                    }
                } else if (keyCode == KeyEvent.VK_E) {
                    if(Apath.start_blk == null || !(Apath.start_blk.getX() == (e.getX() - mouse_X) && Apath.start_blk.getY() == (e.getY() - mouse_Y))){
                        if (Apath.end_blk == null) {
                            Apath.end_blk = new Block(e.getX() - mouse_X, e.getY() - mouse_Y);
                        } else {
                            Apath.end_blk.setX(e.getX() - mouse_X);
                            Apath.end_blk.setY(e.getY() - mouse_Y);
                            Apath.end_blk.resetNeighbours();
                        }
                        this.k = null;
                        this.e = null;
                    }
                } else if (keyCode == KeyEvent.VK_C) {
                    int wallX = e.getX() - (e.getX() % SIZE);
                    int WallY = e.getY() - (e.getY() % SIZE);
                    Block tmp = new Block(wallX, WallY);
                    if(!(tmp.equals(Apath.start_blk) || tmp.equals(Apath.end_blk))){
                        Apath.walls.add(tmp);
                        this.k = null;
                        this.e = null;
                    }
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                int mouse_X = e.getX() - (e.getX() % SIZE);
                int mouse_Y = e.getY() - (e.getY() % SIZE);
                if (keyCode == KeyEvent.VK_S) {
                    if (Apath.start_blk != null && !Apath.running && mouse_X == Apath.start_blk.getX() && Apath.start_blk.getY() == mouse_Y) {
                        Apath.start_blk = null;
                        Apath.currentBlock = null;
                    }
                } else if (keyCode == KeyEvent.VK_E) {
                    if (Apath.end_blk != null && !Apath.running && mouse_X == Apath.end_blk.getX() && Apath.end_blk.getY() == mouse_Y) {
                        Apath.end_blk = null;
                        Apath.currentBlock = null;
                    }
                } else if (keyCode == KeyEvent.VK_C) {
                    for (int i = 0; i < Apath.walls.size(); i++) {
                        if (Apath.walls.get(i).getX() == mouse_X && Apath.walls.get(i).getY() == mouse_Y) {
                            Apath.walls.remove(i);
                            break;
                        }
                    }
                }
            }
        }catch (NullPointerException ignored){

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.e = e;
        Key_listener(this.e, k);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.e = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.e = e;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int height = this.getHeight();
    }

    @Override
    public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {
        if(key.getKeyCode() == KeyEvent.VK_SPACE){
            if(!Apath.running){
                Apath.running = true;
            }
            else{
                Apath.running = false;
            }
        }
        else{
            this.k = key;
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
        this.k = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent m) {
    }
}
