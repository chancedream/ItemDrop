
public class ItemType {
    private String name;
    private String code;
    private String equiv1;
    private boolean magic;
    private boolean rare;
    private boolean normal;
    
    public ItemType(String name, String code, String equiv1, boolean magic,
            boolean rare, boolean normal) {
        super();
        this.name = name;
        this.code = code;
        this.equiv1 = equiv1;
        this.magic = magic;
        this.rare = rare;
        this.normal = normal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEquiv1() {
        return equiv1;
    }

    public void setEquiv1(String equiv1) {
        this.equiv1 = equiv1;
    }

    public boolean canMagic() {
        return magic;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public boolean canRare() {
        return rare;
    }

    public void setRare(boolean rare) {
        this.rare = rare;
    }

    public boolean canNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }
}
