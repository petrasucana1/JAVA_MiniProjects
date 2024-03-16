//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Interval interval1 = new Interval(10, 14);
        Interval interval2 = new Interval(10, 15);
        Interval interval3 = new Interval(12, 18);
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       Statue statue= new Statue("New York","Iconic symbol in New York City","Statue Of Liberty", interval1);
       Church church= new Church("New York", "St. Patrick's Cathedral","Historic cathedral in Manhattan", interval2);
       Concert concert= new Concert("Iasi", "Inna","Romanian singer",123.00);

       List<Attraction> attractions= new ArrayList<>();

       attractions.add(statue);
       attractions.add(church);
       attractions.add(concert);

       Trip trip=new Trip("New York",4, attractions, 500.50);

       System.out.println("Attractions in the trip:");
       for(Attraction attraction: trip.getAttractions()){
           System.out.println(attraction);
       }

        System.out.println("Total trip price: $" + trip.getPrice());
    }
}