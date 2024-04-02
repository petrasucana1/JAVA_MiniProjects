import java.util.*;


public class Main {
    public static void main(String[] args) {

        //time intervals creation for clients
        TimeInterval interval1 = new TimeInterval(10, 14);
        TimeInterval interval2 = new TimeInterval(10, 15);
        TimeInterval interval3 = new TimeInterval(12, 18);
        TimeInterval interval4 = new TimeInterval(10, 11);
        TimeInterval interval5 = new TimeInterval(17, 21);
        TimeInterval interval6 = new TimeInterval(14, 18);
        TimeInterval interval7 = new TimeInterval(12, 20);
        TimeInterval interval8 = new TimeInterval(11, 17);
        TimeInterval interval9 = new TimeInterval(17, 18);
        TimeInterval interval0 = new TimeInterval(14, 15);


        //clients creation
        Client client1 = new Client("C1", interval1, ClientType.REGULAR);
        Client client2 = new Client("C2", interval2, ClientType.PREMIUM);
        Client client3 = new Client("C3", interval3, ClientType.REGULAR);
        Client client4 = new Client("C4", interval4, ClientType.REGULAR);
        Client client5 = new Client("C5", interval5, ClientType.PREMIUM);
        Client client6 = new Client("C6", interval6, ClientType.REGULAR);
        Client client7 = new Client("C7", interval7, ClientType.REGULAR);
        Client client8 = new Client("C8", interval8, ClientType.REGULAR);
        Client client9 = new Client("C9", interval9, ClientType.PREMIUM);
        Client client0 = new Client("C0", interval0, ClientType.REGULAR);


        //depots and vehicles creation
        Depot depot1 = new Depot("Depot1");
        Depot depot2 = new Depot("Depot2");

        Truck truck1 = new Truck("Truck1", depot1, 50);
        Truck truck2 = new Truck("Truck2", depot2, 100);
        Drone drone1 = new Drone("Drone1", depot2, 120);

        depot1.addVehicle(truck1);
        depot2.addVehicle(truck2);
        depot2.addVehicle(drone1);


        //create lists of clients, depots and vehicles
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        clients.add(client6);
        clients.add(client7);
        clients.add(client8);
        clients.add(client9);
        clients.add(client0);

        List<Depot> depots = new ArrayList<>();
        depots.add(depot1);
        depots.add(depot2);


        //problem instance creation
        Problem problem = new Problem(depots, clients);
        List<Vehicle> vehicles = problem.getVehicles();

        //solution instance creation
        Solution solution = new Solution(clients, vehicles);

        //clients sort
        solution.sortClients();
        solution.allocateClients();

        //print data
        for (Client client : clients)
            System.out.println(client.getName() + " : [" + client.getVisitingTimeInterval().getStartTime() + "," + client.getVisitingTimeInterval().getEndTime() + "]");
        System.out.println();

        //print solution
        System.out.println("Today, vehicles has the following clients:");
        for (Vehicle vehicle : vehicles) {
            System.out.print("Vehicle " + vehicle.getName() + ": ");
            vehicle.printClientsName();
        }

    }
}