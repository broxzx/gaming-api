package com.fyuizee.gamingapi.persistence.domain.geography.enums;

import lombok.Getter;

@Getter
public enum GeographyName {

    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    EUROPE("Europe"),
    ASIA("Asia"),
    AFRICA("Africa"),
    OCEANIA("Oceania");

    final String value;

    GeographyName(String value) {
        this.value = value;
    }

}
