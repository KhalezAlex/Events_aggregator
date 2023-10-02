package org.aggregator.aggregator.model.dto;

import lombok.Data;
import org.apache.el.stream.StreamELResolverImpl;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
}
