package com.example.ticketapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private String name;
    private String lastBus;
    private double price;

    public Passenger() {
    }

    public Passenger(long id, long userId, String name, String lastBus, double price) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.lastBus = lastBus;
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getLastBus() {
        return lastBus;
    }

    public void setLastBus(String lastBus) {
        this.lastBus = lastBus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
