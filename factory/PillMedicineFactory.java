package factory;
import models.PillMedicine;

public class PillMedicineFactory extends MedicineFactory{

    @Override
    public PillMedicine createMedicine(String id, String name, int dosage, int stock, int price) {
        return new PillMedicine(id, name, dosage, stock, price);
    }
    
}
