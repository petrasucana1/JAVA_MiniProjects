package org.example;


public class Passenger extends Person{
    public Passenger(String name, int age, String destination) {
        super(name, age, destination);
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
