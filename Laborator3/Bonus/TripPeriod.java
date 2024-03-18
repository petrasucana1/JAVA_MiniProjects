import java.time.DayOfWeek;
import java.time.LocalDate;

public class TripPeriod extends Pair<LocalDate, Integer> {
    LocalDate startDay;
    Integer numberOfDays;

    public TripPeriod(LocalDate startDay, Integer numberOfDays) {
        this.startDay = startDay;
        this.numberOfDays = numberOfDays;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
