package P3;

public class Person {
    private String name = "";
    private int id = -1;

    /* init function of the class Person */
    public Person(String name) {
        this.name = name;
    }

    /* set the private field id of this class */
    public void setId(int id) {
        this.id = id;
    }

    /* get the private field id of this class */
    public int getId() {
        return this.id;
    }

    /* get the private field name of this class */
    public String getName() {
        return this.name;
    }
}
