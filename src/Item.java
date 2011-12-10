
public class Item {
    private ItemBase base;
    private Quality quality;
    private int qlvl;
    private String name;
    
    public Item(ItemBase base, Quality quality, int qlvl, String name) {
        super();
        this.base = base;
        this.quality = quality;
        this.qlvl = qlvl;
        this.name = name;
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
    
    @Override
    public String toString() {
        return String.format("name=%s, qlvl=%d, quality=%s, base=%s", name, qlvl, quality, base);
    }
}
