package factory;

import models.Medicine;

public abstract class MedicineFactory {
    public abstract Medicine createMedicine(String id, String name, int measure, int stock, int price);
}
