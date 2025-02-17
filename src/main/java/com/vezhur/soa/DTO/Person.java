package com.vezhur.soa.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date birthday;
    private Integer weight;
    private Location location;
}
