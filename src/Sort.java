import java.util.ArrayList;
import java.util.Collections;

public class Sort {
    public static ArrayList<Block> Sort(ArrayList<Block> lst){
        if(lst.size() < 2){
            return lst;
        }
        int current_pos = 0;
        for(int i =1;i<lst.size();i++){
            if(lst.get(i).getTot_cost() <= lst.get(0).getTot_cost()){
                current_pos++;
                Collections.swap(lst, i, current_pos);
            }
        }
        Collections.swap(lst, 0, current_pos);
        ArrayList<Block> left = new ArrayList<Block>();
        ArrayList<Block> right = new ArrayList<Block>();
        ArrayList<Block> result = new ArrayList<Block>();
        for(int i =0;i<current_pos;i++){
            left.add(lst.get(i));
        }
        for(int i = current_pos +1 ;i<lst.size();i++){
            right.add(lst.get(i));
        }
        left = Sort(left);
        right = Sort(right);
        result.addAll(left);
        result.add(lst.get(current_pos));
        result.addAll(right);
        return lst;
    }
}
