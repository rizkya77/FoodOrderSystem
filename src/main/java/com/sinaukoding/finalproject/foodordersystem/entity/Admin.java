package com.sinaukoding.finalproject.foodordersystem.entity;

import com.sinaukoding.finalproject.foodordersystem.model.enums.Role;
import com.sinaukoding.finalproject.foodordersystem.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_admin", indexes = {
        @Index(name = "idx_admin_created_date", columnList = "createdDate"),
        @Index(name = "idx_admin_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_admin_username", columnList = "username"),
        @Index(name = "idx_admin_email", columnList = "email"),
        @Index(name = "idx_admin_status", columnList = "status"),
        @Index(name = "idx_admin_role", columnList = "role")
})
public class Admin extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String token;
    private LocalDateTime expiredTokenAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin", orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Transaction> listTransaction = new HashSet<>();

}
