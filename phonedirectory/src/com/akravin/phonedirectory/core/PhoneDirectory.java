package com.akravin.phonedirectory.core;

import com.akravin.phonedirectory.search.SearchEngine;
import com.akravin.phonedirectory.search.enums.SearchType;
import com.akravin.phonedirectory.utils.PersonInfoParser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class PhoneDirectory {

    private SearchEngine searchEngine = new SearchEngine();

    public PhoneDirectory(String fileName) {
        loadPersons(fileName);
    }

    private void loadPersons(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                searchEngine.addPerson(PersonInfoParser.parse(line));
            });
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Map<SearchType, Long> search(String userInput) {
        return searchEngine.search(userInput);
    }
}
