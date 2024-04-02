package company;
import company.shell.*;

import company.model.*;
import company.repository.*;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //java -cp  .\Compulsory-1.0-SNAPSHOT.jar company.Main

        /*String masterDirectoryPath = "C:/Users/petra/Desktop/FII/ANULII/SEM2/JAVA/Lab5/Compulsory/src/MasterDirectory";

        try {
            Repository repository = new Repository(masterDirectoryPath);

            System.out.println("Master Directory with the path: " + masterDirectoryPath + " has the following sub-directories:");

            repository.displayRepositoryContent();
        } catch (InvalidRepositoryException e) {
            System.err.println("Error: " + e.getMessage());

        }*/

        Scanner scanner= new Scanner(System.in);

        while(true){
            System.out.print("> ");
            String input=scanner.nextLine().trim();

            if(input.equalsIgnoreCase("exit")){
                break;
            }

            String[] parts = input.split("\\s+");
            String commandName= parts[0].toLowerCase();

            String[] arguments= new String[parts.length-1];
            System.arraycopy(parts,1,arguments,0, arguments.length);

            try {
                Command command;
                switch (commandName) {
                    case "view" -> command = new ViewCommand();
                    case "report" -> command = new ReportCommand();
                    case "export" -> command = new ExportCommand();
                    default -> throw new CommandException("Unknown command: " + commandName);
                }
                command.execute(arguments);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();

    }
}
