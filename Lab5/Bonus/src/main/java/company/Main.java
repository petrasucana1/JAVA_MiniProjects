package company;
import company.shell.*;
import com.github.javafaker.Faker;
import company.model.*;
import company.repository.*;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {
    public static void main(String[] args) {

        String masterDirectoryPath = "C:/Users/petra/Desktop/FII/ANULII/SEM2/JAVA/Lab5/Bonus/src/MasterDirectory";

        generateRandomEmployees(masterDirectoryPath,5);

        generateExcelDocument(masterDirectoryPath);

        Map<String, Set<String>> employeeAbilities=readAbilitiesFromExcel("C:/Users/petra/Desktop/FII/ANULII/SEM2/JAVA/Lab5/Bonus/src/MasterDirectory/abilities.xlsx");

        List<Set<String>> maximalGroups= findMaximalGroups(employeeAbilities);

        System.out.println();
        for (int i = 0; i < maximalGroups.size(); i++) {
            System.out.println("\u001B[33m * Group " + (i + 1) + ": \u001B[0m" + maximalGroups.get(i));
        }
        System.out.println("\n");
    }

    static void generateRandomEmployees(String path, int number){
        File masterDirectory=new File(path);
        Faker faker= new Faker();

        for(int i=1;i<=number;i++){
            String directoryName = String.format("%s_%s", faker.lorem().word(), faker.number().numberBetween(10, 99));
            File subdirectory= new File(masterDirectory,directoryName);
            if (subdirectory.mkdir()) {
                System.out.println("Directorul " + subdirectory.getPath() + " a fost creat cu succes.");
            } else {
                System.out.println("Eroare la crearea directorului " + subdirectory.getPath() + ".");
            }
            generateRandomFiles(subdirectory,3);
        }
    }

    static void generateRandomFiles(File subdirectory, int number){
        Faker faker = new Faker();

        for(int i=1;i<=number;i++){
            String baseFileName = faker.lorem().word();
            String fileName = baseFileName + ".txt";

            File file= new File(subdirectory, fileName);
            try{
                file.createNewFile();
            } catch(IOException e){
                System.out.println("Eroare la crearea fișierului " + fileName + ": " + e.getMessage());
            }
        }
    }

    static void generateExcelDocument(String path){
        Faker faker=new Faker();

        List<String> jobTitles = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            jobTitles.add(faker.job().title());
        }


        try(Workbook workbook= new XSSFWorkbook()){

            Sheet sheet= workbook.createSheet("Abilities");

            File directory= new File(path);
            File[] subdirectories= directory.listFiles(File::isDirectory);

            if(subdirectories != null){
                int rowNum=0;
                for(File subdirectory: subdirectories){
                    Row row= sheet.createRow(rowNum++);
                    Cell cell= row.createCell(0);
                    cell.setCellValue(subdirectory.getName());

                    // Shuffle the job titles list to randomize
                    Collections.shuffle(jobTitles);

                    // Select the first 5 job titles after shuffling
                    for (int i = 1; i <= 5; i++) {
                        cell = row.createCell(i);
                        cell.setCellValue(jobTitles.get(i - 1));
                    }
                }
               /* for(int i=1;i<=5;i++){
                    cell=row.createCell(i);
                    cell.setCellValue(faker.job().title());
                }*/

            }

            String excelFilePath= path+"/abilities.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
                System.out.println("Excel file created successfully: " + excelFilePath);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    static Map<String, Set<String>> readAbilitiesFromExcel(String filePath) {
    Map<String, Set<String>> employeeAbilities = new HashMap<>();

    try (FileInputStream fis = new FileInputStream(new File(filePath));
         Workbook workbook = WorkbookFactory.create(fis)) {

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            String employeeName = row.getCell(0).getStringCellValue();
            Set<String> abilities = new HashSet<>();

            for (int i = 1; i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    abilities.add(cell.getStringCellValue());
                }
            }

            employeeAbilities.put(employeeName, abilities);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return employeeAbilities;
}


    static List<Set<String>> findMaximalGroups(Map<String, Set<String>> employeeAbilities) {
        List<Set<String>> maximalGroups = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : employeeAbilities.entrySet()) {
            String employeeName = entry.getKey();
            Set<String> abilities = entry.getValue();

            boolean added = false;


            for (Set<String> group : maximalGroups) {
                if (hasCommonAbility(group, abilities,employeeAbilities)) {
                    group.add(employeeName);
                    added = true;
                    break;
                }
            }


            if (!added) {
                Set<String> newGroup = new HashSet<>();
                newGroup.add(employeeName);
                maximalGroups.add(newGroup);
            }
        }

        return maximalGroups;
    }

    static boolean hasCommonAbility(Set<String> groupEmployeeNames, Set<String> abilities, Map<String, Set<String>> employeeAbilities) {

        for(String employee: groupEmployeeNames) {
            Set<String> abilities1 = employeeAbilities.get(employee);
            abilities1.retainAll(abilities);
            if (!abilities1.isEmpty())
                return false;
        }
        return true;
    }

}


