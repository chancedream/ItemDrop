import java.util.Map;


public class UniqueItemParser extends Parser {
    public UniqueItemParser(String[] title) {
        super(title);
        iName = index("index");
        iQlvl = index("lvl");
        iEnabled = index("enabled");
        iCode = index("code");
        iRarity = index("rarity");
    }

    int iName, iQlvl, iEnabled, iCode, iRarity;
    
    public UniqueItem parse(String[] tokens, Map<String, ItemBase> codeItemBaseMap) {
        if (parseInt(tokens[iEnabled]) != 1)
            return null;
        String code = tokens[iCode];
        return new UniqueItem(codeItemBaseMap.get(code), parseInt(tokens[iQlvl]), tokens[iName], parseInt(tokens[iRarity]));
    }
}
