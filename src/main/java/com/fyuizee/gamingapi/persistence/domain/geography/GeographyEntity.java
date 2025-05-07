package com.fyuizee.gamingapi.persistence.domain.geography;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "geography")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GeographyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}
