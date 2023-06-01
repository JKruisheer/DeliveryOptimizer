package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.jboss.resteasy.spi.touri.MappedBy;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InventoryOrder extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "InventoryOrderSequence",
            sequenceName = "inventory_order",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InventoryOrderSequence")
    @Column(name = "INVENTORY_ORDER_ID")
    private Integer id;

    public String orderName;

    private Timestamp orderDate;

    private String customerName;

    private String deliveryLocationName;

    @OneToMany(mappedBy = "inventoryOrder", cascade = CascadeType.ALL)
    @JsonProperty(namespace = "lineeetje")
    private List<InventoryOrderLine> lines = new ArrayList<>();

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
