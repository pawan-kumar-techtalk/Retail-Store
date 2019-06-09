package com.mediaocean.retailstore.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemizedBillVo implements Serializable {

    private static final long serialVersionUID = 1L;

    List<ProductVo> productList;
    double totalAmount;

    public List<ProductVo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductVo> productList) {
        this.productList = productList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemizedBillVo that = (ItemizedBillVo) o;
        return Double.compare(that.totalAmount, totalAmount) == 0 &&
                Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList, totalAmount);
    }

    @Override
    public String toString() {
        return "ItemizedBillVo{" +
                "productList=" + productList +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
