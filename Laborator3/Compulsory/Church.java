import java.util.Objects;

public class Church extends Attraction implements Visitable{

    private Interval timetable;

    public Church(String town, String description, String charchName, Interval timetable) {
        super(town, description,charchName);
        this.timetable = timetable;
    }

    @Override
    public Interval getTodayTimetable() {
        return timetable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Church church = (Church) o;
        return Objects.equals(timetable, church.timetable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timetable);
    }

    @Override
    public String toString() {
        return "Church{" +
                "timetable=" + timetable +
                '}';
    }
}
