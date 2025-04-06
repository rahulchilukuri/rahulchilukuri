class DLL {
    public DLL next;
    public DLL prev;
    public int val;
    public int key;
    public int freq;
    
    DLL(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}

class LFUCache {
    int capacity;
    int minFreq = Integer.MAX_VALUE;
    Map<Integer, DLL> cache;
    Map<Integer, DLL> freqMap;

    DLL newSequence() {
        DLL tail = new DLL(0,0);
        DLL head = new DLL(0,0);
        tail.prev = head;
        head.next = tail;
        head.prev = tail;//to access tail when head is accessible
        return head;
    }

    void remove(DLL node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }    

    void addToFront(DLL head, DLL node) {
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        head.next = node;
    }    

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        DLL entry = cache.get(key);
        if(entry != null) {
            updateFrequency(entry);
            return entry.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        DLL entry = cache.get(key);
        if(entry != null) {
            updateFrequency(entry);
            entry.val = value;
            return;
        }

        if(cache.size() >= capacity) {
            //get tail of minFreq seq and remove it's actual tail
            DLL minFreqHead = freqMap.get(minFreq);

            //head prev points to tail
            //tail prev is the LRU
            DLL lru = minFreqHead.prev.prev;
            remove(lru); //breaking prev/next of this node in it's sequence
            cache.remove(lru.key);
        }

        minFreq = 1;
        DLL newEntry = new DLL(key,value);
        freqMap.putIfAbsent(minFreq, newSequence());
        addToFront(freqMap.get(1), newEntry);
        cache.put(key, newEntry);
    }

    void updateFrequency(DLL entry) {
        remove(entry); //breaking prev/next of this node in it's sequence
        int oldFreq = entry.freq++;

        freqMap.putIfAbsent(entry.freq, newSequence());
        addToFront(freqMap.get(entry.freq), entry);
        
        //check the minFreq
        //after updating the frequency check which is the
        DLL oldFreqHead = freqMap.get(oldFreq);
        if (oldFreqHead.prev == oldFreqHead.next) {  // If the frequency list is empty
            freqMap.remove(oldFreq);  // Remove the empty frequency list
            if (minFreq == oldFreq) {
                minFreq++;  // Increase minFreq if we removed the list for the previous frequency
            }
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
