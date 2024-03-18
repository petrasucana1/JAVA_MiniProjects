//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        //Set the timetable of each day of the week for each attraction

        Map<DayOfWeek, TimeInterval> church1Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            church1Timetable.put(day, new TimeInterval(LocalTime.of(9, 0), LocalTime.of(17, 0)));
        }

        Map<DayOfWeek, TimeInterval> church2Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            church2Timetable.put(day, new TimeInterval(LocalTime.of(9, 0), LocalTime.of(19, 0)));
        }

        Map<DayOfWeek, TimeInterval> statue1Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day != DayOfWeek.MONDAY)
                statue1Timetable.put(day, new TimeInterval(LocalTime.of(8, 30), LocalTime.of(14, 0)));
        }

        Map<DayOfWeek, TimeInterval> statue2Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day != DayOfWeek.WEDNESDAY)
                statue2Timetable.put(day, new TimeInterval(LocalTime.of(10, 0), LocalTime.of(19, 30)));
        }

        Map<DayOfWeek, TimeInterval> concert1Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                concert1Timetable.put(day, new TimeInterval(LocalTime.of(20, 30), LocalTime.of(23, 0)));
        }

        Map<DayOfWeek, TimeInterval> concert2Timetable = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY)
                concert2Timetable.put(day, new TimeInterval(LocalTime.of(19, 0), LocalTime.of(21, 30)));
        }


        //set the attractions

        Church church1 = new Church("New York", "St. Patrick's Cathedral", "Historic cathedral in Manhattan", 1, 3, church1Timetable);
        Church church2 = new Church("New York", "Trinity Church", "Important site during the American Revolution",1, 5, church2Timetable);
        Statue statue1 = new Statue("New York", "Statue Of Liberty", "Iconic symbol in New York City", 2,3, statue1Timetable);
        Statue statue2 = new Statue("New York", "ATLAS statue", "Symbolic representation of reigning superiority and strength",2, 5, statue2Timetable);
        Concert concert1 = new Concert("New York", "Concert Inna", "Romanian singer", 3,5, concert1Timetable, 123.00);
        Concert concert2 = new Concert("New York", "Concert Alicia Keys", "Grammy-winning singer-songwriter", 3,4, concert2Timetable, 530.00);


        List<Attraction> attractions = new ArrayList<>();

        attractions.add(church1);
        attractions.add(church2);
        attractions.add(statue1);
        attractions.add(statue2);
        attractions.add(concert1);
        attractions.add(concert2);


        //set the trip
        Trip trip1 = new Trip("New York", new TripPeriod(LocalDate.of(2024, 3, 18), 6), attractions, 500.50);
        TravelPlan travelPlanTrip1 = new TravelPlan(trip1);


        //graph coloring
        ColoringGraph graph1 = new ColoringGraph(travelPlanTrip1, attractions);

        //First coloring heuristic:
        graph1.sortByOpenHour();
        graph1.plan.printPlan();
        System.out.println();
        System.out.println();

        /*for(Attraction attraction: attractions)
        {
            System.out.println(attraction + " * " + attraction.isAvailable() + " - " + attraction.getColor());
        }*/

        ///afisez culorile reprezentative pentru fiecare zi
        System.out.println("CULORILE REPREZENTATIVE FIECAREI ZI DIN CALATORIE:");
        LocalDate firstDay = trip1.getPeriodOfTime().getStartDay();
        Integer count = 0;
        while (count < trip1.getPeriodOfTime().numberOfDays) {
            System.out.print(setColorDays(count) + "   \u25CF " + firstDay + "\u001B[0m");
            firstDay= firstDay.plusDays(1);
            count++;
        }
        System.out.println();
        System.out.println();


        //afisez nodurile grafului si culorile acestuia
        System.out.println("NODURILE GRAFULUI SI CULORILE ACESTORA IN FUNCTIE DE ZILELE IN CARE SUNT VIZITATE: ");
        for (Attraction attraction : attractions) {
            System.out.println(getColorCode(attraction.getColor()) + "  \u25CF \u001B[0m" + attraction.getName());
        }
        System.out.println();
        System.out.println();

        //afisez conexiunile grafului(matricea de adiacenta)
        System.out.println("CONEXIUNILE GRAFULUI - MATRICEA DE ADIACENTA (ATRACTIILE DE ACELASI TIP SUNT ADIACENTE): ");
        for (Pair<Attraction, Attraction> connection : graph1.connectedAttractions) {
            System.out.println(connection.getFirst().getName() + " ---- " + connection.getSecond().getName());
        }
        System.out.println();
        System.out.println();

        //reset availability
        for(Attraction attraction: attractions)
        {
            attraction.setAvailability(true);
            attraction.setColor("\0");
        }

        TravelPlan travelPlanTrip2 = new TravelPlan(trip1);
        ColoringGraph graph2 = new ColoringGraph(travelPlanTrip2, attractions);


        //second coloring heuristic
        graph2.sortByPopularity();
        graph2.plan.printPlan();
        System.out.println();
        System.out.println();

        /*for(Attraction attraction: attractions)
        {
            System.out.println(attraction + " * " + attraction.isAvailable() + " - " + attraction.getColor());
        }*/

        ///afisez culorile reprezentative pentru fiecare zi
        System.out.println("CULORILE REPREZENTATIVE FIECAREI ZI DIN CALATORIE:");
        LocalDate firstDay2 = trip1.getPeriodOfTime().getStartDay();
        Integer count2 = 0;
        while (count2 < trip1.getPeriodOfTime().numberOfDays) {
            System.out.print(setColorDays(count2) + "   \u25CF " + firstDay2 + "\u001B[0m");
            firstDay2= firstDay2.plusDays(1);
            count2++;
        }
        System.out.println();
        System.out.println();


        //afisez nodurile grafului si culorile acestuia
        System.out.println("NODURILE GRAFULUI SI CULORILE ACESTORA IN FUNCTIE DE ZILELE IN CARE SUNT VIZITATE: ");
        for (Attraction attraction : attractions) {
            System.out.println(getColorCode(attraction.getColor()) + "  \u25CF \u001B[0m" + attraction.getName());
        }
        System.out.println();
        System.out.println();

        //afisez conexiunile grafului(matricea de adiacenta)
        System.out.println("CONEXIUNILE GRAFULUI - MATRICEA DE ADIACENTA (ATRACTIILE DE ACELASI TIP SUNT ADIACENTE): ");
        for (Pair<Attraction, Attraction> connection : graph2.connectedAttractions) {
            System.out.println(connection.getFirst().getName() + " ---- " + connection.getSecond().getName());
        }

    }

    public static String getColorCode(String color) {
        switch (color) {
            case "green":
                return "\u001B[32m"; // Verde
            case "blue":
                return "\u001B[34m"; // Albastru
            case "red":
                return "\u001B[31m"; // Roșu
            case "yellow":
                return "\u001B[93m"; // Galben
            case "orange":
                return "\u001B[33m"; // Portocaliu
            case "purple":
                return "\u001B[35m"; // Mov
            case "pink":
                return "\u001B[95m"; // Roz
            default:
                return ""; // În cazul în care culoarea nu este recunoscută, nu se aplică nicio culoare
        }
    }


    public static String setColorDays(Integer count) {
        switch (count) {
            case 0:
                return "\u001B[32m"; // Verde
            case 1:
                return "\u001B[34m"; // Albastru
            case 2:
                return "\u001B[31m"; // Roșu
            case 3:
                return "\u001B[93m"; // Galben
            case 4:
                return "\u001B[33m"; // Portocaliu
            case 5:
                return "\u001B[35m"; // Mov
            case 6:
                return "\u001B[95m"; // Roz
            default:
                return ""; // În cazul în care culoarea nu este recunoscută, nu se aplică nicio culoare
        }

    }

}