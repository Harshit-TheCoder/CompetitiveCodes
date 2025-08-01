package Stack;

import java.util.*;

class LFUCache {
    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
            freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addToFront(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLeastRecent() {
            if (size == 0) return null;
            Node node = tail.prev;
            remove(node);
            return node;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    int capacity, minFreq;
    Map<Integer, Node> keyToNode;
    Map<Integer, DoublyLinkedList> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;
        Node node = keyToNode.get(key);
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }

        if (keyToNode.size() >= capacity) {
            DoublyLinkedList minList = freqToList.get(minFreq);
            Node toRemove = minList.removeLeastRecent();
            keyToNode.remove(toRemove.key);
        }

        Node newNode = new Node(key, value);
        keyToNode.put(key, newNode);
        freqToList.computeIfAbsent(1, k -> new DoublyLinkedList()).addToFront(newNode);
        minFreq = 1;
    }

    private void updateFreq(Node node) {
        int freq = node.freq;
        DoublyLinkedList oldList = freqToList.get(freq);
        oldList.remove(node);

        if (freq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;
        freqToList.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).addToFront(node);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println(cache.get(1)); // 10
        cache.put(3, 30); // evicts 2
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 30
    }
}

