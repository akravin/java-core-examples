package com.akravin.phonedirectory.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonInfoParser {

    private static final Pattern PATTERN_PERSON = Pattern.compile("^([A-Za-z]+)\\s+([A-Z.]+)\\s+([A-Za-z]+)\\|(\\d+)-([0-9\\-]+)$");

    private PersonInfoParser() {}

    public static PersonInfo parse(String line) {
        Matcher matcher = PATTERN_PERSON.matcher(line);
        if (matcher.matches()) {
            return new PersonInfo(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4),
                    matcher.group(5));
        }
        return null;
    }
}
