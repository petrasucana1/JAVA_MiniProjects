import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColoringGraph {
    TravelPlan plan;
    List<Attraction> attractions;
    List<Pair<Attraction, Attraction>> connectedAttractions;


    public ColoringGraph(TravelPlan plan, List<Attraction> attractions) {
        this.plan = plan;
        this.connectedAttractions = new ArrayList<>();
        this.attractions = attractions;
        identifyConnections(attractions);

    }

    private void identifyConnections(List<Attraction> attractions) {
        for (int i = 0; i < attractions.size() - 1; i++) {
            for (int j = i + 1; j < attractions.size(); j++) {
                if (attractions.get(i).getColorCode() == attractions.get(j).getColorCode()) {
                    Attraction attraction1 = attractions.get(i);
                    Attraction attraction2 = attractions.get(j);
                    Pair<Attraction, Attraction> pair = new Pair<>();
                    pair.setFirst(attraction1);
                    pair.setSecond(attraction2);
                    Pair<Attraction,Attraction> invPair = new Pair<>();
                    invPair.setFirst(attraction2);
                    invPair.setSecond(attraction1);
                    if(!connectedAttractions.contains(pair) && !connectedAttractions.contains(invPair))
                        connectedAttractions.add(pair);
                }
            }
        }
    }

    public void sortByOpenHour() {

            Trip trip = plan.getTrip();
            LocalDate day = trip.getPeriodOfTime().getStartDay();
            Integer count = 0;
            while (count < trip.getPeriodOfTime().getNumberOfDays()) {
                List<Attraction> currentDayAttractions = trip.sortVisitableAttractions(day.getDayOfWeek());
                /*System.out.println("\n");
                System.out.print("day: " + day.getDayOfWeek());
                for (Attraction d : currentDayAttractions) {
                    System.out.println("- " + d);
                }*/
                int ok1 = 1, ok2 = 2, ok3 = 3;
                for (Attraction currentDayAttraction : currentDayAttractions) {
                    if (currentDayAttraction.isAvailable()) {
                        if (currentDayAttraction.getColorCode() == ok1) {
                            plan.addAttraction(day, currentDayAttraction);
                            currentDayAttraction.setAvailability(false);
                            setColor(count,currentDayAttraction);
                            ok1 = 0;
                        }
                        if (currentDayAttraction.getColorCode() == ok2) {
                            plan.addAttraction(day, currentDayAttraction);
                            currentDayAttraction.setAvailability(false);
                            setColor(count,currentDayAttraction);
                            ok2 = 0;
                        }
                        if (currentDayAttraction.getColorCode() == ok3) {
                            plan.addAttraction(day, currentDayAttraction);
                            setColor(count,currentDayAttraction);
                            currentDayAttraction.setAvailability(false);
                            ok3 = 0;
                        }

                    }
                }
                day = day.plusDays(1);
                count++;
            }
    }

    public void sortByPopularity(){

        Trip trip = plan.getTrip();
        LocalDate day = trip.getPeriodOfTime().getStartDay();
        Integer count = 0;
        while (count < trip.getPeriodOfTime().getNumberOfDays()) {
            List<Attraction> currentDayAttractions = trip.sortByPopularity(day.getDayOfWeek());
                /*System.out.println("\n");
                System.out.print("day: " + day.getDayOfWeek());
                for (Attraction d : currentDayAttractions) {
                    System.out.println("- " + d);
                }*/
            int ok1 = 1, ok2 = 2, ok3 = 3;
            for (Attraction currentDayAttraction : currentDayAttractions) {
                if (currentDayAttraction.isAvailable()) {
                    if (currentDayAttraction.getColorCode() == ok1) {
                        plan.addAttraction(day, currentDayAttraction);
                        currentDayAttraction.setAvailability(false);
                        setColor(count,currentDayAttraction);
                        ok1 = 0;
                    }
                    if (currentDayAttraction.getColorCode() == ok2) {
                        plan.addAttraction(day, currentDayAttraction);
                        currentDayAttraction.setAvailability(false);
                        setColor(count,currentDayAttraction);
                        ok2 = 0;
                    }
                    if (currentDayAttraction.getColorCode() == ok3) {
                        plan.addAttraction(day, currentDayAttraction);
                        setColor(count,currentDayAttraction);
                        currentDayAttraction.setAvailability(false);
                        ok3 = 0;
                    }

                }
            }
            day = day.plusDays(1);
            count++;
        }
    }

    public void setColor(Integer count, Attraction attraction) {
        switch (count) {
            case 0:
                attraction.setColor("green");
                break;
            case 1:
                attraction.setColor("blue");
                break;
            case 2:
                attraction.setColor("red");
                break;
            case 3:
                attraction.setColor("yellow");
                break;
            case 4:
                attraction.setColor("orange");
                break;
            case 5:
                attraction.setColor("purple");
                break;
            case 6:
                attraction.setColor("pink");
                break;
            default:
                // Setează o culoare implicită pentru cazurile neașteptate
                attraction.setColor("gray");
                break;
        }
    }
}

