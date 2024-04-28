package org.example.api.pojo;

import org.example.api.model.ProductInfo ;

import java.io.Serializable;
import java.util.List;


public class MultiProduct implements Serializable {
    private List<ProductInfo> beginnerTreasure;
    private List<ProductInfo> premier;
    private List<ProductInfo> individual;

    public List<ProductInfo> getBeginnerTreasure() {
        return beginnerTreasure;
    }

    public void setBeginnerTreasure(List<ProductInfo> beginnerTreasure) {
        this.beginnerTreasure = beginnerTreasure;
    }

    public List<ProductInfo> getPremier() {
        return premier;
    }

    public void setPremier(List<ProductInfo> premier) {
        this.premier = premier;
    }

    public List<ProductInfo> getIndividual() {
        return individual;
    }

    public void setIndividual(List<ProductInfo> individual) {
        this.individual = individual;
    }
}
