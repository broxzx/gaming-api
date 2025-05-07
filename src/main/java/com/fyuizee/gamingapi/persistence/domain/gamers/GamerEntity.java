package com.fyuizee.gamingapi.persistence.domain.gamers;

import com.fyuizee.gamingapi.persistence.domain.gamersgames.GamerProgress;
import com.fyuizee.gamingapi.persistence.domain.geography.GeographyEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Table(name = "gamers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GamerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "geography_id")
    private GeographyEntity geographyEntity;

    @OneToMany(mappedBy = "gamerEntity", fetch = FetchType.LAZY)
    private List<GamerProgress> gamerProgress;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

}
