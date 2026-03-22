package co.prueba.accenture.backend.pruebaaccenturedev.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branchList = new ArrayList<>();

}
