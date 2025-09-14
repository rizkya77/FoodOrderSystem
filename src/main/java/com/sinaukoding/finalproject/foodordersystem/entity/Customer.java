package com.sinaukoding.finalproject.foodordersystem.entity;


import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_customer", indexes = {
        @Index(name = "idx_customer_created_date", columnList = "createdDate"),
        @Index(name = "idx_customer_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_customer_name", columnList = "name"),
        @Index(name = "idx_customer_nik", columnList = "nik"),
        @Index(name = "idx_customer_noHp", columnList = "noHp"),
        @Index(name = "idx_customer_address", columnList = "address"),
        @Index(name = "idx_customer_email", columnList = "email"),
        @Index(name = "idx_customer_status", columnList = "status"),
        @Index(name = "idx_customer_role", columnList = "role")
})
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nik;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String noHp;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Transaction> listTransaction = new HashSet<>();

}
