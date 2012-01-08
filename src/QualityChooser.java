import java.util.Random;


public class QualityChooser {
       
    private Random rand = new Random();
      
    public static void main(String[] args) {
        int qlvl = 7;
        int mlvl = 12;
        boolean classic = false;
        int cu, cs, cr;
        cu = cs = cr = 995;
        int cm = 1024;
        int mf = 900;
        int total = 100000;
        QualityChooser chooser = new QualityChooser();
        for (int j = 0; j <=1000; j+=50) {
            int count = 0;
            for (int i = 0; i < total; i++) {
                if (chooser.isUnique(qlvl, mlvl, classic, cu, j)) {
                    count++;
                }
            }
        System.out.println("mf = " + j + " : " + count);
        }
    }
       
    public Quality detimine(int qlvl, int mlvl, boolean classic, int cu, int cs, int cr, int cm, int mf) {
        if (isUnique(qlvl, mlvl, classic, cu, mf)) {
            return Quality.UNIQUE;
        }
        if (isSet(qlvl, mlvl, classic, cs, mf)) {
            return Quality.SET;
        }
        if (isRare(qlvl, mlvl, classic, cr, mf)) {
            return Quality.RARE;
        }
        if (isMagical(qlvl, mlvl, classic, cm, mf)) {
            return Quality.MAGICAL;
        }
        return Quality.NORMAL;
    }
    
    public boolean isUnique(int qlvl, int mlvl, boolean classic, int cu, int mf) {
        return roll(qlvl, mlvl, classic ? 240 : 400, classic ? 3 : 1, cu, 6400, mf*250/(mf + 250));
    }
    
    private boolean isSet(int qlvl, int mlvl, boolean classic, int cs, int mf) {
        return roll(qlvl, mlvl, classic ? 120 : 160, classic ? 3 : 2, cs, 5600, mf*500/(mf + 500));
    }
    
    private boolean isRare(int qlvl, int mlvl, boolean classic, int cr, int mf) {
        return roll(qlvl, mlvl, classic ? 80 : 100, classic ? 3 : 2, cr, 3200, mf*600/(mf + 600));
    }
    
    private boolean isMagical(int qlvl, int mlvl, boolean classic, int cm, int mf) {
        return roll(qlvl, mlvl, classic ? 17 : 34, classic ? 6 : 3, cm, 192, mf);
    }
    
    private boolean roll(int qlvl, int mlvl, int value, int divisor, int cx, int min, int xf) {
        return rand.nextInt(chance(qlvl, mlvl, value, divisor, cx, min, xf) + 1) < 128;
    }
    
    private int chance(int qlvl, int mlvl, int value, int divisor, int cx, int min, int xf) {
        float chance1 = (value - (mlvl - qlvl) / (float)divisor)*128/(1+(float)xf/100);
        if (chance1 < min) chance1 = min;
        return (int)(chance1 * (1024-cx)/1024);
    }
}
