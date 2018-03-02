package P3;

public class Person {
    private String name = "";
    private int id = -1;

    public Person(String name){
        this.name  = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
