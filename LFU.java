import java.util.*;

class DLL {
    public DLL prev, next;
    public int key, val, freq;

    DLL(int key, int val) {
        this.key = key;
        this.val = val;
        this.freq = 1;
    }
}

class DLinkedList {
    DLL head, tail;
    int size;

    DLinkedList() {
        head = new DLL(0, 0); // dummy head
        tail = new DLL(0, 0); // dummy tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // Add node right after head (most recent)
    void addToFront(DLL node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    // Remove a given node
    void remove(DLL node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    // Remove the least recently used node (just before tail)
    DLL removeTail() {
        if (size > 0) {
            DLL node = tail.prev;
            remove(node);
            return node;
        }
        return null;
    }

    boolean isEmpty() {
        return size == 0;
    }
}

public class LFUCache {
    int capacity;
    int minFreq;
    Map<Integer, DLL> cache; // key -> node
    Map<Integer, DLinkedList> freqMap; // freq -> list of nodes with this freq

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        DLL node = cache.get(key);
        if (node == null) return -1;
        updateFrequency(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            DLL node = cache.get(key);
            node.val = value;
            updateFrequency(node);
        } else {
            if (cache.size() == capacity) {
                DLinkedList minFreqList = freqMap.get(minFreq);
                DLL toRemove = minFreqList.removeTail();
                cache.remove(toRemove.key);
            }

            DLL newNode = new DLL(key, value);
            cache.put(key, newNode);
            minFreq = 1;
            freqMap.putIfAbsent(1, new DLinkedList());
            freqMap.get(1).addToFront(newNode);
        }
    }

    private void updateFrequency(DLL node) {
        int oldFreq = node.freq;
        DLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        if (oldList.isEmpty()) {
            freqMap.remove(oldFreq);
            if (minFreq == oldFreq) minFreq++;
        }

        node.freq++;
        freqMap.putIfAbsent(node.freq, new DLinkedList());
        freqMap.get(node.freq).addToFront(node);
    }
}
