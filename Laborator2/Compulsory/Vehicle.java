import java.util.*;
class Vehicle {
    private String name;
    private Depot depot;
    private List<Client> clients;

    public Vehicle(String name, Depot depot, List<Client> clients) {
        this.name = name;
        this.depot = depot;
        this.clients = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", depot=" + depot +
                ", clients=" + clients +
                '}';
    }
}
