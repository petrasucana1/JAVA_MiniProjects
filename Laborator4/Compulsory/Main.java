package org.example;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Person> group =  createRandomGroupOfPersons(10);


       List<Driver> drivers= group.stream()
                    .filter(person->person instanceof Driver)
                    .map(person -> (Driver) person) // Convertim fiecare Person la Driver
                    .sorted(Comparator.comparing(Person::getAge))
                    .collect(Collectors.toCollection(LinkedList::new));


        Set<Passenger> passengers = group.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));



        for(Driver driver:drivers){
            System.out.println("Driver: "+ driver );
        }

        System.out.println();

        for(Passenger passenger:passengers){
            System.out.println("Passenger: "+ passenger );
        }



    }

    private static List<Person> createRandomGroupOfPersons(int count){
        Faker faker= new Faker();
        Random random= new Random();
        List<Person> group = new ArrayList<>();

        for(int i=0; i<count;i++){
            String name=faker.name().fullName();
            int age= faker.number().numberBetween(20,70);
            String destination= faker.address().city();

            if(random.nextBoolean()){
                group.add(new Driver(name,age,destination));

            }else{
                group.add(new Passenger(name,age,destination));
            }
        }
        return group;

    }
}
