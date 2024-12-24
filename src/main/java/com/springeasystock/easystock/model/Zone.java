package com.springeasystock.easystock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import jakarta.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "fk_wave_id", referencedColumnName = "id")
    private Set<Wave> waves;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany
    @JoinColumn(name = "fk_item_id", referencedColumnName = "id")
    private Set<Item> items;

    @Column(name = "item_count")
    private Integer itemCount;
}
