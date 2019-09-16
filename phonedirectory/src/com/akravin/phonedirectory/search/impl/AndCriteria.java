package com.akravin.phonedirectory.search.impl;

import com.akravin.phonedirectory.search.Criteria;
import com.akravin.phonedirectory.utils.PersonInfo;
import java.util.stream.Stream;

public class AndCriteria implements Criteria {

    private final Criteria leftCriteria;
    private final Criteria rightCriteria;

    public AndCriteria(Criteria leftCriteria, Criteria rightCriteria) {
        this.leftCriteria = leftCriteria;
        this.rightCriteria = rightCriteria;
    }

    @Override
    public Stream<PersonInfo> meets(Stream<PersonInfo> persons) {
        return rightCriteria.meets(leftCriteria.meets(persons));
    }
}
