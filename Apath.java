import java.util.ArrayList;
import java.awt.*;

public class Apath {
    public static ArrayList<Block> walls = new ArrayList<Block>();
    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static Block start_blk, end_blk;
    public static boolean setup = false;
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
        if(start_blk != null){
            g.setColor(Color.GREEN);
            g.fillRect(Apath.start_blk.getX(), Apath.start_blk.getY(), SIZE, SIZE);
        }
        if(end_blk != null){
            g.setColor(Color.BLUE);
            g.fillRect(Apath.end_blk.getX(), Apath.end_blk.getY(), SIZE, SIZE);
        }
    }
}
