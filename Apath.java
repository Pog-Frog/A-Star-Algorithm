import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;

public class Apath {
    public static ArrayList<Block> walls = new ArrayList<Block>();
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static Block start_blk, end_blk;
    public static boolean setup = false;
    public static boolean running = false;
    private static int SIZE = Panel.SIZE;

    public static void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        for (int j = 0; j < Panel.HEIGHT * Panel.SCALE; j += SIZE) {
            for (int i = 0; i < Panel.WIDTH * Panel.SCALE; i += SIZE) {
                g.drawRect(i, j, SIZE, SIZE);
            }
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < Apath.walls.size(); i++) {
            g.fillRect(Apath.walls.get(i).getX(), Apath.walls.get(i).getY(), SIZE, SIZE);
        }
        if (start_blk != null) {
            g.setColor(Color.CYAN);
            g.fillRect(Apath.start_blk.getX(), Apath.start_blk.getY(), SIZE, SIZE);
            drawInfo(Apath.start_blk, g);
            for(int i =0;i<Apath.start_blk.get_neighbours().size();i++){
                drawInfo(Apath.start_blk.get_neighbours().get(i), g);
            }
        }
        if (end_blk != null) {
            g.setColor(Color.yellow);
            g.fillRect(Apath.end_blk.getX(), Apath.end_blk.getY(), SIZE, SIZE);
            drawInfo(Apath.end_blk, g);
        }
    }

    public static void drawInfo(Block tmpBlock, Graphics2D g) {
        if (SIZE > 50 && running == true) {
            g.setColor(Color.black);
            g.setFont(new Font("arial", Font.BOLD, 11));
            g.drawString(Integer.toString(tmpBlock.getST_cost()), tmpBlock.getX() + 4, tmpBlock.getY() + SIZE - 7);
            g.drawString(Integer.toString(tmpBlock.getEnd_cost()), tmpBlock.getX() + SIZE - 26,
                    tmpBlock.getY() + SIZE - 7);
            g.setFont(new Font("arial", Font.BOLD, 24));
            g.drawString(Integer.toString(tmpBlock.getTot_cost()), tmpBlock.getX() + 4, tmpBlock.getY() + 16);
        }
    }

    public void findPath(Block tmp) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }

            }
        }
    }
}
