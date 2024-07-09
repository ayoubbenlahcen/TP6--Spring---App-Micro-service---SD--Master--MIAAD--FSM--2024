package ma.fsm.bankaccountservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fsm.bankaccountservice.enums.AccountType;

import java.util.Date;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class BankAccountRequestDTO {
    private Double balance ; // cette indique que la valuer par defaut de  balance sera null
    private String currency ;
    private AccountType type  ;
}
