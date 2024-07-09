package ma.fsm.bankaccountservice.service;


import ma.fsm.bankaccountservice.dto.BankAccountRequestDTO;
import ma.fsm.bankaccountservice.dto.BankAccountResponceDTO;

public interface AccountService {
    public BankAccountResponceDTO addAccount(BankAccountRequestDTO bankAccountDTO ) ;
}
