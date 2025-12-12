public abstract class Person {
    private String name;
    private String section;

    public Person(String name, String section) {
        this.name = name;
        this.section = section;
    }

    // Encapsulation
    public String getName() { return name; }
    public String getSection() { return section; }

    // Polymorphism
    public abstract void displayRole();
}
