import java.util.*;
public class ServiceQueue {
    private final Queue<String> queue=new LinkedList<>();
    public void add(String request){queue.offer(request);System.out.println("Request added to queue.");}
    public void process(){
        if(queue.isEmpty()) System.out.println("No pending service requests.");
        else System.out.println("Processed: "+queue.poll());
    }
}