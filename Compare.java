import java.util.Comparator;

public class Compare implements Comparator <Block> {
    public int compare (Block blk1, Block blk2){
        return blk1.compareTo(blk2);
    }
}
