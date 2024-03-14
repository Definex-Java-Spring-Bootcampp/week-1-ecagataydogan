package com.patika.kredinbizdenservice;

import com.patika.kredinbizdenservice.model.Application;
import com.patika.kredinbizdenservice.model.Bank;
import com.patika.kredinbizdenservice.model.HouseLoan;
import com.patika.kredinbizdenservice.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Singleton
        Bank bank = Bank.getInstance("Ziraat");
        Bank bank2 = Bank.getInstance("Akbank");
        System.out.println(bank.getName());
        System.out.println(bank2.getName());
        User cem = new User("Cem","Dırman","cemdirman@gmail.com","password","0554996245",true);
        User user2 = new User("Esref","Dogan","cemdirman@gmail.com","password","0554996245",true);
        AppManager.registerUser(bank,cem);
        AppManager.registerUser(bank,user2);
        //Cannot be added to bank users because the e-mail address is the same
        System.out.println(bank.getUsers()); // password hashed with SHA-512
        User esref = new User("Esref","Dogan","ecagataydogan@gmail.com","password","05549962425",true);
        AppManager.registerUser(bank,esref);


        //Cem applications
        HouseLoan houseLoan = new HouseLoan(BigDecimal.valueOf(500_000),12,3.59);
        Application application = new Application(houseLoan,cem, LocalDateTime.now());
        Application application1 = new Application(houseLoan,cem,LocalDateTime.now());
        Application application2 = new Application(houseLoan,cem,LocalDateTime.now());
        List<Application> cemApplications = new ArrayList<>();
        cemApplications.add(application2);
        cemApplications.add(application1);
        cemApplications.add(application);

        //Esref applications
        HouseLoan houseLoan1 = new HouseLoan(BigDecimal.valueOf(500_000),12,3.59);
        Application application3 = new Application(houseLoan1,esref,LocalDateTime.now());
        Application application4 = new Application(houseLoan1,esref,LocalDateTime.now());
        List<Application> esrefApplications = new ArrayList<>();
        esrefApplications.add(application3);
        esrefApplications.add(application4);

        //Set user applications
        cem.setApplicationList(cemApplications);
        esref.setApplicationList(esrefApplications);

        //Applications added to bank and users
        System.out.println(bank.getUsers().get(0).getApplicationList());
        System.out.println(bank.getUsers().get(1).getApplicationList());


        System.out.println("----Find user with most application---");
        User userWithMostApplication = AppManager.findUserWithMostApplications(bank.getAllApplications());
        System.out.println(userWithMostApplication);

        System.out.println("---List applications from last month---");
        System.out.println(AppManager.listApplicationsFromLastMonth(bank.getAllApplications()));

        System.out.println("---List application by mail");
        System.out.println(AppManager.listApplicationsByUserEmail(bank,"cemdirman@gmail.com"));
        System.out.println(AppManager.listApplicationsByUserEmail(bank,"ecagataydogan@gmail.com"));


















    }
}
