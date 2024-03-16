import java.time.DayOfWeek;
import java.util.*;

public class TravelPlan {
    Map<DayOfWeek, List<Attraction>> plan;
    Trip trip;

    public TravelPlan(Trip trip) {
        this.trip=trip;
        this.plan = new LinkedHashMap<>();
    }

    public Map<DayOfWeek, List<Attraction>> getPlan() {
        return plan;
    }

    public void setPlan(Map<DayOfWeek, List<Attraction>> plan) {
        this.plan = plan;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void addAttraction(DayOfWeek day, Attraction attraction) {
        // Verificăm dacă există deja o listă de atracții pentru ziua specificată
        List<Attraction> attractions = plan.getOrDefault(day, new ArrayList<>());
        attractions.add(attraction);
        plan.put(day, attractions);
    }

    public void printPlan(){
        System.out.print("\u001B[34m____________________________________\u001B[0m");
        for (Map.Entry<DayOfWeek, List<Attraction>> entry : plan.entrySet()){
            DayOfWeek day= entry.getKey();
            List<Attraction> attractions= entry.getValue();
            System.out.println("\u001B[32m"); // Cod pentru culoarea verde
            System.out.println("Day: " + day);
            // Revenim la culoarea implicită
            System.out.print("\u001B[0m"); // Resetare la culoarea implicită
            //System.out.println("Attractions: ");
            for(Attraction attraction : attractions)
                System.out.println("\u001B[31m     \u25CF \u001B[0m" + attraction.getName());

            System.out.print("\u001B[34m____________________________________\u001B[0m");
        }

    }
}
