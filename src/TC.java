
public class TC {
    public static String ARMOR = "armo";
    public static String WEAPON = "weap";
    public static String BOW = "bow";
    public static String MELEE = "mele";
    
    private String name;
    private int group;
    private int level;
    private int picks;
    private int unique, set, rare, magic, nodrop;
    private String[] items;
    private Integer[] probs;
    
    public TC(String name, int group, int level, int picks, int unique,
            int set, int rare, int magic, int nodrop, String[] items,
            Integer[] probs) {
        super();
        this.name = name;
        this.group = group;
        this.level = level;
        this.picks = picks;
        this.unique = unique;
        this.set = set;
        this.rare = rare;
        this.magic = magic;
        this.nodrop = nodrop;
        this.items = items;
        this.probs = probs;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGroup() {
        return group;
    }
    public void setGroup(int group) {
        this.group = group;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getPicks() {
        return picks;
    }
    public void setPicks(int picks) {
        this.picks = picks;
    }
    public int getUnique() {
        return unique;
    }
    public void setUnique(int unique) {
        this.unique = unique;
    }
    public int getSet() {
        return set;
    }
    public void setSet(int set) {
        this.set = set;
    }
    public int getRare() {
        return rare;
    }
    public void setRare(int rare) {
        this.rare = rare;
    }
    public int getMagic() {
        return magic;
    }
    public void setMagic(int magic) {
        this.magic = magic;
    }
    public int getNodrop() {
        return nodrop;
    }
    public void setNodrop(int nodrop) {
        this.nodrop = nodrop;
    }
    public String[] getItems() {
        return items;
    }
    public void setItems(String[] items) {
        this.items = items;
    }
    public Integer[] getProbs() {
        return probs;
    }
    public void setProbs(Integer[] probs) {
        this.probs = probs;
    }
    
    @Override
    public String toString() {
        return String.format("TC[name=%s, group=%d, level=%d, picks=%d, unique=%d, set=%d, rare=%d, magical=%d, " +
        		"noDrop=%d]", name, group, level, picks, unique, set, rare, magic, nodrop);
    }
}
