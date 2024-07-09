package ma.fsm.bankaccountservice.service;

import ma.fsm.bankaccountservice.dto.BankAccountRequestDTO;
import ma.fsm.bankaccountservice.dto.BankAccountResponceDTO;
import ma.fsm.bankaccountservice.entities.BankAccount;
import ma.fsm.bankaccountservice.mappers.AccountMapper;
import ma.fsm.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional // il faut assurrer que vous utilisez spring Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository ;
    @Autowired
    private AccountMapper accountMapper ;
    @Override
    public BankAccountResponceDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        /*
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
         */

        BankAccount bankAccount = accountMapper.fromBankAccountRequestDto_To_BankAccount(bankAccountDTO);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponceDTO bankAccountResponceDTO = accountMapper.fromBankAccountToBankAccountResponceDTO(savedBankAccount);

        return bankAccountResponceDTO;
    }
}
