import java.util.*;
import java.io.*;

public class CurrencyConverter {

    private static final String CURRENCY_FILE_PATH = "currency.csv";

    public static void main(String[] args) throws IOException {
        // Read the currency data from the file.
        List<Currency> currencies = readCurrencyDataFromFile();

        // Create a menu for the user to choose from.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Currency Converter!");
        System.out.println("Please select a currency to convert from:");
        for (Currency currency : currencies) {
            System.out.println(currency.getName() + " (" + currency.getCode() + ")");
        }

        // Get the user's input.
        String fromCurrencyCode = scanner.nextLine();
        Currency fromCurrency = currencies.stream().filter(currency -> currency.getCode().equals(fromCurrencyCode)).findFirst().orElseThrow(NoSuchElementException::new);

        System.out.println("Please enter the amount to convert:");
        double amount = scanner.nextDouble();

        // Convert the amount to the user's desired currency.
        System.out.println("Please select a currency to convert to:");
        for (Currency currency : currencies) {
            System.out.println(currency.getName() + " (" + currency.getCode() + ")");
        }

        String toCurrencyCode = scanner.nextLine();
        Currency toCurrency = currencies.stream().filter(currency -> currency.getCode().equals(toCurrencyCode)).findFirst().orElseThrow(NoSuchElementException::new);

        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);

        // Print the converted amount.
        System.out.println(amount + " " + fromCurrency.getName() + " is equal to " + convertedAmount + " " + toCurrency.getName());
    }

    private static List<Currency> readCurrencyDataFromFile() throws IOException {
        List<Currency> currencies = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(CURRENCY_FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            String name = tokens[0];
            String code = tokens[1];
            double rate = Double.parseDouble(tokens[2]);
            currencies.add(new Currency(name, code, rate));
        }

        return currencies;
    }

    private static double convertCurrency(Currency fromCurrency, Currency toCurrency, double amount) {
        return amount * fromCurrency.getRate() / toCurrency.getRate();
    }
}

class Currency {

    private String name;
    private String code;
    private double rate;

    public Currency(String name, String code, double rate) {
        this.name = name;
        this.code = code;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }
}