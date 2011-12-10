
public class EquipBase extends ItemBase {
    protected int TC;
    
    public EquipBase(String name, int qlvl, String code, int tC, String type, int rarity) {
        super(name, qlvl, code, type, rarity);
        TC = tC;
    }
    
    public boolean isClassic() {
        return type.matches("abow|aspe|ajav|h2h|h2h2|orb|head|phlm|pelt|ashd");
    }

    public int getTC() {
        return TC;
    }

    public void setTC(int tC) {
        TC = tC;
    }
    
}
