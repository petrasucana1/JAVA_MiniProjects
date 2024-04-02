package org.example;

public abstract class Person {
    private String name;
    private int age;
    private String destination;

    private int route;

    public Person(String name, int age, String destination, int route) {
        this.name = name;
        this.age = age;
        this.destination = destination;
        this.route=route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", destination='" + destination + '\'' +
                ", route=" + route +
                '}';
    }
}
