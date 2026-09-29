import java.util.*;
public class ActionStack {
    private final Deque<String> stack=new ArrayDeque<>();
    public void push(String action){stack.push(action);}
    public void display(){
        System.out.println("\n--- RECENT ACTIONS (STACK) ---");
        if(stack.isEmpty()){System.out.println("No actions.");return;}
        for(String s:stack) System.out.println("• "+s);
    }
}