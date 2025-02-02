package com.training.microservice.bank.accounts.controller;


import com.training.microservice.bank.accounts.constants.AccountsConstants;
import com.training.microservice.bank.accounts.dto.CustomerDto;
import com.training.microservice.bank.accounts.dto.ResponseDto;
import com.training.microservice.bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts controller",
        description = "CRUD rest API for account"
)
@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;


    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }


    @Value("${build.version}")
    private String buildVersion;

    @Operation(
            summary = "Create account for customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping(path = "")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccountByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build information"
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

}
