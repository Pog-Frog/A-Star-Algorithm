import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Apath {
    public static ArrayList<Block> walls = new ArrayList<Block>();
    public static ArrayList<Block> path = new ArrayList<Block>();
    public static int max_row = (Panel.HEIGHT / Panel.SIZE) - 1, max_col = (Panel.WIDTH / Panel.SIZE) - 1;
    public static Block[][] blocks = new Block[max_row + 1][max_col + 1];
    public static Block start_blk, end_blk, currentBlock;
    public static boolean setup = false;
    public static boolean running = false;
    private static int SIZE = Panel.SIZE;

    public static void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < max_row + 1; i++) {
            for (int j = 0; j < max_col + 1; j++) {
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
            currentBlock.resetNeighbours();
            for (int i = 0; i < Apath.currentBlock.get_neighbours().size(); i++) {
                drawInfo(Apath.currentBlock.get_neighbours().get(i), g, null);
            }
            if (path.size() > 0) {
                for (int i = 0; i < path.size(); i++) {
                    if (i == path.size() - 1) {
                        drawInfo(path.get(i), g, Color.lightGray);
                    } else {
                        drawInfo(path.get(i), g, Color.green);
                    }
                    for (int j = 0; j < path.get(i).get_neighbours().size(); j++) {
                        drawInfo(path.get(i).get_neighbours().get(j), g, Color.pink);
                    }
                }
            }
        }
        if(start_blk != null && end_blk != null){
            for (int i = 0; i < Apath.start_blk.get_neighbours().size(); i++) {
                drawInfo(Apath.start_blk.get_neighbours().get(i), g, null);
            }
        }

    }

    public static Boolean is_wall(Block blk) {
        for (int i = 0; i < walls.size(); i++) {
            if (blk.isEqual(walls.get(i))) {
                return true;
            }
        }
        return false;
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
        path.clear();
        if (!(currentBlock == null)) {
            currentBlock = null;
        }
        if (Apath.end_blk != null && Apath.start_blk != null && running) {
            Apath.start_blk.resetNeighbours();
            Apath.end_blk.resetNeighbours();
            Collections.sort(Apath.start_blk.get_neighbours(), new Compare());
            Apath.currentBlock = Apath.start_blk.get_neighbours().get(0);
            if (!(currentBlock.getEnd_cost() == SIZE || currentBlock.getEnd_cost() == (int) (Math.sqrt(2) * SIZE))) {
                currentBlock.resetNeighbours();
                Collections.sort(Apath.currentBlock.get_neighbours(), new Compare());
                path.add(Apath.currentBlock.get_neighbours().get(0));
                int i = 0;
                while (!(path.get(i).getEnd_cost() == SIZE || path.get(i).get_neighbours().size() == 0 || path.get(i).getEnd_cost() == (int) (Math.sqrt(2) * SIZE))) {
                    path.get(i).resetNeighbours();
                    Collections.sort(path.get(i).get_neighbours(), new Compare());
                    for (int j = 0; j < path.get(i).get_neighbours().size(); j++) {
                        for (int k = 0; k < path.size(); k++) {
                            if (path.get(i).get_neighbours().size() < j + 1)
                                break;
                            if (path.get(i).get_neighbours().get(j).isEqual(path.get(k)) || path.get(i).get_neighbours().get(j).isEqual(currentBlock)) {
                                path.get(i).get_neighbours().remove(j);
                            }
                        }
                    }
                    if (path.get(i).get_neighbours().size() > 1) {
                        if (path.get(i).get_neighbours().get(0).getTot_cost() == path.get(i).get_neighbours().get(1).getTot_cost()) {
                            if (path.get(i).get_neighbours().get(0).getEnd_cost() > path.get(i).get_neighbours().get(1).getEnd_cost()) {
                                Collections.swap(path.get(i).get_neighbours(), 0, 1);
                            }
                        }
                    }
                    if (!(path.get(i).get_neighbours().size() == 0)) {
                        path.get(i).get_neighbours().get(0).setParent(path.get(i));
                    }
                    path.add(path.get(i).get_neighbours().get(0));
                    System.out.println(path.size());
                    if (!(path.get(i).get_neighbours().size() == 0))
                        i++;
                }
            }

        } else {
            System.out.println("\n Error: A start point and an end point must be set before starting the program");
        }
    }

}
