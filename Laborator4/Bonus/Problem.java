package org.example;
import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private List<Driver> drivers;
    private Set<Passenger> passengers;

    public Problem(List<Driver> drivers, Set<Passenger> passengers) {
        this.drivers = drivers;
        this.passengers = passengers;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<String> allDriversDestinations() {
        List<String> allDriversDestination = drivers.stream()
                .map(Driver::getDestination)
                .distinct()
                .collect(Collectors.toList());

        return allDriversDestination;
    }

  public Map<String,List<Person>> destinationsMap(){
            Map<String, List<Person>> destinationsMap = new HashMap<>();

            for (Driver driver : drivers) {
                destinationsMap.computeIfAbsent(driver.getDestination(), key -> new ArrayList<>()).add(driver);
            }

            for (Passenger passenger : passengers) {
                destinationsMap.computeIfAbsent(passenger.getDestination(), key -> new ArrayList<>()).add(passenger);
            }

            return destinationsMap;

    }
}
