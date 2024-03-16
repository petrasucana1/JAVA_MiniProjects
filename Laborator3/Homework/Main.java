//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        //Set the timetable of each day of the week for each attraction

        Map<DayOfWeek, TimeInterval<LocalTime>> church1Timetable = new HashMap<>();
        for(DayOfWeek day: DayOfWeek.values()){
            church1Timetable.put(day, new TimeInterval<>(LocalTime.of(9,0),LocalTime.of(17,0)));
        }

        Map<DayOfWeek, TimeInterval<LocalTime>> church2Timetable = new HashMap<>();
        for(DayOfWeek day: DayOfWeek.values()){
            church2Timetable.put(day, new TimeInterval<>(LocalTime.of(9,0),LocalTime.of(17,0)));
        }

        Map<DayOfWeek, TimeInterval<LocalTime>> statue1Timetable= new HashMap<>();
        for(DayOfWeek day: DayOfWeek.values()){
            statue1Timetable.put(day,new TimeInterval<>(LocalTime.of(8,30), LocalTime.of(14, 0)));
        }

        Map<DayOfWeek, TimeInterval<LocalTime>> statue2Timetable= new HashMap<>();
        for(DayOfWeek day: DayOfWeek.values()){
            statue2Timetable.put(day, new TimeInterval<>(LocalTime.of(10,0), LocalTime.of(19,30)));
        }


        //set the attractions

        Church church1= new Church("New York", "St. Patrick's Cathedral","Historic cathedral in Manhattan", church1Timetable);
        Church church2= new Church("New York", "Trinity Church","Important site during the American Revolution", church2Timetable);
        Statue statue1= new Statue("New York","Statue Of Liberty","Iconic symbol in New York City", statue1Timetable);
        Statue statue2= new Statue("New York", "ATLAS statue","Symbolic representation of reigning superiority and strength", statue2Timetable);
        Concert concert1= new Concert("New York", "Inna","Romanian singer",123.00);
        Concert concert2= new Concert("New York", "Alicia Keys","Grammy-winning singer-songwriter",530.00);


        List<Attraction> attractions= new ArrayList<>();

        attractions.add(church1);
        attractions.add(church2);
        attractions.add(statue1);
        attractions.add(statue2);
        attractions.add(concert1);
        attractions.add(concert2);


        //set the trip
        Trip trip1=new Trip("New York",new TripPeriod<>(DayOfWeek.MONDAY, 6), attractions, 500.50);


        //set a travel plan for the trip
        TravelPlan travelPlanTrip1= new TravelPlan(trip1);
        DayOfWeek day=trip1.getPeriodOfTime().getStartDay();
        Integer count=0;
        while(count < trip1.getPeriodOfTime().getNumberOfDays()){
            travelPlanTrip1.addAttraction(day,trip1.getAttractions().get(count));
            day=day.plus(1);
            count++;
        }

        //print plan
        travelPlanTrip1.printPlan();

    }
}