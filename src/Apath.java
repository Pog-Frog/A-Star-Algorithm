import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import java.awt.*;
import java.util.Collections;

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
        for (int i = 0;i < max_row + 1;i++) {
            for(int j = 0 ;j< max_col + 1;j++){
                g.drawRect(blocks[i][j].getX(), blocks[i][j].getY(), SIZE, SIZE);
            }
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < Apath.walls.size(); i++) {
            g.fillRect(Apath.walls.get(i).getX(), Apath.walls.get(i).getY(), SIZE, SIZE);
        }
        if (start_blk != null) {
            drawInfo(start_blk, g, Color.cyan);
        }
        if (end_blk != null) {
            drawInfo(end_blk, g, Color.yellow);
        }
        if(currentBlock != null){
            drawInfo(Apath.currentBlock, g, Color.MAGENTA);
            for (int i = 0; i < Apath.currentBlock.get_neighbours().size(); i++) {
                drawInfo(Apath.currentBlock.get_neighbours().get(i), g, null);
            }
        }
        if(start_blk != null && end_blk != null){
            for (int i = 0; i < Apath.start_blk.get_neighbours().size(); i++) {
                drawInfo(Apath.start_blk.get_neighbours().get(i), g, null);
            }
        }
    }

    public static void drawInfo(Block tmpBlock, Graphics2D g, Color color) {
        if(color != null) {
            g.setColor(color);
            g.fillRect(tmpBlock.getX(), tmpBlock.getY(), SIZE, SIZE);
        }
        if (SIZE > 50 && running ){
            if(!(tmpBlock.isEqual(Apath.start_blk) || tmpBlock.isEqual(Apath.end_blk))){
                g.setColor(Color.black);
                g.setFont(new Font("arial", Font.BOLD, 11));
                g.drawString(Integer.toString(tmpBlock.getST_cost()), tmpBlock.getX() + 4, tmpBlock.getY() + SIZE - 7);
                g.drawString(Integer.toString(tmpBlock.getEnd_cost()), tmpBlock.getX() + SIZE - 26,tmpBlock.getY() + SIZE - 7);
                g.setFont(new Font("arial", Font.BOLD, 24));
                g.drawString(Integer.toString(tmpBlock.getTot_cost()), tmpBlock.getX() + 4, tmpBlock.getY() + 16);
            }
        }
    }

    public static void findPath() {
        if(Apath.end_blk != null && Apath.start_blk != null){
            int tmp_s, tmp_e;
            for(int i = 0;i<Apath.start_blk.get_neighbours().size();i++){
                int x = Apath.start_blk.get_neighbours().get(i).getX();
                int y = Apath.start_blk.get_neighbours().get(i).getY();
                tmp_s= (int)Math.sqrt(Math.pow(Math.abs(x - Apath.start_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.start_blk.getY()), 2));
                tmp_e = (int)Math.sqrt(Math.pow(Math.abs(x - Apath.end_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.end_blk.getY()), 2));
                Apath.start_blk.get_neighbours().get(i).setST_cost(tmp_s);
                Apath.start_blk.get_neighbours().get(i).setEnd_cost(tmp_e);
                Apath.start_blk.get_neighbours().get(i).setTot_cost(tmp_s + tmp_e);
            }
            Collections.sort(Apath.start_blk.get_neighbours(), new Compare());
            Apath.currentBlock = Apath.start_blk.get_neighbours().get(0);
            for(int i = 0;i<Apath.currentBlock.get_neighbours().size();i++){
                int x = Apath.currentBlock.get_neighbours().get(i).getX();
                int y = Apath.currentBlock.get_neighbours().get(i).getY();
                tmp_s= (int)Math.sqrt(Math.pow(Math.abs(x - Apath.start_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.start_blk.getY()), 2));
                tmp_e = (int)Math.sqrt(Math.pow(Math.abs(x - Apath.end_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.end_blk.getY()), 2));
                Apath.currentBlock.get_neighbours().get(i).setST_cost(tmp_s);
                Apath.currentBlock.get_neighbours().get(i).setEnd_cost(tmp_e);
                Apath.currentBlock.get_neighbours().get(i).setTot_cost(tmp_s + tmp_e);
            }
        }
    }

}
