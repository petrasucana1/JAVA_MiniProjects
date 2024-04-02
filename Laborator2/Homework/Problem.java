import java.util.*;

/**
 * The Problem class represents an instance of the Vehicle Routing Problem (VRP).
 * It consists of depots, vehicles and clients with the goal of allocating clients to vehicles.
 * @author Petrasuc Ana
 */
public class Problem {
    private List<Depot> depots;
    private List<Client> clients;

    public Problem(List<Depot> depots, List<Client> clients) {
        this.depots = depots;
        this.clients = clients;
    }

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }
    public List<Depot> getDepots() {
        return depots;
    }
    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * This method gets the list of vehicles from all depots in the problem.
     * @return The list of all vehicles (from all depots).
     */
    public List<Vehicle> getVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();

        for(Depot depot: depots)
           vehicles.addAll(depot.getVehicles());
        return vehicles;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + depots +
                ", clients=" + clients +
                '}';
    }
}
