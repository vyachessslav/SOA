package com.vezhur.soa.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Embeddable;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Embeddable
public class Person {
    private String name;
    private Date birthday;
    private Integer weight;
    private Location location;
}
