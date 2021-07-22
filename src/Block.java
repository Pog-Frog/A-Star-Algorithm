import java.util.ArrayList;
import java.util.Collections;

public class Block implements Comparable <Block>{
    private int start_cost = 0, end_cost = 0, tot_cost = 0;
    private int x, y;
    private Block parent = null;
    private int size = Panel.SIZE;
    private ArrayList<Block> neighbours = new ArrayList<Block>();

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
        if (this.x == b.getX() && this.y == b.getY()) {
            return true;
        }
        return false;
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

    public ArrayList<Block> get_neighbours() {
        if (this.neighbours.size() < 1) {
            int mrk = 0;
            int col = (int) x / size, row = (int) y / size;
            //w
            if(!(col - 1 < 0)){
                if(!((Apath.blocks[row][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row][col - 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row][col - 1]);
            }
            // nw
            if (!(row - 1 < 0 || col - 1 < 0)) {
                if(!((Apath.blocks[row - 1][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col - 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row - 1][col - 1]);
            }
            // n
            if (!(row - 1 < 0 && col >= 0)) {
                if(!((Apath.blocks[row - 1][col].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row - 1][col]);
            }
            // ne
            if (!(row - 1 < 0 || col + 1 > Apath.max_col)) {
                if(!((Apath.blocks[row - 1][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row - 1][col + 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row - 1][col + 1]);
            }
            // e
            if (!(col + 1 > Apath.max_col)) {
                if(!((Apath.blocks[row][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row][col + 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row][col + 1]);
            }
            // se
            if (!(row + 1 > Apath.max_row || col + 1 > Apath.max_col)) {
                if(!((Apath.blocks[row + 1][col + 1].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col + 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row + 1][col + 1]);
            }
            // s
            if (!(row + 1 > Apath.max_row)) {
                if(!((Apath.blocks[row + 1][col].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row + 1][col]);
            }
            // sw
            if (!(col - 1 < 0 || row + 1 > Apath.max_row )) {
                if(!((Apath.blocks[row + 1][col - 1].isEqual(Apath.start_blk) || Apath.blocks[row + 1][col - 1].isEqual(Apath.end_blk))))
                neighbours.add(Apath.blocks[row + 1][col - 1]);
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
}
