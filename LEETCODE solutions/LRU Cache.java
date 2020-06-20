class Node{
    int key;
    int value;
    Node next, prev;
    
    Node(){}
    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    Map<Integer, Node> map = new HashMap<>();
    int capacity, cacheSize = 0;
    Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node accessed = map.get(key);
        
        //cache hit
        if(accessed != null){
            //remove element from between the list
            accessed.prev.next = accessed.next;
            accessed.next.prev = accessed.prev;
            
            //to the front of the list
            accessed.next = head.next;
            accessed.prev = head;
            head.next = accessed;
            accessed.next.prev = accessed;
            
            return accessed.value;
        }
        
        return -1;
    }
    
    public void put(int key, int value) {
        //cache update
        Node update = map.get(key);
        if(update!=null){
            update.value = value;
            this.get(key);
            return;
        }
        
        //capacity reached
        if(cacheSize == capacity){
            Node remove = tail.prev;
            remove.prev.next = tail;
            tail.prev = remove.prev;
            cacheSize--;
            
            map.remove(remove.key);
        }
            
        //add node to the front of the list
        Node add = new Node(key, value);
        add.next = head.next;
        add.prev = head;
        head.next = add;
        add.next.prev = add;
            
        map.put(key, add);
        cacheSize++;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */