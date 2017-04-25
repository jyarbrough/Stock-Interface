package sample;

import sample.models.stockFactories.Stock;

public class Context {
    private Stock stock;

    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}