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
public class Coordinates {
    private Integer x;
    private Float y;
}
