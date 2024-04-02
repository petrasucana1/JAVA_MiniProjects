import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class TravelPlan {
    Map<LocalDate, List<Attraction>> plan;
    Trip trip;

    public TravelPlan(Trip trip) {
        this.trip=trip;
        this.plan = new LinkedHashMap<>();
    }

    public Map<LocalDate, List<Attraction>> getPlan() {
        return plan;
    }

    public void setPlan(Map<LocalDate, List<Attraction>> plan) {
        this.plan = plan;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void addAttraction(LocalDate day, Attraction attraction) {
        // Verificăm dacă există deja o listă de atracții pentru ziua specificată
        List<Attraction> attractions = plan.getOrDefault(day, new ArrayList<>());
        attractions.add(attraction);
        plan.put(day, attractions);
    }

    public void printPlan(){
        System.out.print("\u001B[34m____________________________________\u001B[0m");
        for (Map.Entry<LocalDate, List<Attraction>> entry : plan.entrySet()){
            LocalDate day= entry.getKey();
            List<Attraction> attractions= entry.getValue();
            System.out.println("\u001B[32m"); // Cod pentru culoarea verde
            System.out.println("Day: " + day + " " + day.getDayOfWeek());
            // Revenim la culoarea implicită
            System.out.print("\u001B[0m"); // Resetare la culoarea implicită
            //System.out.println("Attractions: ");
            for(Attraction attraction : attractions)
                System.out.println("\u001B[31m     \u25CF \u001B[0m" + attraction.getName());

            System.out.print("\u001B[34m____________________________________\u001B[0m");
        }

    }
}
