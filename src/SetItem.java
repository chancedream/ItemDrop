import java.util.List;


public class SetItem extends Item {
    private int rarity;

    public SetItem(ItemBase base, int qlvl, String name, List<PropertyValue> properties, int rarity) {
        super(base, Quality.SET, qlvl, name, properties);
        this.rarity = rarity;
    }
    
    @Override
    public Item generateItem() {
        return new SetItem(base, qlvl, name, generateProperties(), rarity);
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

}
