import java.time.DayOfWeek;
import java.util.Map;
import java.util.Objects;

public class Concert extends Attraction implements  Visitable, Payable{
    private double price;
    private Map<DayOfWeek, TimeInterval> timetable;


    public Concert(String town,String singer, String description,int colorCode, int popularity, Map<DayOfWeek, TimeInterval> timetable,  double price) {
        super(town,singer,description, colorCode, popularity);
        this.price = price;
        this.timetable=timetable;
    }

    @Override
    public Map<DayOfWeek, TimeInterval> getVisitingTimetable() {
        return timetable;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Concert concert = (Concert) o;
        return Double.compare(price, concert.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }

    @Override
    public String toString() {
        return "Concert{" +
                "price=" + price +
                ", timetable=" + timetable +
                '}';
    }
}
