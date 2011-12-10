import java.util.Map;


public class SetItemParser extends Parser {
    public SetItemParser(String[] title) {
        super(title);
        iName = index("index");
        iSet = index("set");
        iQlvl = index("lvl");
        iCode = index("item");
        iRarity = index("rarity");
    }

    private int iName, iSet, iQlvl, iCode, iRarity;
    
    public SetItem parse(String[] tokens, Map<String, ItemBase> codeItemBaseMap) {
        String name = tokens[iName];
        if ("index".equals(name) || "Expansion".equals(name)) {
            return null;
        }
        return new SetItem(codeItemBaseMap.get(tokens[iCode]), parseInt(tokens[iQlvl]), name, parseInt(tokens[iRarity]));
    }
}
