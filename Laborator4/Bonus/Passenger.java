package org.example;


public class Passenger extends Person{
    public Passenger(String name, int age, String destination, int route) {

        super(name, age, destination, route);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", destination='" + getDestination() + '\'' +
                '}';
    }
}
