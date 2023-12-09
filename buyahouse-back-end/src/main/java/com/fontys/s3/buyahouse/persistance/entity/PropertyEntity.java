package com.fontys.s3.buyahouse.persistance.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "property")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Length(min =2, max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @NotNull
    @Min(1)
    @Column(name = "area")
    private Integer area;
    @NotNull
    @Min(1)
    @Column(name = "rooms")
    private Integer rooms;
    @NotNull
    @Min(1)
    @Column(name = "price")
    private Double price;
    @Column(name = "city")
    private String city;
    @Column(name = "parking_included")
    private Boolean parkingIncluded;
    @Column(name = "furnished")
    private Boolean furnished;
    @NotNull
    @Min(1)
    @Column(name = "max_occupancy")
    private Integer maxOccupancy;
    @Column(name = "outside_area")
    private Boolean outsideArea;
    @NotNull
    @Column(name = "property_type_id")
    private Integer propertyTypeId;
    @Column(name = "address")
    private String address;
    @NotNull
    @Column(name = "construction_year")
    private Integer constructionYear;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "photo_link")
    private String photoLink;
}
