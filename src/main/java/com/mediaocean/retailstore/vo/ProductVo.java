package com.mediaocean.retailstore.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    int categoryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    double price;

    public ProductVo() {}

    public ProductVo(int id, String name, int categoryId, double price) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryVo(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVo productVo = (ProductVo) o;
        return id == productVo.id &&
                categoryId == productVo.categoryId &&
                price == productVo.price &&
                Objects.equals(name, productVo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, price);
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                '}';
    }
}
