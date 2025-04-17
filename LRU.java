class DLL {
	public DLL next;
	public DLL prev;
	public int key;
	public int val;
	
	public DLL(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

class LRUCache {
	private int capacity;
	private DLL tail;
	private DLL head;
	private Map<Integer, DLL> cache;
	
	LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
		
		this.tail = new DLL(0,0);
		this.head = new DLL(0,0);
		this.tail.prev = this.head;
		this.head.next = this.tail;
	}

    private void removeNode(DLL node) {
        node.prev.next = node.next;
		node.next.prev = node.prev;
    }
	
	private void moveToFront(DLL node) {
		removeNode(node);
		addToFront(node);
	}
	
	private void addToFront(DLL node) {
		node.next = this.head.next;
		this.head.next.prev = node;
		
		this.head.next = node;
		node.prev = this.head;
	}
	
	public int get(int key) {
		DLL node = this.cache.get(key);
		if(node == null) 
			return -1;
		moveToFront(node);
		return node.val;
	}
	
	public void put(int key, int value) {
		DLL node = cache.get(key);
		if(node != null) {
			node.val = value;
			moveToFront(node);
			return;
		}
		
		if(this.cache.size() >= capacity) {
			DLL lru = this.tail.prev;
			this.cache.remove(lru.key);
			removeNode(lru);
		}
		
        DLL newHead = new DLL(key,value);
		this.cache.put(key, newHead);
		addToFront(newHead);
	}
}
