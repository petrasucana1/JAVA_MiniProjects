
public class Client {
    private String name;
    private TimeInterval visitingTimeInterval;
    private ClientType clientType;

    public Client(String name, TimeInterval visitingTimeInterval, ClientType clientType) {
        this.name = name;
        this.visitingTimeInterval = visitingTimeInterval;
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public TimeInterval getVisitingTimeInterval() {
        return visitingTimeInterval;
    }

    public ClientType getClientType() {
        return clientType;
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
}
