
public class SetItem extends Item {
    private int rarity;

    public SetItem(ItemBase base, int qlvl, String name, int rarity) {
        super(base, Quality.SET, qlvl, name);
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

}
