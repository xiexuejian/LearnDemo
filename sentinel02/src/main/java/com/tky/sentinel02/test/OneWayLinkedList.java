package com.tky.sentinel02.test;

public class OneWayLinkedList<E> implements Container<E> {
    private Node<E> head;
    private int total;

    private static class Node<E>{
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e,null);
        if(head==null){
            head = newNode;
        }else{
            Node<E> node = head;
            while(node.next!=null){
                node = node.next;
            }
            node.next = newNode;
        }
        total++;
    }


    @Override
    public void delete(E e) {
        if(head != null){
            Node<E> node = head;
            Node<E> find = null;
            Node<E> last = null;

            if(e==null){
                while(node!=null){
                    if(node.data==null){
                        find = node;
                        break;
                    }
                    last = node;
                    node = node.next;
                }
            }else{
                while(node!=null){
                    if(e.equals(node.data)){
                        find = node;
                        break;
                    }
                    last = node;
                    node = node.next;
                }
            }

            if(find != null){
                if(last==null){
                    head = find.next;
                }else{
                    last.next = find.next;
                }
                total--;
            }
        }
    }

    @Override
    public void update(E old, E value) {
        if(head != null){
            Node<E> node = head;
            Node<E> find = null;

            if(old==null){
                while(node!=null){
                    if(node.data==null){
                        find = node;
                        break;
                    }
                    node = node.next;
                }
            }else{
                while(node!=null){
                    if(old.equals(node.data)){
                        find = node;
                        break;
                    }
                    node = node.next;
                }
            }

            if(find != null){
                find.data = value;
            }
        }
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(E e) {
        int index = -1;
        if(head!=null){
            if(e==null){
                int i=0;
                for(Node<E> node = head; node.next!=null; node=node.next ){
                    if(node.data==e){
                        index=i;
                        break;
                    }
                    i++;
                }
            }else{
                int i=0;
                for(Node<E> node = head; node.next!=null; node=node.next ){
                    if(e.equals(node.data)){
                        index=i;
                        break;
                    }
                    i++;
                }
            }
        }
        return index;
    }

    @Override
    public Object[] getAll() {
        Object[] all = new Object[total];
        Node<E> node = head;
        for (int i = 0; i < all.length; i++) {
            all[i] = node.data;
            node = node.next;
        }
        return all;
    }

    @Override
    public int size() {
        return total;
    }
}