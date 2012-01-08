
public class MonsterParser extends Parser {
    private int iID,iName;
    private int iEnabled;
    private int iLevel;
    private int iLevelN;
    private int iLevelH;
    private int iTC, iTCQ, iTCN, iTCQN, iTCH, iTCQH, iBoss;
    
    public MonsterParser(String[] title) {
        super(title);
        iID = index("Id");
        iName = index("NameStr");
        iEnabled = index("enabled");
        iLevel = index("Level");
        iLevelN = index("Level(N)");
        iLevelH = index("Level(H)");
        iTC = index("TreasureClass1");
        iTCQ = index("TreasureClass4");
        iTCN = index("TreasureClass1(N)");
        iTCQN = index("TreasureClass4(N)");
        iTCH = index("TreasureClass1(H)");
        iTCQH = index("TreasureClass4(H)");
        iBoss = index("boss");
    }
    
    public Monster parse(String[] tokens) {
        if (parseInt(tokens[iEnabled]) != 1) {
            return null;
        }
        return new Monster(tokens[iID], tokens[iName], parseInt(tokens[iLevel]), parseInt(tokens[iLevelN]), 
                parseInt(tokens[iLevelH]), tokens[iTC], tokens[iTCQ], tokens[iTCN], tokens[iTCQN],
                tokens[iTCH], tokens[iTCQH], parseInt(tokens[iBoss])==1);
    }

    
}
