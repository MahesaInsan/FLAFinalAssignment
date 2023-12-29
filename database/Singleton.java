package database;

import java.util.ArrayList;
import java.util.HashMap;

import models.Medicine;
import models.Transaction;
import models.User;

public class Singleton implements Proxy{
    private static Singleton instance = null;
    private static HashMap<String, User> userList;
    private static HashMap<String, Medicine> medicineList;
    private static ArrayList<Transaction> transactionList;

    public static Singleton getDatabase(){
        if(instance == null){
            instance = new Singleton();
            userList = new HashMap<>();
            medicineList = new HashMap<>();
            transactionList = new ArrayList<>();
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        userList.put(user.getUsername(), user);
    }

    @Override
    public User login(String username, String password) {
        User user = userList.get(username);
        if(user == null || !password.equals(user.getPassword())){
            return null;
        }else return user;
    }

    @Override
    public HashMap<String, User> getAllUser() {
        return userList;
    }

    @Override
    public User getOneUser(String username) {
        return userList.get(username);
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineList.put(medicine.getId(), medicine);
    }

    @Override
    public void deleteMedicine(String id) {
        medicineList.remove(id);
    }

    @Override
    public HashMap<String, Medicine> getAllMedicine() {
        return medicineList;
    }

    @Override
    public Medicine getOneMedicine(String id) {
        return medicineList.get(id);
    }

    @Override
    public ArrayList<Transaction> getAllTransaction() {
        return transactionList;
    }

    @Override
    public ArrayList<Transaction> getUserTransaction(User user) {
        ArrayList<Transaction> temp = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if(transaction.getUser().equals(user)){
                temp.add(transaction);
            }
        }

        return temp;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    @Override
    public void addMedStock(Medicine medicine, int stock) {
        medicine.updateStock(stock);
    }

    
}
