    // Inheritance
public class Student extends Person {

    public Student(String name, String section) {
        super(name, section);
    }

    // Polymorphism
    @Override
    public void displayRole() {
        System.out.println("Role: Student | Name: " + getName() + " | Section: " + getSection());
    }
}
