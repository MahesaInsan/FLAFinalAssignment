package state;

import models.Medicine;

public class FullStockState extends State{

    public FullStockState(Medicine med) {
        super(med);
    }

    @Override
    public void changeState() {
        if(checkStock()){
            med.setStatus(new LowStockState(med));
        }
    }

    @Override
    protected boolean checkStock() {
        if(med.getStock() < 20){
            return true;
        }else return false;
    }

    @Override
    public String showState() {
        return "Full Stock";
    }
    
}
