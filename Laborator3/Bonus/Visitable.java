import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public interface Visitable {

    Map<DayOfWeek, TimeInterval> getVisitingTimetable();

    default LocalTime getOpeningHour(DayOfWeek day){
        if(getVisitingTimetable().containsKey(day))
            return getVisitingTimetable().get(day).getOpenHour();
        return null;
    }
}
