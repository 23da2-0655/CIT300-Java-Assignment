import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentLinkedList students = new StudentLinkedList();
    private static final ActionStack actions = new ActionStack();
    private static final ServiceQueue requests = new ServiceQueue();
    private static final StudentBST bst = new StudentBST();
    private static final StudentHashTable hash = new StudentHashTable();
    private static final CampusGraph graph = new CampusGraph();

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT & CAMPUS ROUTE SYSTEM");
        System.out.println("==============================================");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> updateStudent();
                    case 3 -> deleteStudent();
                    case 4 -> students.display();
                    case 5 -> addRequest();
                    case 6 -> requests.process();
                    case 7 -> actions.display();
                    case 8 -> bst.displayInOrder();
                    case 9 -> searchHash();
                    case 10 -> addLocation();
                    case 11 -> graph.removeLocation(readText("Location: "));
                    case 12 -> addConnection();
                    case 13 -> removeConnection();
                    case 14 -> graph.display();
                    case 15 -> traverse();
                    case 16 -> running = false;
                    default -> System.out.println("Invalid menu choice.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
            if (running) pause();
        }
        System.out.println("Thank you. Program ended.");
    }

    private static void printMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
        System.out.println("------------------------------------------");
    }

    private static void addStudent() {
        String id = readText("Student ID: ");
        if (!validId(id) || hash.contains(id)) { System.out.println("Invalid or duplicate Student ID."); return; }
        String name = readText("Name: ");
        String programme = readText("Programme: ");
        int marks = readMarks();
        Student s = new Student(id, name, programme, marks);
        students.add(s); bst.insert(s); hash.put(s);
        actions.push("Added student " + id);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        String id = readText("Student ID to update: ");
        Student s = hash.get(id);
        if (s == null) { System.out.println("Student not found."); return; }
        String name = readText("New name: ");
        String programme = readText("New programme: ");
        int marks = readMarks();
        s.name = name; s.programme = programme; s.marks = marks;
        bst.rebuild(students); hash.put(s);
        actions.push("Updated student " + id);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        String id = readText("Student ID to delete: ");
        Student s = hash.get(id);
        if (s == null) { System.out.println("Student not found."); return; }
        students.remove(id); hash.remove(id); bst.rebuild(students);
        actions.push("Deleted student " + id);
        System.out.println("Student deleted successfully.");
    }

    private static void addRequest() {
        String id = readText("Student ID: ");
        if (!hash.contains(id)) { System.out.println("Student not found."); return; }
        String request = readText("Service request: ");
        requests.add(id + " - " + request);
        actions.push("Added service request for " + id);
    }

    private static void searchHash() {
        String id = readText("Student ID to search: ");
        Student s = hash.get(id);
        System.out.println(s == null ? "Student not found." : "Found: " + s);
    }

    private static void addLocation() {
        String name = readText("Campus location: ");
        if (graph.addLocation(name)) System.out.println("Location added.");
        else System.out.println("Location already exists.");
    }

    private static void addConnection() {
        String a = readText("First location: ");
        String b = readText("Second location: ");
        if (graph.addConnection(a, b)) System.out.println("Connection added.");
        else System.out.println("Check locations; connection may already exist.");
    }

    private static void removeConnection() {
        String a = readText("First location: ");
        String b = readText("Second location: ");
        if (graph.removeConnection(a, b)) System.out.println("Connection removed.");
        else System.out.println("Connection not found.");
    }

    private static void traverse() {
        String start = readText("Starting location: ");
        graph.bfs(start);
    }

    private static int readMarks() {
        while (true) {
            int m = readInt("Marks (0-100): ");
            if (m >= 0 && m <= 100) return m;
            System.out.println("Marks must be between 0 and 100.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String x = sc.nextLine().trim();
            try { return Integer.parseInt(x); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String x = sc.nextLine().trim();
            if (!x.isEmpty()) return x;
            System.out.println("Input cannot be empty.");
        }
    }

    private static boolean validId(String id) { return id.matches("[A-Za-z0-9_-]{2,20}"); }

    private static void pause() { System.out.println("\nPress Enter to continue..."); sc.nextLine(); }
}