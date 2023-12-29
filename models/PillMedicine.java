package models;

public class PillMedicine extends Medicine{
    private int dosage;

    public PillMedicine(String id, String name, int dosage, int stock, int price) {
        super(id, name, stock, price);
        this.dosage = dosage;
    }
    public int getDosage() {
        return dosage;
    }
    public void setDosage(int dosage) {
        this.dosage = dosage;
    }
    
}
