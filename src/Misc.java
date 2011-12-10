
public class Misc extends ItemBase {

    public Misc(String name, int qlvl, String code, String type, int rarity) {
        super(name, qlvl, code, type, rarity);
    }
    
    public String toString() {
        return String.format("Misc[name=%s, qlvl=%d, code=%s, type=%s, rarity=%d]", name, qlvl, code, type, rarity);
    }

    @Override
    public boolean isClassic() {
        return false;
    }
}
