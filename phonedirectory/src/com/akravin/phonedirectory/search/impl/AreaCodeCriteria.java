package com.akravin.phonedirectory.search.impl;

import com.akravin.phonedirectory.search.Criteria;
import com.akravin.phonedirectory.utils.PersonInfo;
import java.util.stream.Stream;

public class AreaCodeCriteria implements Criteria {

    private final String code;

    public AreaCodeCriteria(String code) {
        this.code = code;
    }

    @Override
    public Stream<PersonInfo> meets(Stream<PersonInfo> persons) {
        return persons.parallel().filter(person -> person.getAreaCode()
                .equalsIgnoreCase(code));

    }
}
