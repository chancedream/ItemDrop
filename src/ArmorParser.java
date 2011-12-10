
public class ArmorParser extends Parser{
    public ArmorParser(String[] title) {
        super(title);
        iName = index("name");
        iQlvl = index("level");
        iCode = index("code");
        iType = index("type");
        iRarity = index("rarity");
    }

    private int iName, iQlvl, iCode, iType, iRarity;
    
    public Armor parse(String[] tokens) {
        if ("name".equals(tokens[iName]) || "Expansion".equals(tokens[iName])) {
            return null;
        }
        int qlvl = parseInt(tokens[iQlvl]);
        return new Armor(tokens[iName], qlvl, tokens[iCode], calcTC(qlvl), tokens[iType], parseInt(tokens[iRarity]));
    }
}
