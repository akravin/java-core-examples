package com.akravin.phonedirectory.search.impl;

import com.akravin.phonedirectory.search.Criteria;
import com.akravin.phonedirectory.utils.PersonInfo;
import java.util.stream.Stream;

public class PhoneCriteria implements Criteria {

    private final String number;

    public PhoneCriteria(String number) {
        this.number = number;
    }

    @Override
    public Stream<PersonInfo> meets(Stream<PersonInfo> persons) {
        return persons.parallel().filter(person -> person.getPhone()
                .equalsIgnoreCase(number));
    }
}
