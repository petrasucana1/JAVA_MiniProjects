import java.time.*;
import java.util.*;
public class Trip implements Payable{
    private String townName;
    private TripPeriod<DayOfWeek,Integer> periodOfTime;
    private List<Attraction> attractions;
    private double price;

    public Trip(String townName, TripPeriod<DayOfWeek,Integer> periodOfTime, List<Attraction> attractions, double price) {
        this.townName = townName;
        this.periodOfTime = periodOfTime;
        this.attractions = attractions;
        this.price = price;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public TripPeriod<DayOfWeek,Integer> getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(TripPeriod<DayOfWeek,Integer> periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void addAttraction(Attraction attraction){
        attractions.add(attraction);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public List<Attraction> sortVisitableAttractions(DayOfWeek day){
        List<Attraction> visitableAttractions= new ArrayList<>();
        for(Attraction attraction: attractions)
        {
            if(attraction instanceof Visitable )
                    visitableAttractions.add(attraction);
        }

        visitableAttractions.sort((attraction1, attraction2) -> {
            LocalTime openingHour1 = ((Visitable) attraction1).getOpeningHour(day);
            LocalTime openingHour2 = ((Visitable) attraction2).getOpeningHour(day);
            return openingHour1.compareTo(openingHour2);
        });

        return visitableAttractions;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "townName='" + townName + '\'' +
                ", periodOfTime=" + periodOfTime +
                ", attractions=" + attractions +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return periodOfTime == trip.periodOfTime && Double.compare(price, trip.price) == 0 && Objects.equals(townName, trip.townName) && Objects.equals(attractions, trip.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(townName, periodOfTime, attractions, price);
    }
}
