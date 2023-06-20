package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.acme.entities.data.InventoryOrderLineType;
import org.acme.entities.data.InventoryProduct;

import java.sql.Timestamp;

@Entity
@Table(name = "XXINV_INVENTORY_ORDER_LINE")
public class InventoryOrderLine extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "InventoryOrderLineSequence",
            sequenceName = "inventory_order_line",
            allocationSize = 1,
            initialValue = 400)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InventoryOrderLineSequence")
    @Column(name = "INVENTORY_ORDER_LINE_ID")
    public Integer id;

    @Column(name = "ORDER_TYPE")
    private InventoryOrderLineType type;

    @Column(name = "PRODUCT_TYPE")
    private InventoryProduct product;

    @Column(name = "QUANTITY")
    public Integer quantity;

    @ManyToOne
    @JoinColumn(name = "INVENTORY_ORDER_ID")
    @JsonIgnore
    private InventoryOrder inventoryOrder;

    public void setInventoryOrder(InventoryOrder inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
    }
}
