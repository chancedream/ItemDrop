
public class WeaponParser extends Parser {
    public WeaponParser(String[] title) {
        super(title);
        iName = index("name");
        iQlvl = index("level");
        iCode = index("code");
        iType = index("type");
        iRarity = index("rarity");
    }

    private int iName, iQlvl, iCode, iType, iRarity;
    
    public Weapon parse(String[] tokens) {
        if ("name".equals(tokens[iName]) || "Expansion".equals(tokens[iName])) {
            return null;
        }
        int qlvl = parseInt(tokens[iQlvl]);
        return new Weapon(tokens[iName], qlvl, tokens[iCode], calcTC(qlvl), tokens[iType], parseInt(tokens[iRarity]));
    }
}
