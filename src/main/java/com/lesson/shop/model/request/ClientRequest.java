package com.lesson.shop.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class ClientRequest {
    @NotBlank(message = "Name can't be empty")
    private String name;
    @Pattern(regexp = "^\\+?[0-9\\-\\s]*$")
    private String phoneNumber;

}
