package com.akravin.phonedirectory.search;

import com.akravin.phonedirectory.utils.PersonInfo;

import java.util.stream.Stream;

public interface Criteria {

    Stream<PersonInfo> meets(Stream<PersonInfo> info);

}
