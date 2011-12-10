
public class UniqueItem extends Item {
    private int rarity;

    public UniqueItem(ItemBase base, int qlvl, String name, int rarity) {
        super(base, Quality.UNIQUE, qlvl, name);
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

}
