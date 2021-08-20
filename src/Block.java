import java.util.ArrayList;
import java.util.Collections;

public class Block implements Comparable <Block>{
    private int start_cost = 0, end_cost = 0, tot_cost = 0;
    private int x, y;
    private Block parent = null;
    private int size = Panel.SIZE;
    private ArrayList<Block> neighbours = new ArrayList<Block>();
    private boolean wall = false;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEqual(Block b) {
        return this.x == b.getX() && this.y == b.getY();
    }

    public int getST_cost() {
        return this.start_cost;
    }

    public void setST_cost(int cost) {
        this.start_cost = cost;
    }

    public int getEnd_cost() {
        return this.end_cost;
    }

    public void setEnd_cost(int cost) {
        this.end_cost = cost;
    }

    public int getTot_cost() {
        return this.tot_cost;
    }

    public void setTot_cost(int cost) {
        this.tot_cost = cost;
    }

    public Block getParent() {
        return parent;
    }

    public void setParent(Block parent) {
        this.parent = parent;
    }

    public void set_wall(boolean wall){
        this.wall = wall;
    }

    public boolean check_wall(){
        return this.wall;
    }

    public synchronized ArrayList<Block> get_neighbours() {
        if (this.neighbours.size() < 1) {
            int col = x / size, row = y / size;
            //w
            if(!(col - 1 < 0)){
                if (!((Apath.blocks[row][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row][col - 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row][col - 1].check_wall()))) {
                    if (this.parent != null) {
                        if (!(Apath.blocks[row][col - 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row][col - 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row][col - 1]);
                    }
                }
            }
            // nw
            if (!(row - 1 < 0 || col - 1 < 0)) {
                if (!((Apath.blocks[row - 1][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col - 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row - 1][col - 1].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row - 1][col - 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row - 1][col - 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row - 1][col - 1]);
                    }
            }
            // n
            if (!(row - 1 < 0 && col >= 0)) {
                if (!((Apath.blocks[row - 1][col].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row - 1][col].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row - 1][col].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row - 1][col]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row - 1][col]);
                    }
            }
            // ne
            if (!(row - 1 < 0 || col + 1 > Apath.max_col)) {
                if (!((Apath.blocks[row - 1][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col + 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row - 1][col + 1].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row - 1][col + 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row - 1][col + 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row - 1][col + 1]);
                    }
            }
            // e
            if (!(col + 1 > Apath.max_col)) {
                if (!((Apath.blocks[row][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row][col + 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row][col + 1].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row][col + 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row][col + 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row][col + 1]);
                    }
            }
            // se
            if (!(row + 1 > Apath.max_row || col + 1 > Apath.max_col)) {
                if (!((Apath.blocks[row + 1][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col + 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row + 1][col + 1].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row + 1][col + 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row + 1][col + 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row + 1][col + 1]);
                    }
            }
            // s
            if (!(row + 1 > Apath.max_row)) {
                if (!((Apath.blocks[row + 1][col].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row + 1][col].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row + 1][col].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row + 1][col]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row + 1][col]);
                    }
            }
            // sw
            if (!(col - 1 < 0 || row + 1 > Apath.max_row )) {
                if (!((Apath.blocks[row + 1][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col - 1].isEqual(Apath.end_blk) || this.check_wall() || Apath.blocks[row + 1][col - 1].check_wall())))
                    if (this.parent != null) {
                        if (!(Apath.blocks[row + 1][col - 1].isEqual(this.parent))) {
                            neighbours.add(Apath.blocks[row + 1][col - 1]);
                        }
                    } else {
                        neighbours.add(Apath.blocks[row + 1][col - 1]);
                    }
            }
            int tmp_s, tmp_e;
            for (int j = 0; j < neighbours.size(); j++) {
                int x = neighbours.get(j).getX();
                int y = neighbours.get(j).getY();
                tmp_s = (int) Math.sqrt(Math.pow(Math.abs(x - Apath.start_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.start_blk.getY()), 2));
                tmp_e = (int) Math.sqrt(Math.pow(Math.abs(x - Apath.end_blk.getX()), 2) + Math.pow(Math.abs(y - Apath.end_blk.getY()), 2));
                neighbours.get(j).setST_cost(tmp_s);
                neighbours.get(j).setEnd_cost(tmp_e);
                neighbours.get(j).setTot_cost(tmp_s + tmp_e);
            }
        }
        return this.neighbours;
    }

    public void resetNeighbours(){
        this.neighbours.clear();
    }

    public void setNeighbours(ArrayList<Block> lst){
        Collections.copy(this.neighbours, lst);
    }

    public int compareTo(Block blk) {
        return this.getTot_cost() - blk.getTot_cost();
    }

    public boolean isPath() {
        for (int i = 0; i < Apath.path.size(); i++) {
            if (this.isEqual(Apath.path.get(i)))
                return true;
        }
        return false;
    }

    public int getDistance(Block x, Block y) {
        int distx = Math.abs(x.getX() - y.getX());
        int disty = Math.abs(x.getY() - y.getY());
        if (distx > disty) {
            return (int) (1.4 * Panel.SIZE) * disty + 10 * (distx - disty);
        }
        return (int) (1.4 * Panel.SIZE) * disty - distx;
    }
}
