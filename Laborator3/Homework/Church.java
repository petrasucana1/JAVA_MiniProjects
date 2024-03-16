import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class Church extends Attraction implements Visitable{

    private Map<DayOfWeek, TimeInterval<LocalTime>> timetable;

    public Church(String town, String charchName, String description, Map<DayOfWeek, TimeInterval<LocalTime>> timetable) {
        super(town,charchName, description);
        this.timetable = timetable;
    }

    @Override
    public Map<DayOfWeek, TimeInterval<LocalTime>> getVisitingTimetable() {
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
