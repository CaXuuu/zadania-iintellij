import java.util.AbstractList;
import java.util.Iterator;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    private Node <T> head, tail;
    public void addLast(T value) {
        Node <T> created = new Node<>(value);
        if (tail == null) {
            head = created;
        }else{
            tail.next = created;
        }
        tail = created;
    }
    public T getFirst() {
        return head.value;
    }
    public T getLast() {
        return tail.value;
    }
    public void addFirst(T value) {
        Node <T> created = new Node<>(value);
        if (head == null) {
            tail = created;
        }else{
            created.next = head;
        }
        head = created;
    }
    public boolean add(T t){
        addLast(t);
        return true;
    }
    public int size() {
        if(head == null)  return 0;
        Node <T> current = head;
        int index = 1;
        while(current.next != null) {
            current = current.next;
            index++;
        }
        return index;
    }
    public T get(int index) {
        Node <T> current = head;
        for(int i = 0; i < index; i++) {
            try{
                current = current.next;
            }catch(NullPointerException e){
                throw new IndexOutOfBoundsException(e.getMessage());
            }
        }
        return current.value;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private Node <T> node = head;
            public boolean hasNext() {
                return node != null;
            }
            public T next() {
                T current = node.value;
                node = node.next;
                return current;
            }
        };
    }
    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();
        for(T value : this)
            builder.add(value);
        return builder.build();
    }
}
