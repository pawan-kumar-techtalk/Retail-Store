package com.mediaocean.retailstore.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CategoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    double salesTax;

    public CategoryVo() {}

    public CategoryVo(int id, String name, double salesTax) {
        this.id = id;
        this.name = name;
        this.salesTax = salesTax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryVo that = (CategoryVo) o;
        return id == that.id &&
                salesTax == that.salesTax &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salesTax);
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salesTax=" + salesTax +
                '}';
    }
}
