import java.util.*;
public class StudentLinkedList {
    private static class Node { Student data; Node next; Node(Student d){data=d;} }
    private Node head;
    public void add(Student s) {
        Node n=new Node(s); if(head==null){head=n;return;}
        Node cur=head; while(cur.next!=null) cur=cur.next; cur.next=n;
    }
    public boolean remove(String id) {
        Node cur=head, prev=null;
        while(cur!=null) {
            if(cur.data.id.equalsIgnoreCase(id)) {
                if(prev==null) head=cur.next; else prev.next=cur.next; return true;
            }
            prev=cur; cur=cur.next;
        } return false;
    }
    public Student find(String id) {
        Node cur=head; while(cur!=null){if(cur.data.id.equalsIgnoreCase(id)) return cur.data;cur=cur.next;} return null;
    }
    public void display() {
        System.out.println("\n--- STUDENT RECORDS (LINKED LIST) ---");
        if(head==null){System.out.println("No student records.");return;}
        Node cur=head; while(cur!=null){System.out.println(cur.data);cur=cur.next;}
    }
    public List<Student> toList(){ List<Student> list=new ArrayList<>(); Node c=head; while(c!=null){list.add(c.data);c=c.next;} return list; }
}