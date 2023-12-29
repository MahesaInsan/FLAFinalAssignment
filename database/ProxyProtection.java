package database;

import java.util.ArrayList;
import java.util.HashMap;

import models.Medicine;
import models.Transaction;
import models.User;

public class ProxyProtection implements Proxy{
    private boolean isAuthorize = false;
    private static Singleton db;

    public ProxyProtection(User user) {
        if(user.getIsAdmin()){
            this.isAuthorize = true;
        }else this.isAuthorize = false;
        db = Singleton.getDatabase();
    }

    public ProxyProtection(){
        db = Singleton.getDatabase();
    }

    @Override
    public void addUser(User user) {
        db.addUser(user);
    }

    @Override
    public User login(String username, String password) {
        return db.login(username, password);
    }

    @Override
    public HashMap<String, User> getAllUser() {
        return db.getAllUser();
    }

    @Override
    public User getOneUser(String username) {
        return db.getOneUser(username);
    }

    @Override
    public void addMedicine(Medicine medicine) {
        if(isAuthorize){
            db.addMedicine(medicine);
        }
    }

    @Override
    public void deleteMedicine(String id) {
        if(isAuthorize){
            db.deleteMedicine(id);
        }
    }

    @Override
    public HashMap<String, Medicine> getAllMedicine() {
        return db.getAllMedicine();
    }

    @Override
    public Medicine getOneMedicine(String id) {
        return db.getOneMedicine(id);
    }

    @Override
    public ArrayList<Transaction> getAllTransaction() {
        if(isAuthorize){
            return db.getAllTransaction();
        }else return null;
    }

    @Override
    public ArrayList<Transaction> getUserTransaction(User user) {
        return db.getAllTransaction();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        db.addTransaction(transaction);
    }

    @Override
    public void addMedStock(Medicine medicine, int stock) {
        if(isAuthorize){
            db.addMedStock(medicine, stock);
        }
    }
    
}
