
public class Property {
    private String code;
    private Stat[] stats;
    private int[] vals;
    private int[] funcs;
    
    public Property(String code, Stat[] stats, int[] vals, int[] funcs) {
        this.code = code;
        this.stats = stats;
        this.vals = vals;
        this.funcs = funcs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Stat[] getStats() {
        return stats;
    }

    public void setStats(Stat[] stats) {
        this.stats = stats;
    }

    public int[] getFuncs() {
        return funcs;
    }

    public void setFuncs(int[] funcs) {
        this.funcs = funcs;
    }

    public int[] getVals() {
        return vals;
    }

    public void setVals(int[] vals) {
        this.vals = vals;
    }
    
}
