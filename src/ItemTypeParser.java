
public class ItemTypeParser extends Parser {
    private int iName, iCode, iEquiv1, iMagic, iRare, iNormal;

    public ItemTypeParser(String[] title) {
        super(title);
        iName = index("ItemType");
        iCode = index("Code");
        iEquiv1 = index("Equiv1");
        iMagic = index("Magic");
        iRare = index("Rare");
        iNormal = index("Normal");
    }
    
    public ItemType parse(String[] tokens) {
        return new ItemType(tokens[iName], tokens[iCode], tokens[iEquiv1], parseBoolean(tokens[iMagic]),
                parseBoolean(tokens[iRare]), parseBoolean(tokens[iNormal]));
                
    }

}
