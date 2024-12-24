package com.springeasystock.easystock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.sql.Timestamp;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    private Timestamp createdAt;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "zone_id", referencedColumnName = "id")
//    private Zone zone;
//    @OneToOne(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private Zone zone;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "role_employee",
            joinColumns ={ @JoinColumn(name = "employee_id", referencedColumnName = "id")},

            inverseJoinColumns ={ @JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;
}

