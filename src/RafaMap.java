import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RafaMap<K, V> implements Map {

    private static final int CAPACITY = 100;
    private Node[] table = new Node[CAPACITY];
    private int size = 0;
    transient Set<Entry<K, V>> entrySet;

    public Node[] getTable(){
        return this.table;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object K) {
        Node foundNode = this.findNode(K);

        return foundNode != null;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }

    @Override
    public Object get(Object K) {

        Node foundNode = this.findNode(K);
        try {
        return foundNode == null ? null :  foundNode.getValue();
        } catch (NullPointerException e) {
            System.err.println("No such key in map");
        }
        return null;
    }

    @Override
    public Object put(Object K, Object V) {

        int hashCode = Objects.hashCode(K);
        int targetIndex = this.getTargetIndex(hashCode);

        if (table[targetIndex] == null){ //si la posicion en el arreglo esta vacia agrega un nuevo nodo
            table[targetIndex] = new RafaMap.Node<>(hashCode, K, V, null);
            this.incrementSize(1);
        } else {
            Node node = table[targetIndex];
            if (node.getKey().equals(K)) {
                node.setValue(V);
                return null;
            }
            while (node.getNext() != null) {
                if (node.getKey().equals(K)) {
                    node.setValue(V);
                    return null;
                }
                node = node.getNext();
            }
            node.setNext(new RafaMap.Node<>(hashCode, K, V, null));
            this.incrementSize(1);
        }
        return null;
    }

    @Override
    public Object remove(Object K) {

        int hashCode = Objects.hashCode(K);
        int targetIndex = this.getTargetIndex(hashCode);

        Node prevNode = this.findPrevNode(K);

        try {
            if (prevNode.getKey().equals(table[targetIndex].getKey())){
                table[targetIndex] = prevNode.getNext();

            } else {
                prevNode.setNext(prevNode.getNext().getNext());
            }
            this.decrementSize(1);
        } catch (NullPointerException e){
            System.err.println("No such key in map");
        }
        return null;
    }

    @Override
    public void putAll(Map map) {

    }

    @Override
    public void clear() {
        table = new Node[CAPACITY];
        this.size = 0;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    static class Node<K, V> implements Entry<K, V>{

        final int hash;
        final K key;
        V value;
        RafaMap.Node<K, V> next;

        Node(int hash, K key, V value, RafaMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {
            this.value = v;
            return null;
        }

        Node<K, V> getNext(){
            return this.next;
        }

        private void setNext(Node<K, V> node){
            this.next = node;
        }


    }

    private int getTargetIndex(int hashCode){
        return hashCode % CAPACITY;
    }

    private void incrementSize(int i){
        this.size += i;
    }

    private void decrementSize(int i){
        this.size -= i;
    }

    private Node findNode(Object K){
        int hashCode = Objects.hashCode(K);
        int targetIndex = this.getTargetIndex(hashCode);
        Node result = null;

        try{
            result = table[targetIndex];
            while (!result.getKey().equals(K)){
                result = result.getNext();
            }
        } catch (NullPointerException e){
            result = null;
        }
        return result;
    }

    private Node findPrevNode(Object K){
        int hashCode = Objects.hashCode(K);
        int targetIndex = this.getTargetIndex(hashCode);
        Node result = null;

        try{
            result = table[targetIndex];
            if (result.getKey().equals(K)){ //If the node is in the first position return it
                return result;
            } else {
                while (result.getNext() != null){ //While there is a next node
                    if (result.getNext().getKey() == K){ //Return the current node if the next one has the desired key
                        return result;
                    } else {
                        result = result.getNext(); //Swap to the next node
                    }
                }
                result = null;
            }
        } catch (NullPointerException e){
            result = null;
        }
        return result;
    }

}
