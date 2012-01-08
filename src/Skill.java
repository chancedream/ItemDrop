
public class Skill {
    private String name;
    private int id;
    private String charClass;
    
    public Skill(String name, int id, String charClass) {
        this.name = name;
        this.id = id;
        this.charClass = charClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

}
