package com.akravin.phonedirectory.search;

import com.akravin.phonedirectory.search.enums.SearchType;
import com.akravin.phonedirectory.search.impl.*;
import com.akravin.phonedirectory.utils.PersonInfo;

import java.util.*;

public class SearchEngine {

    // Think about using hash or B-Tree indexes to speed up the search
    private final List<PersonInfo> persons = new ArrayList<>();

    public void addPerson(PersonInfo person) {
        persons.add(person);
    }

    public Map<SearchType, Long> search(String userInput) {
        String[] parts = userInput.split(" ");
        EnumMap<SearchType, Long> result = new EnumMap<>(SearchType.class);
        if (parts.length == 1) {
            pushIfExists(SearchType.FIRST_NAME, new FirstNameCriteria(userInput), persons, result);
            pushIfExists(SearchType.LAST_NAME, new LastNameCriteria(userInput), persons, result);
            pushIfExists(SearchType.AREA_CODE, new AreaCodeCriteria(userInput), persons, result);
            pushIfExists(SearchType.PHONE, new PhoneCriteria(userInput), persons, result);
        } else if (parts.length == 2) {
            pushIfExists(SearchType.NAME, new AndCriteria(new FirstNameCriteria(parts[0]),
                    new LastNameCriteria(parts[1])), persons, result);
        } else if (parts.length == 3) {
            Criteria criteria = new AndCriteria(new FirstNameCriteria(parts[0]),
                    new AndCriteria(new MiddleNameCriteria(parts[1]), new LastNameCriteria(parts[2])));
            pushIfExists(SearchType.FULL_NAME, criteria, persons, result);
        }
        return result;
    }

    private void pushIfExists(SearchType searchType, Criteria criteria, List<PersonInfo> persons, Map<SearchType, Long> result) {
        long count = criteria.meets(persons.stream()).count();
        if (count > 0) {
            Long oldCount = result.get(searchType);
            result.put(searchType, oldCount == null ? count: oldCount + count);
        }
    }
}
