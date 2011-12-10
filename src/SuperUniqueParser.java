import java.util.Map;


public class SuperUniqueParser extends Parser {
    private int iName, iTC, iTCN, iTCH;

    public SuperUniqueParser(String[] title) {
        super(title);
        iName = index("Name");
        iTC = index("TC");
        iTCN = index("TC(N)");
        iTCH = index("TC(H)");
    }

    public void parse(String[] tokens, Map<String, Monster> nameMap) {
        Monster monster = nameMap.get(tokens[iName]);
        if (monster != null) {
            monster.setTc(tokens[iTC]);
            monster.setTcN(tokens[iTCN]);
            monster.setTcH(tokens[iTCH]);
        }
    }
}
