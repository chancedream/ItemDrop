import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Item {
    protected ItemBase base;
    protected Quality quality;
    protected int qlvl;
    protected String name;
    protected boolean identified;
    protected List<PropertyValue> propertyValues;
    
    public Item(ItemBase base, Quality quality, int qlvl, String name, List<PropertyValue> propertyValues) {
        super();
        this.base = base;
        this.quality = quality;
        this.qlvl = qlvl;
        this.name = name;
        this.propertyValues = propertyValues;
    }
    
    public Item generateItem() {
        return new Item(base, quality, qlvl, name, generateProperties());
    }

    public ItemBase getBase() {
        return base;
    }

    public void setBase(ItemBase base) {
        this.base = base;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public int getQlvl() {
        return qlvl;
    }

    public void setQlvl(int qlvl) {
        this.qlvl = qlvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setProperties(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getShowName() {
        if (quality == Quality.UNIQUE || quality == Quality.SET)
            return TblParser.getInstence().getString(name);
        return TblParser.getInstence().getString(base.getCode());
    }
    
    public boolean needIdentify() {
        return quality == Quality.UNIQUE || quality == Quality.SET || quality == Quality.RARE 
            || quality == Quality.MAGICAL;
    }
    
    public void identify() {
        identified = true;
    }
    
    public boolean isIdentified() {
        return identified;
    }

    @Override
    public String toString() {
        TblParser tbl = TblParser.getInstence();
        StringBuffer sb = new StringBuffer(String.format("name=%s, qlvl=%d, quality=%s, base=%s", getShowName(), qlvl, quality, base));
        if (propertyValues != null) {
            int minDam = -1;
            int maxDam = -1;
            for (PropertyValue propertyValue : propertyValues) {
                Stat[] stats = propertyValue.getProperty().getStats();
                int[] funcs =propertyValue.getProperty().getFuncs();
                if (stats.length == 0) {
                    if (funcs.length == 1) {
                        switch (funcs[0]) {
                        case 5:
                            minDam = propertyValue.getValue();
                            break;
                        case 6:
                            maxDam = propertyValue.getValue();
                            break;
                        case 7:
                            sb.append(String.format("\n+%d%% %s", propertyValue.getValue(), tbl.getString("strModEnhancedDamage")));
                            break;
                        case 20:
                            sb.append(String.format("\n%s", tbl.getString(Data.getInstance().getStatMap().get("item_indesctructible").getDescStrPos())));
                            break;
                        case 23:
                            sb.append(String.format("\n%s", tbl.getStringByNum(10565)));
                            break;
                        }
                    }
                }
                else if (stats.length == 1) {
                    int func = stats[0].getDescFunc();
                    String pos = stats[0].getDescStrPos();
                    if (pos.isEmpty()) {
                        switch (propertyValue.getProperty().getFuncs()[0]) {
                        case 14:
                            pos = "ModStre8c";
                            sb.append(String.format("\n%s(%d)", tbl.getString(pos).trim(), propertyValue.getValue()));
                            break;
                            
                        }
                    }
                    else {
                        switch (propertyValue.getProperty().getFuncs()[0]) {
                        case 21:
                            switch(propertyValue.getProperty().getVals()[0]) {
                            case 3:
                                pos = "ModStr3b";
                                break;
                            case 2:
                                pos = "ModStr3c";
                                break;
                            case 1:
                                pos = "ModStr3d";
                                break;
                            case 4:
                                pos = "ModStr3e";
                                break;
                            case 5:
                                pos = "ModStre8a";
                                break;
                            case 6:
                                pos = "ModStre8b";
                                break;
                            }
                            break;
                        }
                        String desc = tbl.getString(pos);
                        String numberFormat = "%+d";
                        switch (func) {
                        case 1:
                            numberFormat = "%+d";
                            break;
                        case 2:
                            numberFormat = "%d%%";
                            break;
                        case 3:
                            numberFormat = "%d";
                            break;
                        case 4:
                            numberFormat = "%+d%%";
                        }
                        switch (stats[0].getDescVal()) {
                        case 0:
                            switch (func) {
                            case 3:
                            case 11:
                                sb.append("\n" +desc);
                                break;
                            case 28:
                                sb.append(String.format("\n+%d %s", propertyValue.getValue(), getSkillName(propertyValue)));
                                break;
                            case 14:
                                sb.append(String.format("\n" + tbl.getString(pos.substring(0, pos.length()-1) + propertyValue.getParam()), propertyValue.getValue()));
                                break;
                            case 15:
                                sb.append(String.format("\n" + tbl.getString(stats[0].getDescStrPos()), 
                                        propertyValue.getMin(), propertyValue.getMax(), getSkillName(propertyValue)));
                                break;
                            case 24:
                                sb.append(String.format("%s %d %s " + tbl.getString(stats[0].getDescStrPos()), 
                                        tbl.getString("ModStre10b"), propertyValue.getMax(), getSkillName(propertyValue),
                                        propertyValue.getMin(), propertyValue.getMin()));
                                break;
                            default:
                                sb.append("\n" +desc);
                            }
                            break;
                        case 2:
                            sb.append(String.format("\n%s " + numberFormat, desc, propertyValue.getValue()));
                            break;
                        case 1:
                        default:
                            sb.append(String.format("\n" + numberFormat + " %s", propertyValue.getValue(), desc));
                        }
                    }
                }
                else if (stats[0].getDescGroup() != 0) {
                    switch (stats[0].getDescGroupFunc()) {
                    case 19:
                        sb.append(String.format("\n" + tbl.getString(stats[0].getDescGroupStrPos()).trim(), propertyValue.getValue()));
                        break;
                    default:
                        sb.append(String.format("\n+%d %s", propertyValue.getValue(), tbl.getString(stats[0].getDescGroupStrPos())));
                    }
                }
                else if (funcs[0] == 15) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("dmg-fire", "Fire");
                    map.put("dmg-ltng", "Lightning");
                    map.put("dmg-mag", "Magic");
                    map.put("dmg-cold", "Cold");
                    map.put("dmg-pois", "Poison");
                    map.put("dmg-throw", "Throw");
                    map.put("dmg-norm", "Min");
                    map.put("dmg-elem", "");
                    String type = map.get(propertyValue.getProperty().getCode());
                    String key = "strMod" + type + "Damage";
                    String desc = null;
                    if (propertyValue.getMin() < propertyValue.getMax()) {
                        key += "Range";
                        desc = tbl.getString(key);
                        if ("Poison".equals(type)) {
                            desc = String.format(desc, propertyValue.getMin(), propertyValue.getMax(), Integer.valueOf(propertyValue.getParam())/25);
                        }
                        else {
                            //System.out.println(key + " " + desc);
                            desc = String.format(desc, propertyValue.getMin(), propertyValue.getMax());
                        }
                    }
                    else {
                        desc = tbl.getString(key);
                        if ("Poison".equals(type)) {
                            desc = String.format(desc, propertyValue.getMin(), Integer.valueOf(propertyValue.getParam())/25);
                        }
                        else {
                            desc = String.format(desc, propertyValue.getMin());
                        }
                    }
                    sb.append(String.format("\n%s", desc));
                }
                else {
                    for (Stat stat : stats) {
                        sb.append(String.format("\n+ %d %s", propertyValue.getValue(), tbl.getString(stat.getDescStrPos())));
                    }
                }
            }
            if (minDam != -1 && maxDam != -1) {
                sb.append(String.format("\n%s", String.format(tbl.getString("strModMinDamage"), minDam, maxDam)));
            }
            else if (minDam != -1) {
                sb.append(String.format("\n+%d %s", minDam, tbl.getString(Data.getInstance().getStatMap().get("mindamage").getDescStrPos())));
            }
            else if (maxDam != -1) {
                sb.append(String.format("\n+%d %s", maxDam, tbl.getString(Data.getInstance().getStatMap().get("maxdamage").getDescStrPos())));
            }
        }
        return sb.toString();
    }
    
    protected List<PropertyValue> generateProperties() {
        List<PropertyValue> newProperties = new ArrayList<PropertyValue>();
        for (PropertyValue property : propertyValues) {
            newProperties.add(property.generateValue());
        }
        return newProperties;
    }
    
    private String getSkillName(PropertyValue propertyValue) {
        Skill skill = Data.getInstance().getSkillMap().get(propertyValue.getParam());
        String id = skill == null ? propertyValue.getParam() : String.valueOf(skill.getId()); 
        String name = TblParser.getInstence().getString("skillname" + id);
        if (name == null) {
            name = TblParser.getInstence().getString("Skillname" + id);
        }
        return name;
    }
    
    private String getClassFromSkillTab(int skillTab) {
        switch((skillTab-1)/3) {
        case 0:
            return "
        }
    }
}
