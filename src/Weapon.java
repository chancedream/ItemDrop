
public class Weapon extends EquipBase {

    public Weapon(String name, int qlvl, String code, int tC, String type, int rarity) {
        super(name, qlvl, code, tC, type, rarity);
    }

    @Override
    public String toString() {
        return String.format("Weapon[name=%s, qlvl=%d, code=%s, TC=%d, type=%s, rarity=%d]", name, qlvl, code, TC, type, rarity);
    }
}
