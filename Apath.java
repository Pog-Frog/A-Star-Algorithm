import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.*;

import java.awt.*;

public class Apath {
    public static ArrayList<Block> walls = new ArrayList<Block>();
    public static int max_row = (Panel.HEIGHT / Panel.SIZE) - 1, max_col = (Panel.WIDTH / Panel.SIZE) - 1;
    public static Block blocks[][] = new Block[max_row + 1][max_col + 1];
    public static Block start_blk, end_blk, currentBlock;
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
            
            for (int i = 0; i < Apath.start_blk.get_neighbours().size(); i++) {
                drawInfo(Apath.start_blk.get_neighbours().get(i), g);
            }
        }
        if (end_blk != null) {
            g.setColor(Color.yellow);
            g.fillRect(Apath.end_blk.getX(), Apath.end_blk.getY(), SIZE, SIZE);
        }
        if(currentBlock != null){
            g.setColor(Color.MAGENTA);
            drawInfo(Apath.start_blk, g);
            for (int i = 0; i < Apath.start_blk.get_neighbours().size(); i++) {
                drawInfo(Apath.currentBlock.get_neighbours().get(i), g);
            }
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

    public static void findPath() {
        if(Apath.end_blk != null && Apath.start_blk != null){
            for(int i = 0;i<Apath.start_blk.get_neighbours().size();i++){
                int x = Apath.start_blk.get_neighbours().get(i).getX();
                int y = Apath.start_blk.get_neighbours().get(i).getY();
                int tmp_s= (int)Math.sqrt(Math.pow(Math.abs(x - Apath.start_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.start_blk.getY()), 2));
                int tmp_e = (int)Math.sqrt(Math.pow(Math.abs(x - Apath.end_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.end_blk.getY()), 2));
                Apath.start_blk.get_neighbours().get(i).setST_cost(tmp_s);
                Apath.start_blk.get_neighbours().get(i).setEnd_cost(tmp_e);
                Apath.start_blk.get_neighbours().get(i).setTot_cost(tmp_s + tmp_e);
            }
            Apath.start_blk.setNeighbours(Sort.Sort(Apath.start_blk.get_neighbours()));
            Apath.currentBlock = Apath.start_blk.get_neighbours().get(0);
        }
    }

}
