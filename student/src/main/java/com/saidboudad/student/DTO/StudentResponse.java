package com.saidboudad.student.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

