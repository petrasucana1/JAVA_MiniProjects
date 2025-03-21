import java.time.*;
import java.util.*;
public class Trip implements Payable{
    private String townName;
    private TripPeriod periodOfTime;
    private List<Attraction> attractions;
    private double price;

    public Trip(String townName, TripPeriod periodOfTime, List<Attraction> attractions, double price) {
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

    public TripPeriod getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(TripPeriod periodOfTime) {
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
            if(attraction instanceof Visitable ) {
                LocalTime openingHour = ((Visitable) attraction).getOpeningHour(day);
                // Verificăm dacă openingHour este null
                if (openingHour != null) {
                    visitableAttractions.add(attraction);
                }
            }
        }

        visitableAttractions.sort((attraction1, attraction2) -> {
            LocalTime openingHour1 = ((Visitable) attraction1).getOpeningHour(day);
            LocalTime openingHour2 = ((Visitable) attraction2).getOpeningHour(day);
            return openingHour1.compareTo(openingHour2);
        });

        return visitableAttractions;
    }

    public List<Attraction> sortByPopularity(DayOfWeek day){
        List<Attraction> visitableAttractions= sortVisitableAttractions(day);
        visitableAttractions.sort((attraction1, attraction2) -> {

                int popularity1=attraction1.getPopularity();
                int popularity2=attraction2.getPopularity();
                return Integer.compare(popularity2,popularity1);

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
