/**
 * The Truck class represents a truck vehicle in the vehicle routing system.
 * It extends the abstract class Vehicle and includes specific properties for trucks.
 * @author Petrasuc Ana
 */
public class Truck  extends Vehicle {
    /**
     * The capacity of the track.
     */
    private int capacity;

    /**
     * Truck constructor with the given name, associated depot and capacity as parameters fields.
     * @param name The name of the track.
     * @param depot The depot to which the track belongs.
     * @param capacity The capacity of the track.
     */
    public Truck(String name, Depot depot,int capacity) {
        super(name,depot);
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "capacity=" + capacity +
                '}';
    }
}
