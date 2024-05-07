package com.workintech.s18d4.exceptions;

import com.workintech.s18d4.entity.Address;
import lombok.Data;


public record CustomerResponse(long id, String email, double salary) {}