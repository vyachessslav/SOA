package com.vezhur.soa.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Embeddable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Embeddable
public class Location {
    private Integer x;
    private Long y;
    private String name;
}
