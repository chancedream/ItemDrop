import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PropertyParser extends Parser {
    private int iCode, iStatBase, iValBase, iFuncBase, iDone;
    private static int STAT_STEP = 4;
    private static int STAT_COUNT = 7;

    public PropertyParser(String[] title) {
        super(title);
        iCode = index("code");
        iStatBase = index("stat1");
        iValBase = index("val1");
        iFuncBase = index("func1");
        iDone = index("*done");
    }
    
    public Property parse(String[] tokens, Map<String, Stat> statMap) {
        if (parseInt(tokens[iDone]) != 1)
            return null;
        List<Stat> statList = new ArrayList<Stat>();
        for (int i = 0; i < STAT_COUNT; i++) {
            Stat stat = statMap.get(tokens[iStatBase + i * STAT_STEP]);
            if (stat != null) {
                statList.add(stat);
            }
        }
        int arrayLength = Math.max(statList.size(), 1);
        int[] vals = new int[arrayLength];
        int[] funcs = new int[arrayLength];
        for (int i = 0; i < funcs.length; i++) {
            vals[i] = parseInt(tokens[iValBase + i * STAT_STEP]);
            funcs[i] = parseInt(tokens[iFuncBase + i * STAT_STEP]);
        }
        return new Property(tokens[iCode], statList.toArray(new Stat[0]), vals, funcs);
    }

}
