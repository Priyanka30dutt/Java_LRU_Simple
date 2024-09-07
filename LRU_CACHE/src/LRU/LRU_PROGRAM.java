package LRU;

import java.util.HashMap;

public class LRU_PROGRAM {
    private int capacity; // Maximum size of cache
    private HashMap<Integer, Node> map = new HashMap<>(); // Stores key-node pair
    private Node head, tail; // Dummy head and tail nodes for the doubly linked list

    // Constructor
    public LRU_PROGRAM(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0); // Initialize head node
        tail = new Node(0, 0); // Initialize tail node
        head.next = tail; // Connect head to tail
        tail.prev = head; // Connect tail to head
        System.out.println("Cache initialized with capacity: " + capacity);
    }

    // Get the value of the key if present, else return -1
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            // Move accessed node to the head
            remove(node);
            insert(node);
            printCache("After get(" + key + "): ");
            return node.value;
        } else {
            System.out.println("Key " + key + " not found.");
            return -1; // Key not found
        }
    }

    // Put a key-value pair into the cache
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Remove the old node
            remove(map.get(key));
        } else if (map.size() == capacity) {
            // Remove the least recently used node (the one right before the tail)
            map.remove(tail.prev.key);
            remove(tail.prev);
        }
        // Insert the new node right after the head
        Node newNode = new Node(key, value);
        insert(newNode);
        map.put(key, newNode);
        printCache("After put(" + key + ", " + value + "): ");
    }

    // Remove a node from the doubly linked list
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node right after the head
    private void insert(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Print the current state of the cache
    public void printCache(String message) {
        System.out.print(message);
        Node current = head.next; // Start after the dummy head node
        while (current != tail) { // Traverse till the dummy tail node
            System.out.print("(" + current.key + "," + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
        // If the new capacity is smaller, evict the least recently used items
        while (map.size() > newCapacity) {
            map.remove(tail.prev.key);
            remove(tail.prev);
        }
        System.out.println("Capacity set to: " + newCapacity);
        printCache("Cache after resizing: ");
    }

    public int getSize() {
        return map.size();
    }

    public void removeKey(int key) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            map.remove(key);
            printCache("After removing key " + key + ": ");
        } else {
            System.out.println("Key " + key + " not found.");
        }
    }

    public int getLRU() {
        if (tail.prev != head) { // Check if the list is not empty
            return tail.prev.value; // Return value of the node right before the tail
        }
        return -1; // If no items in cache
    }

    public int getMRU() {
        if (head.next != tail) { // Check if the list is not empty
            return head.next.value; // Return value of the node right after the head
        }
        return -1; // If no items in cache
    }

    public void clearCache() {
        map.clear();
        head.next = tail;
        tail.prev = head;
        System.out.println("Cache cleared.");
        printCache("Cache after clearing: ");
    }

    
    
}
