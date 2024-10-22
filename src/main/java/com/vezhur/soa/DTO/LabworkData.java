package com.vezhur.soa.DTO;

import com.vezhur.soa.entity.LabworkEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LabworkData {

    private String name;
    private Float minimalPoint;
    private String difficulty;

    public LabworkData(LabworkEntity labwork) {
        this.setName(labwork.getName());
        this.setMinimalPoint(labwork.getMinimalPoint());
        this.setDifficulty(labwork.getDifficulty());
    }
}
