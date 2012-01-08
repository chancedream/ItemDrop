import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UniqueItemParser extends Parser {
    private static int propertyMaxCount = 12;
    private int iName, iQlvl, iEnabled, iCode, iRarity, iProperty;
    
    public UniqueItemParser(String[] title) {
        super(title);
        iName = index("index");
        iQlvl = index("lvl");
        iEnabled = index("enabled");
        iCode = index("code");
        iRarity = index("rarity");
        iProperty = index("prop1");
    }

    public UniqueItem parse(String[] tokens, Map<String, ItemBase> codeItemBaseMap, 
            Map<String, Property> propertyMap) {
        if (parseInt(tokens[iEnabled]) != 1)
            return null;
        String code = tokens[iCode];
        List<PropertyValue> properties = new ArrayList<PropertyValue>();
        for (int i = 0; i < propertyMaxCount; i++) {
            int propertyIndex = iProperty + 4 * i;
            String propertyCode = tokens[propertyIndex].toLowerCase();
            if (propertyCode.isEmpty() || propertyMap.get(propertyCode) == null) break;
            properties.add(new PropertyValue(propertyMap.get(propertyCode), tokens[propertyIndex + 1], 
                    parseInt(tokens[propertyIndex + 2]), parseInt(tokens[propertyIndex + 3])));
        }
        return new UniqueItem(codeItemBaseMap.get(code), parseInt(tokens[iQlvl]), tokens[iName], properties, 
                parseInt(tokens[iRarity]));
    }
}
