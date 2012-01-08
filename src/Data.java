import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author chance
 *
 */
public class Data {
    private Map<String, TC> tcMap;
    private Map<Integer, List<TC>> tcGroupMap;
    private Map<String, List<EquipBase>> tcEquipBaseMap;
    private Map<String, ItemBase> codeItemBaseMap;
    private Map<String, List<Item>> uniqueItemMap;
    private Map<String, List<Item>> setItemMap;
    private Map<String, Monster> monsterIdMap;
    private Map<String, Monster> monsterNameMap;
    private Map<String, Integer[]> uniqueQlvlIndexMap;
    private Map<String, Integer[]> setQlvlIndexMap;
    private Map<String, ItemType> itemTypeMap;
    private Map<String, Stat> statMap;
    private Map<String, Property> propertyMap;
    private Map<String, Skill> skillMap;
    
    private static Data instance;
    
    public static void main(String[] args) {
        System.out.println(Data.getInstance().codeItemBaseMap.size());
        System.out.println(Data.getInstance().setItemMap.get("ucl").get(0).generateItem());
    }
    
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    
    private Data() {
        tcMap = new HashMap<String, TC>();
        tcGroupMap = new HashMap<Integer, List<TC>>();
        DataReader reader = new DataReader("data/TreasureClassEx.txt");
        String[] tokens;
        while((tokens = reader.readLine()) != null) {
            TC tc = TCParser.parse(tokens);
            tcMap.put(tc.getName(), tc);
            int group = tc.getGroup();
            if (group > 0) {
                List<TC> tcList = tcGroupMap.get(group);
                if (tcList == null) {
                    tcList = new ArrayList<TC>();
                    tcGroupMap.put(group, tcList);
                }
                tcList.add(tc);
            }
            //System.out.println(tc);
        }
        tcEquipBaseMap = new HashMap<String, List<EquipBase>>();
        codeItemBaseMap = new HashMap<String, ItemBase>();
        reader = new DataReader("data/armor.txt");
        ArmorParser armorParser = new ArmorParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            EquipBase base = armorParser.parse(tokens);
            if (base != null) {
                codeItemBaseMap.put(base.getCode(), base);
                putEquipBase(tcEquipBaseMap, base, TC.ARMOR);
                //System.out.println(base);
            }
        }
        reader = new DataReader("data/weapons.txt");
        WeaponParser weaponParser = new WeaponParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            EquipBase base = weaponParser.parse(tokens);
            if (base != null) {
                codeItemBaseMap.put(base.getCode(), base);
                if (!base.getType().equals("tpot")) {
                    putEquipBase(tcEquipBaseMap, base, base.getType().contains("bow") ? TC.BOW : TC.MELEE);
                    putEquipBase(tcEquipBaseMap, base, TC.WEAPON);
                }
                //System.out.println(base);
            }
        }
        reader = new DataReader("data/misc.txt");
        MiscParser miscParser = new MiscParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Misc misc = miscParser.parse(tokens);
            codeItemBaseMap.put(misc.getCode(), misc);
            //System.out.println(misc);
        }
        skillMap = new HashMap<String, Skill>();
        reader = new DataReader("data/skills.txt");
        SkillParser skillParser = new SkillParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Skill skill = skillParser.parse(tokens);
            skillMap.put(skill.getName(), skill);
        }
        statMap = new HashMap<String, Stat>();
        reader = new DataReader("data/ItemStatCost.txt");
        StatParser statParser = new StatParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Stat stat = statParser.parse(tokens);
            statMap.put(stat.getName(), stat);
        }
        propertyMap = new HashMap<String, Property>();
        reader = new DataReader("data/Properties.txt");
        PropertyParser propertyParser = new PropertyParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Property property = propertyParser.parse(tokens, statMap);
            if (property != null) {
                propertyMap.put(property.getCode(), property);
            }
        }
        uniqueItemMap = new HashMap<String, List<Item>>();
        reader = new DataReader("data/UniqueItems.txt");
        UniqueItemParser uniqueItemParser = new UniqueItemParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Item item = uniqueItemParser.parse(tokens, codeItemBaseMap, propertyMap);
            if (item != null) {
                putUniqueSetItem(uniqueItemMap, item);
            }
        }
        uniqueQlvlIndexMap = new HashMap<String, Integer[]>();
        indexQlvl(uniqueItemMap, uniqueQlvlIndexMap);
        setItemMap = new HashMap<String, List<Item>>();
        reader = new DataReader("data/SetItems.txt");
        SetItemParser setItemParser = new SetItemParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Item item = setItemParser.parse(tokens, codeItemBaseMap, propertyMap);
            if (item != null) {
                putUniqueSetItem(setItemMap, item);
            }
        }
        setQlvlIndexMap = new HashMap<String, Integer[]>();
        indexQlvl(setItemMap, setQlvlIndexMap);
        monsterIdMap = new HashMap<String, Monster>();
        monsterNameMap = new HashMap<String, Monster>();
        reader = new DataReader("data/monstats.txt");
        MonsterParser monsterParser = new MonsterParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            Monster monster = monsterParser.parse(tokens);
            if (monster != null) {
                monsterIdMap.put(monster.getId(), monster);
                monsterNameMap.put(monster.getName(), monster);
                //System.out.println(monster);
            }
        }
        reader = new DataReader("data/SuperUniques.txt");
        SuperUniqueParser suParser = new SuperUniqueParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            suParser.parse(tokens, monsterNameMap);
        }
        itemTypeMap = new HashMap<String, ItemType>();
        reader = new DataReader("data/ItemTypes.txt");
        ItemTypeParser itemTypeParser = new ItemTypeParser(reader.readLine());
        while((tokens = reader.readLine()) != null) {
            ItemType itemType = itemTypeParser.parse(tokens);
            itemTypeMap.put(itemType.getCode(), itemType);
        }
    }
    
    private void indexQlvl(Map<String, List<Item>> itemMap, Map<String, Integer[]> indexMap) {
        for (List<Item> list : itemMap.values()) {
            if (list.size() > 1) {
                Collections.sort(list, new QlvlComparator());
                List<Item> newList = new ArrayList<Item>();
                int lastQlvl = 0;
                int lastIndex = -1;
                Integer[] qlvlIndexArray = new Integer[100];
                for (Item item : list) {
                    int rarity = item instanceof UniqueItem ? ((UniqueItem)item).getRarity() : ((SetItem)item).getRarity();
                    for (int i = 0; i < rarity; i++) {
                        newList.add(item);
                    }
                    for (int i = lastQlvl; i < item.getQlvl(); i++ ) {
                        qlvlIndexArray[i] = lastIndex;
                    }
                    lastQlvl = item.getQlvl();
                    lastIndex += rarity;
                }
                for (int i = lastQlvl; i < qlvlIndexArray.length; i++) {
                    qlvlIndexArray[i] = lastIndex;
                }
                String code = list.get(0).getBase().getCode(); 
                itemMap.put(code, newList);
                indexMap.put(code, qlvlIndexArray);
            }
        }
    }
    
    private void putEquipBase(Map<String, List<EquipBase>> map, EquipBase base, String type) {
        String tcName = type + base.getTC();
        List<EquipBase> list = map.get(tcName);
        if (list == null) {
            list = new ArrayList<EquipBase>();
            map.put(tcName, list);
        }
        int count = 3;
        if ("h2h".equals(base.getType())) {
            count = 2;
        }
        else if (base.isClassic() || base.getType().matches("wand|staf|scep")) {
            count = 1;
        }
        for (int i = 0; i < count; i++) {
            list.add(base);
        }
    }
    
    private void putUniqueSetItem(Map<String, List<Item>> map, Item item) {
        List<Item> list = map.get(item.getBase().getCode());
        if (list == null) {
            list = new ArrayList<Item>();
            map.put(item.getBase().getCode(), list);
        }
        list.add(item);
        //System.out.println(item);
    }
    
    private class DataReader {
        private BufferedReader reader;
        
        public DataReader(String filePath) {
            try {
                reader = new BufferedReader(new FileReader(filePath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        public String[] readLine() {
            String line;
            try {
                line = reader.readLine();
                if (line != null)
                    return line.split("\\t", -1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Map<String, TC> getTcMap() {
        return tcMap;
    }

    public Map<String, List<EquipBase>> getTcEquipBaseMap() {
        return tcEquipBaseMap;
    }

    public Map<String, ItemBase> getCodeItemBaseMap() {
        return codeItemBaseMap;
    }

    public Map<String, List<Item>> getUniqueItemMap() {
        return uniqueItemMap;
    }

    public Map<String, List<Item>> getSetItemMap() {
        return setItemMap;
    }

    public Map<String, Monster> getMonsterIdMap() {
        return monsterIdMap;
    }
    
    public Map<String, Monster> getMonsterNameMap() {
        return monsterNameMap;
    }

    public Map<String, Integer[]> getUniqueQlvlIndexMap() {
        return uniqueQlvlIndexMap;
    }

    public Map<String, Integer[]> getSetQlvlIndexMap() {
        return setQlvlIndexMap;
    }

    public Map<String, ItemType> getItemTypeMap() {
        return itemTypeMap;
    }

    public Map<Integer, List<TC>> getTcGroupMap() {
        return tcGroupMap;
    }

    public Map<String, Stat> getStatMap() {
        return statMap;
    }

    public Map<String, Property> getPropertyMap() {
        return propertyMap;
    }

    public Map<String, Skill> getSkillMap() {
        return skillMap;
    }
    
    
}
