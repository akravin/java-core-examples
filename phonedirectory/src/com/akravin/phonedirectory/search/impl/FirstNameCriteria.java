package com.akravin.phonedirectory.search.impl;

import com.akravin.phonedirectory.search.Criteria;
import com.akravin.phonedirectory.utils.PersonInfo;
import java.util.stream.Stream;

public class FirstNameCriteria implements Criteria {

    private final String name;

    public FirstNameCriteria(String name) {
        this.name = name;
    }

    @Override
    public Stream<PersonInfo> meets(Stream<PersonInfo> persons) {
        return persons.parallel().filter(person -> person.getFirstName()
                .equalsIgnoreCase(name));
    }
}
