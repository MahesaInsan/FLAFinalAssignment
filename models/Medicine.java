package models;

import state.FullStockState;
import state.LowStockState;
import state.OutOfStockState;
import state.State;

public abstract class Medicine {
    private String id;
    private String name;
    private int stock;
    private int price;
    private State status;

    public Medicine(String id, String name, int stock, int price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        if(stock < 5){
            this.status = new OutOfStockState(this);
        }else if(stock < 20){
            this.status = new LowStockState(this);
        }else this.status = new FullStockState(this);
        
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public State getStatus() {
        return status;
    }
    public void setStatus(State status) {
        this.status = status;
    }
    public void updateStock(int stock){
        this.stock += stock;
        this.status.changeState();
    }

    
}
