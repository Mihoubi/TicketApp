package com.example.ticketapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double linePrice;

    public Bus() {
    }

    public Bus(long id, String name, double linePrice) {
        this.id = id;
        this.name = name;
        this.linePrice = linePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(double linePrice) {
        this.linePrice = linePrice;
    }
}
