package com.vezhur.soa.entity;

import com.vezhur.soa.DTO.LabworkData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "labwork")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LabworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float minimalPoint;

    @Column()
    private String difficulty;

    static public LabworkEntity createLabworkEntity(LabworkData labworkData) {
        LabworkEntity labworkEntity = new LabworkEntity();
        labworkEntity.setName(labworkData.getName());
        labworkEntity.setMinimalPoint(labworkData.getMinimalPoint());
        labworkEntity.setDifficulty(labworkData.getDifficulty());
        return labworkEntity;
    }
}
