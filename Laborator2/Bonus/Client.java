import java.util.Objects;

/**
 * The class Client represents a client who want to be visited by a vehicle
 * @author Petrasuc Ana
 */
public class Client {
    private String name;
    /**
     * Time interval in which client wants to be visited (the start time and end time must be between 10 and 22)
     */
    private TimeInterval visitingTimeInterval;
    /**
     * The client could be premium or regular.
     */
    private ClientType clientType;

    private Location clientLocation;
    /**
     * Client constructor with all fields as parameters
     * @param name Client's name
     * @param visitingTimeInterval Client's disponibility (when he can be visited)
     * @param clientType Client's type(premium/regular)
     */
    public Client(String name, TimeInterval visitingTimeInterval, ClientType clientType,Location clientLocation) {
        this.name = name;
        this.visitingTimeInterval = visitingTimeInterval;
        this.clientType = clientType;
        this.clientLocation=clientLocation;
    }

    /**
     * This method returns the client's name
     * @return client's name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the visiting time interval in which the client is disponible
     * @return The visiting time interval (the start time and end time are between 10 and 22)
     */
    public TimeInterval getVisitingTimeInterval() {
        return visitingTimeInterval;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public Location getClientLocation() {
        return clientLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisitingTimeInterval(TimeInterval visitingTimeInterval) {
        this.visitingTimeInterval = visitingTimeInterval;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", visitingTimeInterval=" + visitingTimeInterval +
                ", clientType=" + clientType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(visitingTimeInterval, client.visitingTimeInterval) && clientType == client.clientType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, visitingTimeInterval, clientType);
    }
}
