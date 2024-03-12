import java.util.*;

/**
 *The abstract class Vehicle represents a vehicle in the vehicle routing system.
 * It serves as a base class for specific vehicle types lyke Track and Drone.
 * @author Petrasuc Ana
 */
public abstract class Vehicle {
    private String name;
    private Depot depot;
    private List<Client> clients;

    /**
     * The current time h-(the hour's part) of the vehicle during its tour.
     */
    int time;
    /**
     *The current time -m(the minutes' part) of the vehicle during its tour
     */
    int minutes;

    public Vehicle(String name, Depot depot) {
        this.name = name;
        this.depot = depot;
        this.clients= new ArrayList<>();
        this.time= 10;
        this.minutes=0;
    }

    public String getName() {
        return name;
    }

    public Depot getDepot() {
        return depot;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void printClientsName(){
        for(Client client: clients)
            System.out.print(client.getName() + " ");
        System.out.println();

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                ", clients=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot) && Objects.equals(clients, vehicle.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depot, clients);
    }
}
