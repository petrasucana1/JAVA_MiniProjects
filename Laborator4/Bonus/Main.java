package org.example;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) {
        List<Person> group =  createRandomGroupOfPersons(10000);


       List<Driver> drivers= group.stream()
                    .filter(person->person instanceof Driver)
                    .map(person -> (Driver) person) // Conversion from Person to Driver
                    .sorted(Comparator.comparing(Person::getAge))
                    .collect(Collectors.toCollection(LinkedList::new));


        Set<Passenger> passengers = group.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));


        Problem problem= new Problem(drivers,passengers);

        ProblemGraph graph = new ProblemGraph(problem);
        graph.createGraph();
        // graph.printGraph();


        Solution solution=new Solution(problem,graph);

        printMatchings(solution);

        // printExtraInfo(solution);

    }

    private static List<Person> createRandomGroupOfPersons(int count){
        Faker faker= new Faker();
        Random random= new Random();
        List<Person> group = new ArrayList<>();

        for(int i=0; i<count;i++){
            String name=faker.name().fullName();
            int age= faker.number().numberBetween(20,70);
            String destination= faker.address().city();
            int route=faker.number().numberBetween(1,1000); // 0.1 * total number of persons of the group

            if(random.nextBoolean()){
                group.add(new Driver(name,age,destination,route));

            }else{
                group.add(new Passenger(name,age,destination,route));
            }
        }
        return group;

    }

    private static void printMatchings(Solution solution){
       List<Passenger> unmatchedPassengers= solution.findMatchings();
       List<Driver> unmatchedDrivers= new ArrayList<>();
       Set<Person> maximumCardinalSet=new HashSet<>();

       List<Driver> drivers= solution.getProblem().getDrivers();
       Set<Passenger> passengers=solution.getProblem().getPassengers();

       System.out.println("                 \u001B[32m                     * DRIVERS \u001B[0m              \u001B[34m  * PASSENGERS \u001B[0m ");
       System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");
       System.out.println();

       for(Driver driver: drivers){
           if(driver.getStatus()) {
               unmatchedDrivers.add(driver);
               System.out.println("\u001B[31m-\u001B[0m Driver \u001B[32m" + driver.getName() + "\u001B[0m is \u001B[31mnot\u001B[0m having a matching passenger in his way of his destination.");
           }else
               System.out.println("\u001B[32m+\u001B[0m Driver \u001B[32m"+ driver.getName()+ "\u001B[0m is having \u001B[34m"+ driver.getPassenger().getName() + "\u001B[0m as a matching passenger in his way of his destination.");
       }

        System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");

       System.out.println("\u001B[33mThe following passengers don't have a matching driver: \u001B[0m");
       for(Passenger passenger:unmatchedPassengers){
           System.out.println("\u001B[31m-\u001B[0m Passenger: \u001B[34m"+ passenger.getName() + "\u001B[0m");
       }

        System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");

        maximumCardinalSet.addAll(unmatchedPassengers);
        maximumCardinalSet.addAll(unmatchedDrivers);
        System.out.println("\u001B[33mMaximum cardinal set of persons: \u001B[0m (CARDINAL: " + maximumCardinalSet.size() + ")");
        for(Person person : maximumCardinalSet)
            if(person instanceof Passenger)
                System.out.println("\u001B[31m*\u001B[0m Person: \u001B[34m"+ person.getName() + "\u001B[0m");
            else
                System.out.println("\u001B[31m*\u001B[0m Person: \u001B[32m"+ person.getName() + "\u001B[0m");

    }


    private static void printExtraInfo(Solution solution){
        List<Driver> drivers= solution.getProblem().getDrivers();
        Set<Passenger> passengers=solution.getProblem().getPassengers();

        System.out.println("\u001B[33mDrivers of the group: \u001B[0m");
        for(Driver driver:drivers){
            System.out.println("\u001B[32m-\u001B[0m Driver: "+ driver.getName()+", "+ driver.getAge()+ "years old, goes to "+ driver.getDestination()+", on route: " + driver.getRoute() );
        }

        System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");


        System.out.println("\u001B[33mPassengers of the group: \u001B[0m");
        for(Passenger passenger:passengers){
            System.out.println("\u001B[34m-\u001B[0m Passenger: "+ passenger.getName()+", "+ passenger.getAge()+ "years old, wants to go to "+ passenger.getDestination()+", on route: " + passenger.getRoute() );
        }

        System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");

     /*   List<String> destinationsList= solution.getProblem().allDriversDestinations();
        for(String destination: destinationsList){
            System.out.println(destination);
        }

        System.out.println();

        Map<String,List<Person>> map=solution.getProblem().destinationsMap();
        for (Map.Entry<String, List<Person>> entry : map.entrySet()) {
            String destination = entry.getKey();
            List<Person> people = entry.getValue();

            System.out.println("Destination: " + destination);
            System.out.println("People: ");

            for (Person person : people) {
                System.out.println(person);
            }

            System.out.println();
        }*/
    }
}
