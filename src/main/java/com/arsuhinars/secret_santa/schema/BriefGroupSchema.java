package com.arsuhinars.secret_santa.schema;

import com.arsuhinars.secret_santa.model.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BriefGroupSchema {
    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private String description;

    public BriefGroupSchema(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
    }

    public BriefGroupSchema(GroupSchema group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
    }
}
