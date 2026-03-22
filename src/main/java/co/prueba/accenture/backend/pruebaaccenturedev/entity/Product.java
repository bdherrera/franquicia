package co.prueba.accenture.backend.pruebaaccenturedev.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(nullable = false)
    private String name;

    @ToString.Include
    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;


}
