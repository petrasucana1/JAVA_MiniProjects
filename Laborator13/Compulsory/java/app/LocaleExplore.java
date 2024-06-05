package app;

import comm.DisplayLocales;
import comm.Info;
import comm.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", Locale.getDefault());

        DisplayLocales displayLocales = new DisplayLocales();
        SetLocale setLocale = new SetLocale();
        Info info = new Info();

        while (true) {
            System.out.println(messages.getString("prompt"));
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("locales")) {
                displayLocales.execute();
            } else if (command.startsWith("setlocale")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    setLocale.execute(parts[1]);
                } else {
                    System.out.println("Usage: setlocale <languageTag>");
                }
            } else if (command.startsWith("info")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    info.execute(parts[1]);
                } else {
                    info.execute(null);
                }
            } else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}
