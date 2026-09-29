public class Student {
    String id, name, programme;
    int marks;
    public Student(String id, String name, String programme, int marks) {
        this.id=id; this.name=name; this.programme=programme; this.marks=marks;
    }
    public String toString() {
        return String.format("ID: %-12s Name: %-20s Programme: %-20s Marks: %d", id, name, programme, marks);
    }
}