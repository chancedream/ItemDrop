import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class TblParser {
    public static String FILE_NAME = "data/String.tbl";
    public static String EXP_FILE_NAME = "data/ExpansionString.tbl";
    public static String PATCH_FILE_NAME = "data/PatchString.tbl";
    
    public static int HEADER_SIZE               = 0x15;
    public static int ELEMENT_SIZE               = 0x02;
    public static int NODE_SIZE               = 0x11;

    // Header info location
    public static int CRC_OFFSET               = 0x00;      // word
    public static int NUM_ELEMENTS_OFFSET         = 0x02;      // word
    public static int HASHTABLE_SIZE_OFFSET         = 0x04;      // dword
    public static int VERSION_OFFSET            = 0x08;      // byte (always 0)
    public static int STRING_START_OFFSET         = 0x09;      // dword
    public static int NUM_LOOPS_OFFSET            = 0x0D;      // dword
    public static int FILE_SIZE_OFFSET            = 0x11;      // dword

    // Element info location
    public static int NODE_NUMBER_OFFSET            = 0x00;      // word

    // Node info location
    public static int ACTIVE_OFFSET            = 0x00;      // byte
    public static int IDX_NBR_OFFSET            = 0x01;      // word
    public static int HASH_VALUE_OFFSET            = 0x03;      // dword
    public static int IDX_STRING_OFFSET            = 0x07;      // dword
    public static int NAME_STRING_OFFSET         = 0x0B;      // dword
    public static int NAME_LEN_OFFSET            = 0x0F;      // word

    // KeyNums
    public static int STRING_KEY_NUM            =     0;
    public static int PATCH_STRING_KEY_NUM         = 10000;
    public static int EXPANSION_STRING_KEY_NUM      = 20000;
    
    private static TblParser instance = new TblParser();
    
    private StringTable stringTable, expStringTable, patchStringTable;
    
    public static void main(String[] args) throws Exception {
        
//        System.out.println(Data.getInstance().getSetItemMap().get("hbt").get(0).getName());
//        System.out.println(instance.getString(Data.getInstance().getSetItemMap().get("hbt").get(0).getName()));
        StringTable table = instance.patchStringTable;
        System.out.println(table.getString("X"));
//        for (int i = 0; i < table.getHashtableSize(); i++) {
//                System.out.println(table.getStringNum(i));
//        }
    }
    
    private TblParser() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static TblParser getInstence() {
        return instance;
    }
    
    public String getString(String key) {
        try {
            String str = patchStringTable.getString(key);
            if (str != null)
                return str;
            str = expStringTable.getString(key);
            if (str != null)
                return str;
            return stringTable.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getStringByNum(int num) {
        try {
            if (num < 10000)
                return stringTable.getStringNum(num);
            if (num < 20000)
                return expStringTable.getStringNum(num - 10000);
            return patchStringTable.getStringNum(num - 20000);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void init() throws Exception {
        stringTable = initTable(FILE_NAME);
        expStringTable = initTable(EXP_FILE_NAME);
        patchStringTable = initTable(PATCH_FILE_NAME);
//        for (int i = 0; i < expStringTable.getHashtableSize(); i++) {
//            String str = expStringTable.getStringNum(i);
//            System.out.println(i + str);
//        }
    }
    
    private StringTable initTable(String fileName) throws Exception {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
//        System.out.println(input.markSupported());
        input.mark(Integer.MAX_VALUE);
        StringTable table = new StringTable();
        table.setCrc(readUnsignedShort(input));
        table.setNumElements(readUnsignedShort(input));
//        System.out.println(table.getNumElements());
        table.setHashtableSize(readInt(input));
//        System.out.println(table.getHashtableSize());
        table.setVersion((byte)input.read());
        table.setIndexStart(readInt(input));
        table.setMaxTry(readInt(input));
        table.setIndexEnd(readInt(input));
        int[] indices = new int[table.getNumElements()];
        for (short i = 0; i < indices.length; i++) {
            indices[i] = readUnsignedShort(input);
        }
        table.setIndices(indices);
        StringTable.HashEntry[] hashtable = new StringTable.HashEntry[table.getHashtableSize()];
        for (int i = 0; i < hashtable.length; i++) {
            StringTable.HashEntry entry = new StringTable.HashEntry();
            entry.setUsed((byte)input.read());
            entry.setIndex(readUnsignedShort(input));
            entry.setHashValue(readInt(input));
            entry.setKeyOffset(readInt(input));
            entry.setStringOffset(readInt(input));
            entry.setStrLen(readUnsignedShort(input));
            hashtable[i] = entry;
        }
        table.setHashtable(hashtable);
        input.reset();
        byte[] data = new byte[table.getIndexEnd()];
        int offset = 0;
        int len = data.length;
        int num;
        while((num = input.read(data, offset, len)) > 0) {
            offset += num;
            len -= num;
        }
        table.setData(data);
        return table;
    }
    
    private int readUnsignedShort(InputStream input) throws Exception {
        return read(input, 2);
    }
    
    private int readInt(InputStream input) throws Exception {
        return read(input, 4);
    }
    
    private int read(InputStream input, int size) throws IOException {
        byte[] bytes = new byte[size];
        input.read(bytes);
        return bytesToInt(bytes);
    }
    
    private int bytesToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < b.length; i++) {
            int shift = i * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }
}
