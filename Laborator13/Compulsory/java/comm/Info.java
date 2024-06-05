package comm;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public void execute(String localeTag) {
        Locale locale;
        if (localeTag == null || localeTag.isEmpty()) {
            locale = Locale.getDefault();
        } else {
            locale = Locale.forLanguageTag(localeTag);
        }
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", Locale.getDefault());
        Currency currency = Currency.getInstance(locale);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);

        System.out.println(messages.getString("info").replace("{0}", locale.toString()));
        System.out.println("Country: " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println("Language: " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");
        System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");
        System.out.println("Week Days: " + String.join(", ", dateFormatSymbols.getWeekdays()));
        System.out.println("Months: " + String.join(", ", dateFormatSymbols.getMonths()));
        System.out.println("Today: " + dateFormat.format(new java.util.Date()));
    }
}
