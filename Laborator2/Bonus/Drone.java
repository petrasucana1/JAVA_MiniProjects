/**
 * The Drone class represents a drone vehicle in the vehicle routing system.
 * It extends the abstract class Vehicle and includes specific properties for drones.
 * @author Petrasuc Ana
 */
public class Drone extends Vehicle {
    /**
     * The maximum flight duration of the drone, measured in arbitrary units.
     */
    private int maxFlightDuration;

    /**
     * Constructor for Drone with the given name, associated depot and maximum flight duration as parameters.
     * @param name The name of the drone.
     * @param depot The depot to which the drone belongs.
     * @param maxFlightDuration The maximum flight duration of the drone.
     */
    public Drone(String name, Depot depot, int maxFlightDuration) {
        super(name, depot);
        this.maxFlightDuration=maxFlightDuration;
    }

    public int getMaxFlightDuration() {
        return maxFlightDuration;
    }

    public void setMaxFlightDuration(int maxFlightDuration) {
        this.maxFlightDuration = maxFlightDuration;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "maxFlightDuration=" + maxFlightDuration +
                '}';
    }
}
