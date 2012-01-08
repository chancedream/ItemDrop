import java.util.List;


public class UniqueItem extends Item {
    private int rarity;

    public UniqueItem(ItemBase base, int qlvl, String name, List<PropertyValue> properties, int rarity) {
        super(base, Quality.UNIQUE, qlvl, name, properties);
        this.rarity = rarity;
    }

    @Override
    public Item generateItem() {
        //System.out.println(this);
        return new UniqueItem(base, qlvl, name, generateProperties(), rarity);
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

}
