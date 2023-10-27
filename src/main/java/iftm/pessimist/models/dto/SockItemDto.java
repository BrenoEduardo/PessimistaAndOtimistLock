package iftm.pessimisticlock.models.dto;

import iftm.pessimisticlock.models.item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SockItemDto implements Serializable {
    private Long id;
    private int quant;
    private String name;

    public item toSockItem() {
        final item item = new item();
        item.setQuant(this.getQuant());
        item.setName(this.getName());
        return item;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuant() {
        return this.quant;
    }

    public void setQuant(int quantity) {
        this.quant = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
