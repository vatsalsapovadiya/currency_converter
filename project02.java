import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class CurrencyConverter {
    private static final double USD_TO_INR = 74.93;
    private static final double EUR_TO_INR = 88.67;
    private static final double GBP_TO_INR = 103.53;
    private static final double INR_TO_USD = 0.013;
    private static final double EUR_TO_USD = 1.18;
    private static final double GBP_TO_USD = 1.38;
    private static final double INR_TO_EUR = 0.011;
    private static final double USD_TO_EUR = 0.85;
    private static final double GBP_TO_EUR = 1.17;
    private static final double INR_TO_GBP = 0.0097;
    private static final double USD_TO_GBP = 0.73;
    private static final double EUR_TO_GBP = 0.86;

    public double convert(double amount, String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            return amount;
        }
        switch (sourceCurrency) {
            case "INR":
                switch (targetCurrency) {
                    case "USD":
                        return amount * INR_TO_USD;
                    case "EUR":
                        return amount * INR_TO_EUR;
                    case "GBP":
                        return amount * INR_TO_GBP;
                    default:
                        throw new IllegalArgumentException("Invalid target currency");
                }
            case "USD":
                switch (targetCurrency) {
                    case "INR":
                        return amount * USD_TO_INR;
                    case "EUR":
                        return amount * USD_TO_EUR;
                    case "GBP":
                        return amount * USD_TO_GBP;
                    default:
                        throw new IllegalArgumentException("Invalid target currency");
                }
            case "EUR":
                switch (targetCurrency) {
                    case "INR":
                        return amount * EUR_TO_INR;
                    case "USD":
                        return amount * EUR_TO_USD;
                    case "GBP":
                        return amount * EUR_TO_GBP;
                    default:
                        throw new IllegalArgumentException("Invalid target currency");
                }
            case "GBP":
                switch (targetCurrency) {
                    case "INR":
                        return amount * GBP_TO_INR;
                    case "USD":
                        return amount * GBP_TO_USD;
                    case "EUR":
                        return amount * GBP_TO_EUR;
                    default:
                        throw new IllegalArgumentException("Invalid target currency");
                }
            default:
                throw new IllegalArgumentException("Invalid source currency");
        }
    }

    public static void writeToFile(double amount, String sourceCurrency, String targetCurrency, double result) {
        try {
            FileWriter fileWriter = new FileWriter("currency_conversion.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(amount + " " + sourceCurrency + " is equal to " + result + " " + targetCurrency);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}

class CC1 {
    public static void main(String[] args) throws IOException {
        System.out.println("________________________________________________Welcome to the Currency Converter_______________________________________");
        System.out.println("Please select the currencies you want to convert:");
        System.out.println("1. INR to USD");
        System.out.println("2. USD to INR");
        System.out.println("3. INR to EUR");
        System.out.println("4. USD to EUR");
        System.out.println("5. EUR to USD");
        System.out.println("6. EUR to INR");
        System.out.println("7. INR to GBP");
        System.out.println("8. USD to GBP");
        System.out.println("9. EUR to GBP");
        System.out.println("10. GBP to INR");
        System.out.println("11. GBP to USD");
        System.out.println("12. GBP to EUR");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        System.out.println("Enter the source currency (INR, USD, EUR, GBP): ");
        String sourceCurrency = scanner.next().toUpperCase();

        System.out.println("Enter the target currency (INR, USD, EUR, GBP): ");
        String targetCurrency = scanner.next().toUpperCase();

        CurrencyConverter cc = new CurrencyConverter();

        try {
            double result = cc.convert(amount, sourceCurrency, targetCurrency);
            System.out.println(amount + " " + sourceCurrency + " is equal to " + result + " " + targetCurrency);
            cc.writeToFile(amount, sourceCurrency, targetCurrency, result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
