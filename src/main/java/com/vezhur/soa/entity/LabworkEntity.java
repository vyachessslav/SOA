package com.vezhur.soa.entity;

import com.vezhur.soa.DTO.Coordinates;
import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.DTO.Person;
import com.vezhur.soa.enums.Difficulty;
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
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "coordinate_x")),
            @AttributeOverride(name = "y", column = @Column(name = "coordinate_y"))
    })
    private Coordinates coordinates;

    @Column(nullable = false)
    private Float minimalPoint;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "author_name")),
            @AttributeOverride(name = "birthday", column = @Column(name = "author_birthday")),
            @AttributeOverride(name = "weight", column = @Column(name = "author_weight")),
            @AttributeOverride(name = "location.x", column = @Column(name = "author_location_x")),
            @AttributeOverride(name = "location.y", column = @Column(name = "author_location_y")),
            @AttributeOverride(name = "location.name", column = @Column(name = "author_location_name"))
    })
    private Person author;

    public LabworkEntity(LabworkDetails labworkDetails, Integer id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.author = labworkDetails.getAuthor();
        this.name = labworkDetails.getName();
        this.difficulty = labworkDetails.getDifficulty();
        this.coordinates = labworkDetails.getCoordinates();
        this.minimalPoint = labworkDetails.getMinimalPoint();
    }

    static public LabworkEntity createLabworkEntity(LabworkDetails labworkDetails) {
        LabworkEntity labworkEntity = new LabworkEntity();
        labworkEntity.setName(labworkDetails.getName());
        labworkEntity.setMinimalPoint(labworkDetails.getMinimalPoint());
        labworkEntity.setDifficulty(labworkDetails.getDifficulty());
        labworkEntity.setCoordinates(labworkDetails.getCoordinates());
        labworkEntity.setAuthor(labworkDetails.getAuthor());
        return labworkEntity;
    }
}
