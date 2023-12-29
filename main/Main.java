package main;

import database.ProxyProtection;
import facade.RegistrationService;
import models.Admin;
import models.PillMedicine;
import models.SyrupMedicine;
import models.User;

public class Main {
/*  Dimas Dani Zaini - 2502021872
    Kenrick Panca Dewanto - 2501986422
    Mahesa Insan Raushanfikir - 2501981024
    Naufal Daffa Ryquelme - 2502012312 */

    public static void main(String[] args) {
        User user = new Admin("", "");
        ProxyProtection db = new ProxyProtection(user);
        db.addMedicine(new PillMedicine("P001", "Paracetamol", 200, 20, 13000));
        db.addMedicine(new SyrupMedicine("S001", "Cough", 100, 3, 27000));
        
        new RegistrationService();
    }
}