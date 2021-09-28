import java.util.ArrayList;
import java.util.List;

public class MyQueue {

    int count = 0;
    Node head;

    public MyQueue(){

    }

    public void add(Object obj){
        if(head == null){
            head = new Node(obj);
            count++;
        }
        else if(obj == null){
            return;
        }
        else {
            Node tempHead = head;
            while(tempHead.getNext() != null){
                tempHead = tempHead.getNext();
            }
            tempHead.setNext(new Node(obj));
            tempHead.getNext().setNext(null);
            count++;
        }
    }

    public synchronized Node remove (){
        if(this.size() > 0){
            Node removedNode = head;
            head = head.getNext();
            count--;
            return removedNode;
        }
        else{
            return null;
        }
    }


    public boolean isEmpty(){
        if(head == null){
            return true;
        }
        else {
            return false;
        }
    }

    public int size(){
        return count;
    }


    public Node peek(){
        if(head != null){
            return head;
        }
        else{
            return null;
        }
    }
    



}
