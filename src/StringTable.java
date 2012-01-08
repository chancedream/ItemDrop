import java.io.UnsupportedEncodingException;


public class StringTable {
    private int crc;
    private int numElements;
    private int hashtableSize;
    private byte version;
    private int indexStart;
    private int maxTry;
    private int indexEnd;
    
    private int[] indices;
    private HashEntry[] hashtable;
    private byte[] data;
    
    private int getHash(String keyString)
    {
       long hashValue = 0;
       char[] chars = keyString.toCharArray();
       for (int i = 0; i < chars.length; i++) {
           hashValue *= 0x10;
           hashValue += chars[i];
           if ((hashValue & 0xF0000000) != 0)
           {
              //System.out.println(Integer.toHexString(hashValue));
              long tempValue = hashValue & 0xF0000000;
              tempValue /= 0x01000000;
              hashValue &= 0x0FFFFFFF;
              hashValue ^= tempValue;
           }
       }
       return (int)hashValue % hashtableSize;
    } // getHash
    
    public String getString(String keyString) throws Exception {
        int hash = getHash(keyString);
        byte[] keyBytes = keyString.getBytes("UTF-8");
        int tryLimit = Math.min(maxTry, hashtable.length - hash);
        for (int i = 0; i < tryLimit; i++) {
            HashEntry entry = hashtable[hash+i];
            if (entry.getUsed() != 0) {
                int keyOffset = entry.getKeyOffset();
                boolean equal = true;
                for (int j = 0; j < keyBytes.length; j++) {
                    if (j >= data.length - keyOffset || keyBytes[j] != data[keyOffset+j]) {
                        equal = false;
                        break;
                    }
                }
                if (equal) {
                    byte[] stringBytes = new byte[entry.getStrLen()];
                    System.arraycopy(data, entry.getStringOffset(), stringBytes, 0, entry.getStrLen());
                    return new String(stringBytes, "UTF-8");
                }
            }
        }
        return null;
    }
    
    public String getStringNum(int num) throws UnsupportedEncodingException {
        if (num >= hashtable.length)
            return null;
        HashEntry entry = hashtable[num];
        if (entry.getUsed() != 0) {
//            int keylength = entry.getStringOffset() - entry.getKeyOffset() -1;
//            byte[] keybytes = new byte[keylength];
//            System.arraycopy(data, entry.getKeyOffset(), keybytes, 0, keylength);
//            System.out.println(new String(keybytes, "UTF-8"));
            byte[] stringBytes = new byte[entry.getStrLen()];
            System.arraycopy(data, entry.getStringOffset(), stringBytes, 0, entry.getStrLen());
            return new String(stringBytes, "UTF-8");
        }
        return null;
    }

    public static class HashEntry {
        private byte used;
        private int index;
        private int hashValue;
        private int keyOffset;
        private int stringOffset;
        private int strLen;
        public byte getUsed() {
            return used;
        }
        public void setUsed(byte used) {
            this.used = used;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public int getHashValue() {
            return hashValue;
        }
        public void setHashValue(int hashValue) {
            this.hashValue = hashValue;
        }
        public int getKeyOffset() {
            return keyOffset;
        }
        public void setKeyOffset(int keyOffset) {
            this.keyOffset = keyOffset;
        }
        public int getStringOffset() {
            return stringOffset;
        }
        public void setStringOffset(int stringOffset) {
            this.stringOffset = stringOffset;
        }
        public int getStrLen() {
            return strLen;
        }
        public void setStrLen(int strLen) {
            this.strLen = strLen;
        }
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public int getNumElements() {
        return numElements;
    }

    public void setNumElements(int numElements) {
        this.numElements = numElements;
    }

    public int getHashtableSize() {
        return hashtableSize;
    }

    public void setHashtableSize(int hashtableSize) {
        this.hashtableSize = hashtableSize;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getIndexStart() {
        return indexStart;
    }

    public void setIndexStart(int indexStart) {
        this.indexStart = indexStart;
    }

    public int getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(int maxTry) {
        this.maxTry = maxTry;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public void setIndexEnd(int indexEnd) {
        this.indexEnd = indexEnd;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public HashEntry[] getHashtable() {
        return hashtable;
    }

    public void setHashtable(HashEntry[] hashtable) {
        this.hashtable = hashtable;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
