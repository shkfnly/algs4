import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> { 
    private Node head = null;
    private Node tail = null;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    };
    public Deque(){// construct an empty deque
       head = new Node();
       tail = new Node();
       head.item = null;
       head.prev = null;
       head.next = tail;
       tail.item = null;
       tail.prev = head;
       tail.next = null;
    };
    public boolean isEmpty(){// is the deque empty?
        return head.next.item == null;
    };
    public int size(){// return the number of items on the deque
        int count = 0;
        Node currNode = head;
        while (currNode.next.item != null) {
            count++;
            currNode = currNode.next;
        }
        return count;
    };
    public void addFirst(Item item){// add the item to the front
        if (item == null){
            throw new NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
    };
    public void addLast(Item item){// add the item to the end
        if (item == null){
            throw new NullPointerException();
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;
    };
    public Item removeFirst(){// remove and return the item from the front
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Item result = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        return result;
    };
    public Item removeLast(){// remove and return the item from the end
        if (isEmpty()){
            throw new java.util.NoSuchElementException();
        }
        Item result = tail.prev.item;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        return result;
    };
    public Iterator<Item> iterator(){// return an iterator over items in order
        return new DequeIterator();
    };
    private class DequeIterator implements Iterator<Item> {
        private Node current = head.next;
        
        public boolean hasNext() {
            return current.next.item != null;
        };
        public void remove() {
            throw new UnsupportedOperationException();
        };
        public Item next(){
           if(!hasNext()){
               throw new java.util.NoSuchElementException();
           }
          Item item = current.item;
          current = current.next;
          System.out.println(current.next.item);
          return item;
          
        }
    };
    public static void main(String[] args){// unit testing
        Deque deque = new Deque();
        String a = "hello";
        String b = "world";
        String c = "sex";
        deque.addFirst(b);
        deque.addLast(c);
        deque.addFirst(a);
        
        for (Object s : deque) {
        }
        
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());

        System.out.println(deque.size());

    };
}