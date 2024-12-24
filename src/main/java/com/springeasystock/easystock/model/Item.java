package com.springeasystock.easystock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "supplier", nullable = false)
    private String supplier;

    @Column(name = "size")
    private float size;

    @Column(name = "price")
    private float price;

    @Column(name = "asile")
    private int asile;

    @Column(name = "rack")
    private int rack;

    @Column(name = "shelf")
    private int shelf;

    @JsonIgnore
    @ManyToMany(mappedBy = "itemIds")
    private Set<OrderList> orderLists;
}
