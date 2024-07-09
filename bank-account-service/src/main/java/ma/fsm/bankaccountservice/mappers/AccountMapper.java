package ma.fsm.bankaccountservice.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fsm.bankaccountservice.dto.BankAccountRequestDTO;
import ma.fsm.bankaccountservice.dto.BankAccountResponceDTO;
import ma.fsm.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountMapper {
    public BankAccountResponceDTO fromBankAccountToBankAccountResponceDTO(BankAccount bankAccount) {
        BankAccountResponceDTO bankAccountResponceDTO = new BankAccountResponceDTO() ;
        BeanUtils.copyProperties(bankAccount , bankAccountResponceDTO);
        return  bankAccountResponceDTO ;
    }
    public BankAccount fromBankAccountRequestDto_To_BankAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO , bankAccount);
        bankAccount.setId(UUID.randomUUID().toString());
        return  bankAccount ;
    }
}
