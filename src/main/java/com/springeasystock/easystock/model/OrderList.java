package com.springeasystock.easystock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customerId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "delivery_date")
    private Timestamp deliveryDate;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "order_list_item",
            joinColumns = @JoinColumn(name = "order_list_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> itemIds;
}

