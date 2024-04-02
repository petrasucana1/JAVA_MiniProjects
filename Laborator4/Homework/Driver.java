package org.example;

public class Driver extends Person{

    private Passenger passenger;
    private boolean status;

    public Driver(String name, int age, String destination, int route) {
        super(name, age, destination, route);
        this.passenger=null;
        this.status=true;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
