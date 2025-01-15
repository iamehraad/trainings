package com.training.microservice.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "John"
    )
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
