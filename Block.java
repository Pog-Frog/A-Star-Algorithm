import java.util.ArrayList;

public class Block {
    private int start_cost, min_cost;
    private int x ,y;
    private Block parent;
    private int size = Panel.SIZE;
    private ArrayList <Block> neighbours = new ArrayList<Block>();

    public Block(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getST_cost(){
        return this.start_cost;
    }

    public void setST_cost(int cost){
        this.start_cost = cost;
    }

    public int getMIN_cost(){
        return this.min_cost;
    }

    public void setMIN_cost(int cost){
        this.min_cost = cost;
    }

    public Block getParent(){
        return parent;
    }

    public void setParent(Block parent){
        this.parent = parent;
    }

    
}
