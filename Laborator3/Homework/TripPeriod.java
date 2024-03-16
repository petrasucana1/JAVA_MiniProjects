import java.time.DayOfWeek;

public class TripPeriod<K,V> {
    K startDay;
    V numberOfDays;

    public TripPeriod(K startDay, V numberOfDays) {
        this.startDay = startDay;
        this.numberOfDays = numberOfDays;
    }

    public K getStartDay() {
        return startDay;
    }

    public void setStartDay(K startDay) {
        this.startDay = startDay;
    }

    public V getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(V numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
