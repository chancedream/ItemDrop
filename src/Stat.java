
public class Stat {
    private String name;
    private int descpriority;
    private int descFunc;
    private int descVal;
    private String descStrPos;
    private int descGroupFunc;
    private int descGroupVal;
    private int descGroup;
    private String descGroupStrPos;
    
    public Stat(String name, int descpriority, int descFunc, int descVal,
            String descStrPos, int descGroup, int descGroupFunc, int descGroupVal,
            String descGroupStrPos) {
        this.name = name;
        this.descpriority = descpriority;
        this.descFunc = descFunc;
        this.descVal = descVal;
        this.descStrPos = descStrPos;
        this.descGroupFunc = descGroupFunc;
        this.descGroupVal = descGroupVal;
        this.descGroup = descGroup;
        this.descGroupStrPos = descGroupStrPos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescStrPos() {
        return descStrPos;
    }

    public void setDescStrPos(String descStrPos) {
        this.descStrPos = descStrPos;
    }

    public int getDescGroup() {
        return descGroup;
    }

    public void setDescGroup(int descGroup) {
        this.descGroup = descGroup;
    }

    public String getDescGroupStrPos() {
        return descGroupStrPos;
    }

    public void setDescGroupStrPos(String descGroupStrPos) {
        this.descGroupStrPos = descGroupStrPos;
    }

    public int getDescVal() {
        return descVal;
    }

    public void setDescVal(int descVal) {
        this.descVal = descVal;
    }

    public int getDescpriority() {
        return descpriority;
    }

    public void setDescpriority(int descpriority) {
        this.descpriority = descpriority;
    }

    public int getDescFunc() {
        return descFunc;
    }

    public void setDescFunc(int descFunc) {
        this.descFunc = descFunc;
    }

    public int getDescGroupFunc() {
        return descGroupFunc;
    }

    public void setDescGroupFunc(int descGroupFunc) {
        this.descGroupFunc = descGroupFunc;
    }

    public int getDescGroupVal() {
        return descGroupVal;
    }

    public void setDescGroupVal(int descGroupVal) {
        this.descGroupVal = descGroupVal;
    }
    
}
