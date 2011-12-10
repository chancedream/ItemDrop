
public abstract class ItemBase {
    protected String name;
    protected int qlvl;
    protected String code;
    protected String type;
    protected int rarity;
    
    public ItemBase(String name, int qlvl, String code, String type, int rarity) {
        super();
        this.name = name;
        this.qlvl = qlvl;
        this.code = code;
        this.type = type;
        this.rarity = rarity;
    }
    
    abstract public boolean isClassic();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQlvl() {
        return qlvl;
    }

    public void setQlvl(int qlvl) {
        this.qlvl = qlvl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
    
}
