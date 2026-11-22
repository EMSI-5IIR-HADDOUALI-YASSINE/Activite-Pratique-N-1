package org.sid.bank_account_service.entities;

import org.sid.bank_account_service.enums.AccountType;

import java.util.Date;

public class BankAcount {
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    private AccountType type;
}
