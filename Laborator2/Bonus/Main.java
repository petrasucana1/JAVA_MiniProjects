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

        //clients location
        Location location1= new Location(2,4);
        Location location2= new Location(3,5);
        Location location3= new Location(1,2);
        Location location4= new Location(3,4);
        Location location5= new Location(3,3);
        Location location6= new Location(1,3);
        Location location7= new Location(2,5);
        Location location8= new Location(1,1);
        Location location9= new Location(4,5);
        Location location0= new Location(1,5);


        //clients creation
        Client client1 = new Client("C1", interval1, ClientType.REGULAR,location1);
        Client client2 = new Client("C2", interval2, ClientType.PREMIUM,location2);
        Client client3 = new Client("C3", interval3, ClientType.REGULAR,location3);
        Client client4 = new Client("C4", interval4, ClientType.REGULAR,location4);
        Client client5 = new Client("C5", interval5, ClientType.PREMIUM,location5);
        Client client6 = new Client("C6", interval6, ClientType.REGULAR,location6);
        Client client7 = new Client("C7", interval7, ClientType.REGULAR,location7);
        Client client8 = new Client("C8", interval8, ClientType.REGULAR,location8);
        Client client9 = new Client("C9", interval9, ClientType.PREMIUM,location9);
        Client client0 = new Client("C0", interval0, ClientType.REGULAR,location0);

        //depots location
        Location location_d1= new Location(4,2);
        Location location_d2= new Location(3,1);

        //depots and vehicles creation
        Depot depot1 = new Depot("Depot1", location_d1);
        Depot depot2 = new Depot("Depot2",location_d2);

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


        //grid graph creation
        GridGraph graph= new GridGraph(6,6);


        //solution instance creation
        Solution solution = new Solution(clients, vehicles,graph);

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

        //graph.printMatrix();
        System.out.println();

       /* int[] shortestPathDistances = graph.dijkstra(1,1);
        int destinationNode=2*5+4;
        int shortestPathDistance=shortestPathDistances[destinationNode];
        System.out.println("Distance from (1,1) to (2,4): "+shortestPathDistance);
        */





























































        
        printGraph(clients,depots, graph.getCols(), graph.getRows());


    }
    static void printGraph(List<Client> clients, List<Depot> depots, int cols, int rows)
     {
         System.out.print(" ");
         for(int j=0;j<cols;j++)
             System.out.print("  "+j);
         System.out.println("           \u001B[31m \u25CF \u001B[0m clients  \u001B[33m \u25CF \u001B[0m depots ");

         for(int i=0;i<rows;i++) {
             System.out.println("   :  :  :  :  :  :");
             System.out.print(i+"--");
             for (int j = 0; j < cols; j++) {
                 int ok = 0;
                 for (Client client : clients) {
                     if (client.getClientLocation().getX() == i && client.getClientLocation().getY() == j) {
                         System.out.print("\u001B[31m");
                         System.out.print('\u25CF');
                         System.out.print("\u001B[0m");
                         System.out.print("--");
                         ok = 1;
                     }
                 }
                 for(Depot depot: depots)
                     if (depot.getDepotLocation().getX() == i && depot.getDepotLocation().getY() == j) {
                         System.out.print("\u001B[33m");
                         System.out.print('\u25CF');
                         System.out.print("\u001B[0m");
                         System.out.print("--");
                         ok = 1;
                    }
                 if (ok == 0)
                     System.out.print('\u25CF' + "--");
             }
             System.out.println();
         }
         System.out.println("   :  :  :  :  :  :");
     }
}