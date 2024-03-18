import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class Statue extends Attraction implements Visitable {
    private Map<DayOfWeek, TimeInterval> timetable;

    public Statue(String town, String statueName,String description,  Map<DayOfWeek, TimeInterval> timetable) {
        super(town,statueName, description);
        this.timetable = timetable;
    }



    @Override
    public Map<DayOfWeek, TimeInterval> getVisitingTimetable() {
        return timetable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Statue statue = (Statue) o;
        return Objects.equals(timetable, statue.timetable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timetable);
    }

    @Override
    public String toString() {
        return "Statue{" +
                "timetable=" + timetable +
                '}';
    }
}
