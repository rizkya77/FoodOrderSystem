package com.sinaukoding.finalproject.foodordersystem.entity;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_food", indexes = {
        @Index(name = "idx_food_created_date", columnList = "createdDate"),
        @Index(name = "idx_food_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_food_status", columnList = "status"),
        @Index(name = "idx_food_name", columnList = "name"),
        @Index(name = "idx_food_price", columnList = "price")
})
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(max = 100, message = "Max karakter 100")
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Min(value = 0, message = "Harga tidak boleh negatif")
    @Column(nullable = false)
    private Double price;

    @Min(value = 0, message = "Stok tidak boleh negatif")
    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<FoodImage> listImage = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaction", nullable = false)
    private Transaction transaction;

}
