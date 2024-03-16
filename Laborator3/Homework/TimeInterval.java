import java.util.Objects;

public class TimeInterval<T> {
    private T openHour;
    private T closeHour;

    public TimeInterval(T openHour, T closeHour) {
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public T getOpenHour() {
        return openHour;
    }

    public void setOpenHour(T openHour) {
        this.openHour = openHour;
    }

    public T getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(T closeHour) {
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
