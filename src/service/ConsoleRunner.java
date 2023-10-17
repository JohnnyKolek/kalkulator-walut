package service;

import model.Currency;
import model.CurrencyCollection;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleRunner {
    private final ICurrencyExchangeService exchangeService;
    private final CurrencyCollection currencyCollection;

    private final Scanner scanner = new Scanner(System.in);

    public ConsoleRunner(ICurrencyExchangeService exchangeService, CurrencyCollection currencyCollection) {
        this.exchangeService = exchangeService;
        this.currencyCollection = currencyCollection;
    }

    public void run() {
        char option;
        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Wymien walutę");
            System.out.println("2. Wypisz dostepne waluty");
            System.out.println("0. EXIT");
            System.out.println("Wybierz:");
            option = scanner.next().charAt(0);
            switch (option) {
                case '1':
                    exchange();
                    break;
                case '2':
                    viewAll(currencyCollection);
                    break;
                case '0':
                    System.out.println("Zamykanie");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Niepoprawna opcja");
            }
        }
    }

    private void viewAll(CurrencyCollection collection) {
        collection.getCurrencyList().forEach(System.out::println);
    }

    private void exchange() {
        System.out.println("WYMIANA WALUTY");
        Optional<Currency> sourceCurrencyOpt = chooseCurrency("Wpisz kod waluty wymienianej:");
        if (sourceCurrencyOpt.isEmpty()) {
            System.err.print("Waluta o takim kodzie nie istenieje!\n");
            exchange();
            return;
        }

        Optional<Currency> finalCurrencyOpt = chooseCurrency("Wpisz kod waluty na ktora chcesz wymienic:");
        if (finalCurrencyOpt.isEmpty()) {
            System.err.print("Waluta o takim kodzie nie istenieje!\n");
            exchange();
            return;
        }

        Optional<BigDecimal> amountOpt = getAmountToExchange();
        if (amountOpt.isEmpty()){
            System.err.println("Niepoprawny format liczby!");
            exchange();
            return;
        }

        BigDecimal amount = amountOpt.get();
        Currency sourceCurrency = sourceCurrencyOpt.get();
        Currency finalCurrency = finalCurrencyOpt.get();
        System.out.println("Po wymianie masz: " + exchangeService.exchange(sourceCurrency, finalCurrency, amount) + " " + finalCurrency.getCode());

    }



    private Optional<BigDecimal> getAmountToExchange() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz ile pieniedzy chcesz wymienic:");

        String temp;

        temp = scanner.next();

        BigDecimal result;
        try {
            result = new BigDecimal(temp);
        } catch (NumberFormatException exception) {
            System.err.println(exception.getMessage());
            return Optional.empty();
        }

        return Optional.of(result);

    }

    private Optional<Currency> chooseCurrency(String label) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(label);

        String temp;

        temp = scanner.next();

        return currencyCollection.getCurrencyByCode(temp.toUpperCase());

    }
}
