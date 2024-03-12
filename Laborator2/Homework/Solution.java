import java.util.*;
import java.util.Collections;

/**
 * The Solution class represents a solution to the Vehicle Routing Problem (VRP).
 * It includes depots, vehicles, clients and specific algorithms for allocating clients to vehicles.
 */
public class Solution {

    private List<Client> allClients;
    private List<Vehicle> allVehicles;

    public Solution(List<Client> allClients, List<Vehicle> allVehicles) {
        this.allClients = allClients;
        this.allVehicles = allVehicles;
    }

    public List<Client> getAllClients() {
        return allClients;
    }

    public List<Vehicle> getAllVehicles() {
        return allVehicles;
    }

    /**
     * This method sorts teh list of all clients in the problem based on their start time and duration of their disponibility (visiting time interval).
     */
    void sortClients() {
        Comparator<Client> byStartTimeAndDuration = Comparator
                .comparing((Client client) -> client.getVisitingTimeInterval().getStartTime())
                .thenComparing(client -> client.getVisitingTimeInterval().getEndTime() - client.getVisitingTimeInterval().getStartTime());

        Collections.sort(allClients, byStartTimeAndDuration);

    }

    /**
     * This method allocates clients to vehicles using a simple greedy algorithm.
     */
    void allocateClients() {
        for (Client client : allClients) {
            Vehicle bestVehicle = findBestVehicle(client);
            if (bestVehicle != null)
                bestVehicle.getClients().add(client);
        }
    }

    /**
     * This method chooses a random vehicle from the list of all vehicles and verify if it can visit a client based on the current time and the time it takes to reach the client(chosen randomly).
     * @param client The client which we want the vehicle to visit.
     * @return The vehicle who can visit the client in time(in the visiting time interval of the client).
     */
    private Vehicle findBestVehicle(Client client) {
        Random random = new Random();
        int count =0;
        while(count != allVehicles.size()) {
            int randomIndex = random.nextInt(allVehicles.size());
            Vehicle vehicle= allVehicles.get(randomIndex);
            if (vehicle.time <= client.getVisitingTimeInterval().getStartTime()) {
                vehicle.minutes += random.nextInt(200) + 1;
                if (vehicle.minutes > 60) {
                    vehicle.time += vehicle.minutes / 60;
                    vehicle.minutes = vehicle.minutes % 60;
                }
                if (vehicle.time <= client.getVisitingTimeInterval().getEndTime())
                    return vehicle;
            }
            count++;
        }
        return null;
    }
}

