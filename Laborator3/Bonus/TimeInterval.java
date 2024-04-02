import java.time.LocalTime;
import java.util.Objects;

public class TimeInterval extends Pair<LocalTime,LocalTime> {
    private LocalTime openHour;
    private LocalTime closeHour;

    public TimeInterval(LocalTime openHour, LocalTime closeHour) {
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public LocalTime getOpenHour() {
        return openHour;
    }

    public void setOpenHour(LocalTime openHour) {
        this.openHour = openHour;
    }

    public LocalTime getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(LocalTime closeHour) {
        this.closeHour = closeHour;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "openHour=" + openHour +
                ", closeHour=" + closeHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeInterval interval = (TimeInterval) o;
        return openHour == interval.openHour && closeHour == interval.closeHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(openHour, closeHour);
    }
}
