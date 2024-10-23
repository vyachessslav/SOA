package com.vezhur.soa.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Embeddable
public class Person {
    private String name;
    private String birthday;
    private Integer weight;
    private Location location;
}
