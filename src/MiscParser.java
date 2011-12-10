
public class MiscParser extends Parser {
    public MiscParser(String[] title) {
        super(title);
        iName = index("name");
        iQlvl = index("level");
        iCode = index("code");
        iType = index("type");
        iRarity = index("rarity");
    }

    private int iName, iQlvl, iCode, iType, iRarity;
    
    public Misc parse(String[] tokens) {
        return new Misc(tokens[iName], parseInt(tokens[iQlvl]), tokens[iCode], tokens[iType], parseInt(tokens[iRarity]));
    }
}
