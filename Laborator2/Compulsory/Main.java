import java.util.*;


public class Main {
    public static void main(String[] args) {
            TimeInterval timeInterval = new TimeInterval(8, 12);
            Client regularClient = new Client("RegularClient", timeInterval, ClientType.REGULAR);
            Client premiumClient = new Client("PremiumClient", timeInterval, ClientType.PREMIUM);


            System.out.println(regularClient);
            System.out.println(premiumClient);

            Depot depot = new Depot("Depot1");

            List<Client> interestedClients= new ArrayList<>();

            Vehicle vehicle = new Vehicle("Vehicle1", depot,interestedClients);
            vehicle.addClient(regularClient);
            vehicle.addClient(premiumClient);

            System.out.println(depot);
            System.out.println(vehicle);
        }
}
