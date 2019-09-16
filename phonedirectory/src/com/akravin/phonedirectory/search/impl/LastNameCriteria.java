package com.akravin.phonedirectory.search.impl;

import com.akravin.phonedirectory.search.Criteria;
import com.akravin.phonedirectory.utils.PersonInfo;
import java.util.stream.Stream;

public class LastNameCriteria implements Criteria {

    private final String name;

    public LastNameCriteria(String name) {
        this.name = name;
    }

    @Override
    public Stream<PersonInfo> meets(Stream<PersonInfo> persons) {
        return persons.parallel().filter(person -> person.getLastName()
                .equalsIgnoreCase(name));

    }
}
