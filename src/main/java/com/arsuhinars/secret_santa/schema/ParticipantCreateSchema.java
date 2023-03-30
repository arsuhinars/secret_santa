package com.arsuhinars.secret_santa.schema;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantCreateSchema {
    @NotBlank
    private String name;

    private String wish;
}
