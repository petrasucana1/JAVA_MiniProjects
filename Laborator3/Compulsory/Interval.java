import java.util.Objects;

public class Interval {
    private int openHour;
    private int closeHour;

    public Interval(int openHour, int closeHour) {
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public int getOpenHour() {
        return openHour;
    }

    public void setOpenHour(int openHour) {
        this.openHour = openHour;
    }

    public int getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(int closeHour) {
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
        Interval interval = (Interval) o;
        return openHour == interval.openHour && closeHour == interval.closeHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(openHour, closeHour);
    }
}
