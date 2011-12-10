
public class Monster {
    private String id;
    private String name;
    private int level;
    private int levelN;
    private int levelH;
    private String tc;
    private String tcq;
    private String tcN;
    private String tcqN;
    private String tcH;
    private String tcqH;
    private boolean boss;
    
    public Monster(String id, String name, int level, int levelN, int levelH, String tc,
            String tcq, String tcN, String tcqN, String tcH, String tcqH, boolean boss) {
        super();
        this.id = id;
        this.name = name;
        this.level = level;
        this.levelN = levelN;
        this.levelH = levelH;
        this.tc = tc;
        this.tcq = tcq;
        this.tcN = tcN;
        this.tcqN = tcqN;
        this.tcH = tcH;
        this.tcqH = tcqH;
        this.boss = boss;
    }
    
    @Override
    public String toString() {
        return String.format("Monster[id=%s, name=%s, level=%d, level N=%d, level H=%d, tc=%s, tcq=%s, tc N=%s, " +
        		"tcq N=%s, tc H=%s, tcq H=%s, boss=%b]", id, name, level, levelN, levelH, tc, tcq, tcN, tcqN, tcH,
        		tcqH, boss);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelN() {
        return levelN;
    }

    public void setLevelN(int levelN) {
        this.levelN = levelN;
    }

    public int getLevelH() {
        return levelH;
    }

    public void setLevelH(int levelH) {
        this.levelH = levelH;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTcq() {
        return tcq;
    }

    public void setTcq(String tcq) {
        this.tcq = tcq;
    }

    public String getTcN() {
        return tcN;
    }

    public void setTcN(String tcN) {
        this.tcN = tcN;
    }

    public String getTcqN() {
        return tcqN;
    }

    public void setTcqN(String tcqN) {
        this.tcqN = tcqN;
    }

    public String getTcH() {
        return tcH;
    }

    public void setTcH(String tcH) {
        this.tcH = tcH;
    }

    public String getTcqH() {
        return tcqH;
    }

    public void setTcqH(String tcqH) {
        this.tcqH = tcqH;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }
    
    
}
