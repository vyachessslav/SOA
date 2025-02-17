package com.vezhur.soa.DTO;

import com.vezhur.soa.entity.LabworkEntity;
import com.vezhur.soa.enums.Difficulty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LabworkDetails {

    private String name;
    private Float minimalPoint;
    private Difficulty difficulty;
    private Coordinates coordinates;
    private Person author;

    public LabworkDetails(LabworkEntity labwork) {
        this.setName(labwork.getName());
        this.setMinimalPoint(labwork.getMinimalPoint());
        this.setDifficulty(labwork.getDifficulty());
        this.setCoordinates(labwork.getCoordinates());
        this.setAuthor(labwork.getAuthor());
    }
}
