import java.util.ArrayList;
import java.util.List;


public class TCParser extends Parser{
    public TCParser(String[] title) {
        super(title);
    }

    public static int iName = 0;
    public static int iGroup = 1;
    public static int iLevel = 2;
    public static int iPicks = 3;
    public static int iUnique = 4;
    public static int iSet = 5;
    public static int iRare = 6;
    public static int iMagical = 7;
    public static int iNoDrop = 8;
    public static int iItem = 9;
    
    public static TC parse(String[] tokens) {
        List<String> itemList = new ArrayList<String>();
        List<Integer> probList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            int index = iItem + 2 * i;
            String item = tokens[index];
            if (item.isEmpty()) {
                break;
            }
            itemList.add(item);
            probList.add(parseInt(tokens[index + 1]));
        }
        return new TC(tokens[iName], parseInt(tokens[iGroup]), parseInt(tokens[iLevel]), parseInt(tokens[iPicks]),
                parseInt(tokens[iUnique]), parseInt(tokens[iSet]), parseInt(tokens[iRare]), parseInt(tokens[iMagical]),
                parseInt(tokens[iNoDrop]), itemList.toArray(new String[0]), probList.toArray(new Integer[0]));
        
    }
}
