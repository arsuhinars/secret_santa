package com.arsuhinars.secret_santa.schema;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateSchema {
    @NotBlank
    private String name;

    private String description;
}
