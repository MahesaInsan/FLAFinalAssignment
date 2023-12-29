package models;

import java.util.ArrayList;

public class Transaction {
    private User user;
    private ArrayList<Medicine> medicineList;
    private int totalPrice;

    public Transaction(User user) {
        this.user = user;
        medicineList = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    public ArrayList<Medicine> getMedicineList() {
        return medicineList;
    }
    public void setMedicineList(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
    public int gettotalPrice() {
        return totalPrice;
    }
    public void settotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void addMedicine(Medicine med){
        medicineList.add(med);
        this.totalPrice += med.getPrice();
    }
    public void removeMedicine(Medicine med){
        medicineList.remove(med);
    }
    
}
