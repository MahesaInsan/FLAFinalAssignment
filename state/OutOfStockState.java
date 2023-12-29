package state;

import models.Medicine;

public class OutOfStockState extends State{
    
    public OutOfStockState(Medicine med) {
        super(med);
    }

    @Override
    public void changeState() {
        if(med.getStock() > 5){
            med.setStatus(new LowStockState(med));
        }
        if(checkStock()){
            med.setStatus(new FullStockState(med));
        }
    }

    @Override
    protected boolean checkStock() {
        if(med.getStock() > 20){
            return true;
        }else return false;
    }

    @Override
    public String showState() {
        return "Out of Stock";
    }
}
