import java.util.*;

/**
 * Class Depot represents a depot which has a number of vehicles (the depot is the start point and end point of every vehicle tour)
 * @author Petrasuc Ana
 */
public class Depot {
    private String name;
    private List<Vehicle> vehicles;

    public Depot(String name) {
        this.name = name;
        this.vehicles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(name, depot.name) && Objects.equals(vehicles, depot.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vehicles);
    }
}
