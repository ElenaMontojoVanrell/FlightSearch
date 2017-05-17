package com.lastminute.model;

/**
 * Created on 16/05/2017.
 */
public class Airline {
    private String code;
    private String name;
    private Double infantPrice;

    public Airline(String code, String name, Double infantPrice) {
        this.code = code;
        this.name = name;
        this.infantPrice = infantPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(Double infantPrice) {
        this.infantPrice = infantPrice;
    }
}
