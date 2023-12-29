package state;

import models.Medicine;

public class LowStockState extends State{

    public LowStockState(Medicine med) {
        super(med);
    }

    @Override
    public void changeState() {
        if(med.getStock() > 20){
            med.setStatus(new FullStockState(med));
        }
        if(checkStock()){
            med.setStatus(new OutOfStockState(med));
        }
    }

    @Override
    protected boolean checkStock() {
        if(med.getStock() < 10){
            return true;
        }else return false;
    }

    @Override
    public String showState() {
        return "Low Stock";
    }
}
