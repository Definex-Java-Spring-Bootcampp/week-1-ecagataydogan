package com.patika.kredinbizdenservice;

import com.patika.kredinbizdenservice.model.Application;
import com.patika.kredinbizdenservice.model.Bank;
import com.patika.kredinbizdenservice.model.CreditCard;
import com.patika.kredinbizdenservice.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AppManager {

    public static void registerUser(Bank bank, User user) {
        List<User> bankUsers = bank.getUsers();
        for (User bankUser: bankUsers) {
            if (bankUser.getEmail().equals(user.getEmail())) {
                return;
            }
        }
        bankUsers.add(user);
    }

    public static User findUserWithMostApplications(List<Application> applications) {
        Map<User, Integer> userApplicationCount = new HashMap<>();
        for (Application application : applications) {
            User user = application.getUser();
            userApplicationCount.put(user, userApplicationCount.getOrDefault(user, 0) + 1);
        }
        User userWithMostApplications = null;
        int maxApplications = 0;

        for (Map.Entry<User, Integer> entry : userApplicationCount.entrySet()) {
            if (entry.getValue() > maxApplications) {
                userWithMostApplications = entry.getKey();
                maxApplications = entry.getValue();
            }
        }
        return userWithMostApplications;
    }

    public static List<Application> listApplicationsFromLastMonth(List<Application> applications) {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        return applications.stream()
                .filter(application -> application.getLocalDateTime().isAfter(oneMonthAgo))
                .collect(Collectors.toList());
    }

    public static List<CreditCard> getCreditCardsSortedByCampaignCount(Bank bank) {
        List<CreditCard> creditCards = bank.getCreditCards();
        return creditCards.stream()
                .sorted((card1, card2) -> Integer.compare(card2.getCampaignList().size(), card1.getCampaignList().size()))
                .collect(Collectors.toList());
    }

    public static List<Application> listApplicationsByUserEmail(Bank bank, String email) {
        List<User> users = bank.getUsers();
        List<Application> applications = new ArrayList<>();

        Optional<User> user = users.stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();

        if (user.isPresent()) {
            applications = user.get().getApplicationList();
        }
        return applications;
    }

}

