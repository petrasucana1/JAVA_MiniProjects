import java.util.Objects;

public class Statue extends Attraction implements Visitable {
    private Interval timetable;

    public Statue(String town, String description, String statueName, Interval timetable) {
        super(town, description, statueName);
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
