
public class StatParser extends Parser {
    private int iName, iDescPriority, iDescFunc, iDescVal, iDescStrPos, iDescGroup, iDescGroupFunc, iDescGroupVal, iDescGroupStrPos;

    public StatParser(String[] title) {
        super(title);
        iName = index("Stat");
        iDescPriority = index("descpriority");
        iDescFunc = index("descfunc");
        iDescVal = index("descval");
        iDescStrPos = index("descstrpos");
        iDescGroup = index("dgrp");
        iDescGroupFunc = index("dgrpfunc");
        iDescGroupVal = index("dgrpval");
        iDescGroupStrPos = index("dgrpstrpos");
    }
    
    public Stat parse(String[] tokens) {
        return new Stat(tokens[iName], parseInt(tokens[iDescPriority]), parseInt(tokens[iDescFunc]), 
                parseInt(tokens[iDescVal]), tokens[iDescStrPos], parseInt(tokens[iDescGroup]), 
                parseInt(tokens[iDescGroupFunc]), parseInt(tokens[iDescGroupVal]), tokens[iDescGroupStrPos]);
    }

}
