import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SetItemParser extends Parser {
    private static int propertyMaxCount = 9;
    private int iName, iSet, iQlvl, iCode, iRarity, iProperty;
    
    public SetItemParser(String[] title) {
        super(title);
        iName = index("index");
        iSet = index("set");
        iQlvl = index("lvl");
        iCode = index("item");
        iRarity = index("rarity");
        iProperty = index("prop1");
    }
    
    public SetItem parse(String[] tokens, Map<String, ItemBase> codeItemBaseMap, 
            Map<String, Property> propertyMap) {
        String name = tokens[iName];
        if ("index".equals(name) || "Expansion".equals(name)) {
            return null;
        }
        List<PropertyValue> properties = new ArrayList<PropertyValue>();
        for (int i = 0; i < propertyMaxCount; i++) {
            int propertyIndex = iProperty + 4 * i;
            String propertyCode = tokens[propertyIndex].toLowerCase();
            if (propertyCode.isEmpty() || propertyMap.get(propertyCode) == null) break;
            properties.add(new PropertyValue(propertyMap.get(propertyCode), tokens[propertyIndex + 1], 
                    parseInt(tokens[propertyIndex + 2]), parseInt(tokens[propertyIndex + 3])));
        }
        return new SetItem(codeItemBaseMap.get(tokens[iCode]), parseInt(tokens[iQlvl]), name, properties, 
                parseInt(tokens[iRarity]));
    }
}
