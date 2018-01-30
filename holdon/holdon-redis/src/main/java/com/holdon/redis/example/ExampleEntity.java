package com.holdon.redis.example;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wd on 2018/1/29.
 */
public class ExampleEntity {

    private Long id;
    private String name;
    private BigDecimal amount;
    private List<String> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
