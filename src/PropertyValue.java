import java.util.Random;


public class PropertyValue {
    private static Random random = new Random();
    protected Property property;
    protected String param;
    private int min;
    private int max;
    private int value;
    
    public PropertyValue(Property property, String param, int min, int max) {
        this(property, param, min, max, 0);
    }

    public PropertyValue(Property property, String param, int min, int max, int value) {
        this.property = property;
        this.param = param;
        this.min = min;
        this.max = max;
        this.value = value;
    }

    public PropertyValue generateValue() {
        //System.out.println(String.format("min=%d, max=%d", min, max));
        int value = 0;
        if (min == max) {
            value = min;
        }
        else if (min < max) {
            value = min + random.nextInt(max-min+1);
        }
        return new PropertyValue(property, param, min, max, value);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}
