package com.training.microservice.bank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(name = "Cards",
        description = "Schema to hold Cards information"
)
@Data
public class CardsDto {
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotEmpty(message = "CardType cannot be null or empty")
    private String cardType;

    @NotEmpty(message = "TotalLimit cannot be null or empty")
    private int totalLimit;

    @NotEmpty(message = "AmountUsed cannot be null or empty")
    private int amountUsed;

    @NotEmpty(message = "AvailableAmount cannot be null or empty")
    private int availableAmount;
}