package com.eepl.lab_back.dto.request.contact;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactRequestDTO {
    private String email;
    private String content;
}
