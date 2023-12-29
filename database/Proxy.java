package database;

import java.util.ArrayList;
import java.util.HashMap;

import models.Medicine;
import models.Transaction;
import models.User;

public interface Proxy {
    public void addUser(User user);
    public User login(String username, String password);
    public HashMap<String, User> getAllUser();
    public User getOneUser(String username);
    public void addMedicine(Medicine medicine);
    public void addMedStock(Medicine medicine, int stock);
    public void deleteMedicine(String id);
    public HashMap<String, Medicine> getAllMedicine();
    public Medicine getOneMedicine(String id);
    public ArrayList<Transaction> getAllTransaction();
    public ArrayList<Transaction> getUserTransaction(User user);
    public void addTransaction(Transaction transaction);
}
