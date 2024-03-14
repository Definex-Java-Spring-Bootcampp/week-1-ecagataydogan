package model;

public class CustomerDetails {
    private String name;
    private int age;
    public CustomerDetails(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
