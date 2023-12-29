package factory;
import models.SyrupMedicine;

public class SyrupMedicineFactory extends MedicineFactory{

    @Override
    public SyrupMedicine createMedicine(String id, String name, int volume, int stock, int price) {
        return new SyrupMedicine(id, name, volume, stock, price);
    }
    
}
