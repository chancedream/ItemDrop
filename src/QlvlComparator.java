import java.util.Comparator;


public class QlvlComparator implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getQlvl() == o2.getQlvl())
            return 0;
        return o1.getQlvl() > o2.getQlvl() ? 1 : -1;
    }

}
