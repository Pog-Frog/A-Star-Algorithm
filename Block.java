import java.util.ArrayList;

public class Block {
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
            if (!(x - Panel.SIZE < 0)) {
                Block w = new Block(x - Panel.SIZE, y);
                Block nw = new Block(x - Panel.SIZE, y - Panel.SIZE);
                for (int i = 0; i < Apath.blocks.size(); i++) {
                    if (mrk == 2) {
                        break;
                    }
                    if (Apath.blocks.get(i).isEqual(w)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                    if (Apath.blocks.get(i).isEqual(nw)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                }
            }
            mrk = 0;
            if (!(y + Panel.SIZE < 0)) {
                Block s = new Block(x, y + Panel.SIZE);
                Block se = new Block(x + Panel.SIZE, y + Panel.SIZE);
                Block sw = new Block(x - Panel.SIZE, y + Panel.SIZE);
                for (int i = 0; i < Apath.blocks.size(); i++){
                    if(mrk == 3){
                        break;
                    }
                    if (Apath.blocks.get(i).isEqual(s)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                    if (Apath.blocks.get(i).isEqual(se)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                    if (Apath.blocks.get(i).isEqual(sw)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }

                }
            }
            mrk = 0;
            if(!(x + Panel.SIZE > Panel.WIDTH)){
                Block ne = new Block(x + Panel.SIZE, y - Panel.SIZE);
                Block e = new Block(x + Panel.SIZE, y);
                for (int i = 0; i < Apath.blocks.size(); i++){
                    if (mrk == 2) {
                        break;
                    }
                    if (Apath.blocks.get(i).isEqual(ne)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                    if (Apath.blocks.get(i).isEqual(e)) {
                        neighbours.add(Apath.blocks.get(i));
                        mrk++;
                    }
                }
            }
            if(!(y - Panel.SIZE > Panel.HEIGHT)){
                Block n = new Block(x, y + Panel.SIZE);
                
                for (int i = 0; i < Apath.blocks.size(); i++){
                    if (Apath.blocks.get(i).isEqual(n)) {
                        neighbours.add(Apath.blocks.get(i));
                        break;
                    }
                }
            }
        }
        return this.neighbours;
    }
}
