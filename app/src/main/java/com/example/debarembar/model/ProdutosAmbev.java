package com.example.debarembar.model;

public class ProdutosAmbev {
    private String name;
    private int imageProduct;

    public  ProdutosAmbev(String name){
        setNome(name);
    }
    public ProdutosAmbev(String name, int imageProduct){
        setNome(name);
        setImageProduct(imageProduct);
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }


}
