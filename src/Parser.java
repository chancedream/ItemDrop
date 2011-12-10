
public class Parser {
    protected String[] title;
    
    public Parser(String[] title) {
        this.title = title;
    }
    
    protected static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    protected boolean parseBoolean(String str) {
        return parseInt(str) == 1;
    }
    
    protected static int calcTC(int qlvl) {
        return ((qlvl-1)/3+1)*3;
    }
    
    protected int index(String colName) {
        for (int i = 0; i < title.length; i++) {
            if (colName.equals(title[i]))
                return i;
        }
        return -1;
    }
    
}
