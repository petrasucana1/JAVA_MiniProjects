package org.example;
import java.util.*;
public class Solution {
    private Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public List<Passenger> makeMatchings() {
        List<Passenger> unmatchedPassengers = new ArrayList<>();
        for (Passenger passenger : problem.getPassengers()) {
            int match = 0;

            //same destination case
            for (Driver driver : problem.getDrivers()) {
                if (driver.getDestination().equals(passenger.getDestination())) {
                    if (driver.getStatus()) {
                        driver.setPassenger(passenger);
                        driver.setStatus(false);
                        match = 1;
                    }
                }
                if (match == 1)
                    break;
            }
            if (match == 0) {
                //same route case
                for (Driver driver : problem.getDrivers()) {
                    if (driver.getRoute() == passenger.getRoute()) {
                        if (driver.getStatus()) {
                            driver.setPassenger(passenger);
                            driver.setStatus(false);
                            match = 1;
                        }
                    }
                    if (match == 1)
                        break;

                }
                if (match == 0)
                    unmatchedPassengers.add(passenger);
            }

        }
        return  unmatchedPassengers;
    }
}
