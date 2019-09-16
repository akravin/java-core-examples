package com.akravin.phonedirectory;

import com.akravin.phonedirectory.core.PhoneDirectory;
import com.akravin.phonedirectory.search.enums.SearchType;

import java.util.Map;
import java.util.Scanner;

public class Demo {
    private static final String FILE_NAME = "resources/persons.txt";


    public static void main(String[] args) {
        new Demo().start();
    }

    private void start() {
        PhoneDirectory phoneDirectory = new PhoneDirectory(FILE_NAME);

        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Name/Area Code: ");

            String userInput = scanner.nextLine();
            if (!userInput.isBlank()) {
                Map<SearchType, Long> result = phoneDirectory.search(userInput);
                result.forEach((k,v) -> {
                    System.out.println("\t\t" + String.format(k.getPattern(), userInput, v));
                });
            } else {
                exit = true;
            }
        }
    }
}
