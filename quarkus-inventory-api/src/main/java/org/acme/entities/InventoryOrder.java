package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "XXINV_INVENTORY_ORDER")
public class InventoryOrder extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "InventoryOrderSequence",
            sequenceName = "inventory_order",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InventoryOrderSequence")
    @Column(name = "INVENTORY_ORDER_ID")
    private Integer id;

    private String orderName;

    private Timestamp orderDate;

    private String customerName;

    private String deliveryLocationName;

    @OneToMany(mappedBy = "inventoryOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonProperty(namespace = "lineeetje")
    private List<InventoryOrderLine> lines = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeliveryLocationName() {
        return deliveryLocationName;
    }

    public void setDeliveryLocationName(String deliveryLocationName) {
        this.deliveryLocationName = deliveryLocationName;
    }

    public List<InventoryOrderLine> getLines() {
        return lines;
    }

    public void setLines(List<InventoryOrderLine> lines) {
        this.lines = lines;
    }

    public void addInventoryLine(InventoryOrderLine line){
        line.setInventoryOrder(this);
        this.lines.add(line);
    }
}
