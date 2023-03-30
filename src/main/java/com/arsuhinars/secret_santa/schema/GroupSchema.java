package com.arsuhinars.secret_santa.schema;

import com.arsuhinars.secret_santa.model.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupSchema {
    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private String description;

    @NotNull
    private List<ParticipantSchema> participants;

    public GroupSchema(Group group) {
        this.id = group.getId();
        this.name = group.getName();

        if (group.getDescription() == null) {
            this.description = "";
        } else {
            this.description = group.getDescription();
        }

        this.participants = group.getParticipants()
            .stream()
            .map(ParticipantSchema::new)
            .toList();
    }
}
