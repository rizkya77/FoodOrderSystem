package com.sinaukoding.finalproject.foodordersystem.entity;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "m_transaction", indexes = {
        @Index(name = "idx_transaction_created_date", columnList = "createdDate"),
        @Index(name = "idx_transaction_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_transaction_status", columnList = "status"),
        @Index(name = "idx_transaction_price", columnList = "price"),
        @Index(name = "idx_transaction_id_customer", columnList = "id_customer"),
        @Index(name = "idx_transaction_id_admin", columnList = "id_admin")
})
public class Transaction extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Min(value = 0, message = "Stok tidak boleh negatif")
    @Column(nullable = false)
    private Integer amount;

    @Column(columnDefinition = "TEXT", nullable = false)
    private Double price;

    @Min(value = 0, message = "Diskon harus lebih dari 0")
    @Max(value = 100, message = "Diskon harus kurang dari 100")
    @Column(nullable = false)
    private Integer discount;

    @Min(value = 0, message = "Harga tidak boleh negatif")
    @Column(nullable = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Food> listFood = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;
}
