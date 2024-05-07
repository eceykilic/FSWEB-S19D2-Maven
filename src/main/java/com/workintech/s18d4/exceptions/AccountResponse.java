package com.workintech.s18d4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


public record AccountResponse(long id, String accountName, double moneyAmount, CustomerResponse customerResponse) {}