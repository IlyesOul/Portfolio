import java.util.List;

public class HashTTTSet {
    private static final double MAX_LOAD_FACTOR = 0.75;
    public HashEntry[] elementData;
    private int size;

    public HashTTTSet() {
        elementData = new HashEntry[10];
        size = 0;
    }


    public void add(TTT p) {
        if (!contains(p)) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }

            int bucket = hashFunction(p);
            elementData[bucket] = new HashEntry(p, elementData[bucket]);
            size++;
        }
    }


    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    public void addAll(List<TTT> other)
    {
        for(TTT element: other)
            add(element);
    }

    public boolean contains(TTT p) {
        int bucket = hashFunction(p);
        HashEntry current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(p)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public boolean contains(int x, int y, char c) {
        TTT p = new TTT(x, y, c);
        int bucket = hashFunction(p);
        HashEntry current = elementData[bucket];
        while (current != null) {
            if (current.data.equal(p)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public boolean isTaken(int x, int y)
    {
        TTT p = new TTT(x, y, ' ');
        int bucket = hashFunction(p);
        HashEntry current = elementData[bucket];
        while (current != null) {
            if (current.data.isTaken(p)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(TTT p) {
        int bucket = hashFunction(p);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data.equals(p)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(p)) {
                    current = current.next;
                }

                // if the element is found, remove it
                if (current.next != null && current.next.data.equals(p)) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }
    
    public HashEntry get(int i)
    {
        return elementData[i];
    }

    private int hashFunction(TTT p) {
        return (p.getX() *31*p.getY()) % elementData.length;
    }

    private double loadFactor() {
        return (double) size / elementData.length;
    }

    private void rehash() {
        // replace element data array with a larger empty version
        HashEntry[] oldElementData = elementData;
        elementData = new HashEntry[2 * oldElementData.length];
        size = 0;
        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry current = oldElementData[i];
            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }

    private class HashEntry {
        public TTT data;
        public HashEntry next;

        public HashEntry(TTT data) {
            this(data, null);
        }

        public HashEntry(TTT data, HashEntry next) {
            this.data = data;
            this.next = next;
        }
    }
}