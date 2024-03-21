package org.example;

public class Driver extends Person{

    private Passenger passenger;
    private boolean status;

    public Driver(String name, int age, String destination) {
        super(name, age, destination);
        this.passenger=null;
        this.status=true;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", destination='" + getDestination() + '\'' +
                ", passenger=" + passenger + // Afișează pasagerul asociat (dacă există)
                ", status=" + status +
                '}';
    }
}
