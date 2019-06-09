package com.mediaocean.retailstore.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CartVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Integer> listProductId;

    public List<Integer> getListProductId() {
        return listProductId;
    }

    public void setListProductId(List<Integer> listProductId) {
        this.listProductId = listProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVo cartVo = (CartVo) o;
        return Objects.equals(listProductId, cartVo.listProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listProductId);
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "listProductId=" + listProductId +
                '}';
    }
}
