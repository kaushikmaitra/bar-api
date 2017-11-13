package uk.gov.hmcts.bar.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
public abstract class PaymentInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String payerName;
    @NonNull
    private Integer amount;
    @NonNull
    @Pattern(regexp ="(?:GBP)",message = "invalid currency")
    private String currency;
    @NonNull
    private String status ;
    @NonNull
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime paymentDate = LocalDateTime.now();

    public static final String DRAFT= "draft";
    public static final String PENDING= "pending";

    public PaymentInstruction(String payerName, Integer amount, String currency) {
        this.payerName = payerName;
        this.amount = amount;
        this.currency = currency;

    }
}
