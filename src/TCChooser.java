import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TCChooser {
    private Random rand = new Random();
    private Data data = Data.getInstance();
    
    public static void main(String[] args) throws Exception {
        TCChooser tcChooser = new TCChooser();
        List<Item> list = tcChooser.drop("baalcrab", Difficulty.HELL, 4, 590, true);
        
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    
    public TCChooser() {
        
    }
    
    public List<Item> drop(String monsterId, Difficulty difficulty, int noDropFix, int mf, boolean questKill) throws Exception {
        Monster monster = data.getMonsterIdMap().get(monsterId);
        if (monster == null) {
            throw new Exception("Monster name: [" + monsterId + "] not found.");
        }
        String tcName = null;
        int mlvl = 0;
        switch (difficulty) {
        case NORMAL:
            tcName = questKill ? monster.getTcq() : monster.getTc();
            mlvl = monster.getLevel();
            break;
        case NIGHTMARE:
            tcName = questKill ? monster.getTcqN() : monster.getTcN();
            mlvl = monster.getLevelN();
            break;
        case HELL:
            tcName = questKill ? monster.getTcqH() : monster.getTcH();
            mlvl = monster.getLevelH();
            break;
        }
        List<Item> result = drop(data.getTcMap().get(tcName), noDropFix, mf, mlvl);
        if (result.size() > 6) {
            result = result.subList(0, 6);
        }
        return result;
    }
    
    private List<Item> drop(TC tc, int noDropFix, int mf, int mlvl) {
        return subTCDrop(tc, noDropFix, mf, mlvl, tc);
    }
    
    private List<Item> subTCDrop(TC tc, int noDropFix, int mf, int mlvl, TC monsterTC) {
        if (tc.getUnique() > 0) {
            monsterTC = tc;
        }
        List<Item> result = new ArrayList<Item>();
        int picks = tc.getPicks();
        if (picks < 0) {
            for (int i = 0; i < tc.getItems().length; i++) {
                String item = tc.getItems()[i];
                for (int j = 0; j < tc.getProbs()[i]; j++) {
                    result.addAll(subTCDrop(item, noDropFix, mf, mlvl, monsterTC));
                    if (picks >= 0) {
                        break;
                    }
                }
                if (picks >= 0) {
                    break;
                }
                
            }
        }
        else {
            int sum = 0;
            for (int i = 0; i < tc.getProbs().length; i++) {
                sum += tc.getProbs()[i];
            }
            int noDrop = tc.getNodrop();
            if (noDrop > 0 && noDropFix > 1) {
                Double fixedRate = Math.pow((double)noDrop/(sum+noDrop), noDropFix);
                noDrop = (int)(sum*fixedRate/(1-fixedRate));
            }
            for (int pickCount = 0; pickCount < picks; pickCount++) {
                int chance = rand.nextInt(sum + noDrop) + 1;
                for (int i = 0; i < tc.getProbs().length; i++) {
                    chance -= tc.getProbs()[i];
                    if (chance <= 0) {
                        result.addAll(subTCDrop(tc.getItems()[i], noDropFix, mf, mlvl, monsterTC));
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    private List<Item> subTCDrop(String tcName, int noDropFix, int mf, int mlvl, TC monsterTC) {
        
        TC tc = data.getTcMap().get(tcName);
        if (tc != null) {
            return subTCDrop(tc, noDropFix, mf, mlvl, monsterTC);
        }
        else {
            List<EquipBase> equipBaseList = data.getTcEquipBaseMap().get(tcName);
            ItemBase itemBase = null;
            if (equipBaseList != null) {
                itemBase = equipBaseList.get(rand.nextInt(equipBaseList.size()));
            }
            else {
                itemBase = data.getCodeItemBaseMap().get(tcName);
            }
            List<Item> result = new ArrayList<Item>();
            result.add(chooseQuality(itemBase, mlvl, mf, monsterTC));
            return result;
        }
    }
    
    private Item chooseQuality(ItemBase itemBase, int mlvl, int mf, TC monsterTC) {
        Quality quality = Quality.NORMAL;
        ItemType itemType = data.getItemTypeMap().get(itemBase.getType()); 
        QualityChooser qChooser = new QualityChooser();
        quality = qChooser.detimine(itemBase.getQlvl(), mlvl, itemBase.isClassic(), monsterTC.getUnique(), 
                monsterTC.getSet(), monsterTC.getRare(), monsterTC.getMagic(), mf);
        switch (quality) {
        case UNIQUE:
            List<Item> uniqueItemList = data.getUniqueItemMap().get(itemBase.getCode());
            if (uniqueItemList == null || uniqueItemList.get(0).getQlvl() > mlvl) {
                //System.out.println("Unique not find for " + itemBase.getName());
                return generateItem(itemBase, itemType, Quality.RARE);
            }
            if (uniqueItemList.size() > 1) {
                int lastIndex = data.getUniqueQlvlIndexMap().get(itemBase.getCode())[mlvl];
                return uniqueItemList.get(rand.nextInt(lastIndex+1));
            }
            return uniqueItemList.get(0);
        case SET:
            List<Item> setItemList = data.getSetItemMap().get(itemBase.getCode());
            if (setItemList == null || setItemList.get(0).getQlvl() > mlvl) {
                //System.out.println("Set not find for " + itemBase.getName());
                return generateItem(itemBase, itemType, Quality.MAGICAL);
            }
            if (setItemList.size() > 1) {
                int lastIndex = data.getSetQlvlIndexMap().get(itemBase.getCode())[mlvl];
                return setItemList.get(rand.nextInt(lastIndex+1));
            }
            return setItemList.get(0);
        case RARE:
        case MAGICAL:
        case SUPERIOR:
        case NORMAL:
        default:
            return generateItem(itemBase, itemType, quality);
            
        }
            
    }
    
    private Item generateItem(ItemBase itemBase, ItemType itemType, Quality targetQuality) {
        switch(targetQuality) {
        case RARE:
            if (itemType.canRare()) {
                return new Item(itemBase, Quality.RARE, itemBase.getQlvl(), itemBase.getName());
            }
        case MAGICAL:
            if (itemType.canMagic()) {
                return new Item(itemBase, Quality.MAGICAL, itemBase.getQlvl(), itemBase.getName());
            }
        case NORMAL:
            targetQuality = Quality.NORMAL;
        case SUPERIOR:
        case LOW_QUALITY:
            if (!itemType.canNormal()) {
                return new Item(itemBase, Quality.MAGICAL, itemBase.getQlvl(), itemBase.getName());
            }
        default:
            return new Item(itemBase, targetQuality, itemBase.getQlvl(), itemBase.getName());
        }
    }
}
