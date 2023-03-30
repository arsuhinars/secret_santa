package com.arsuhinars.secret_santa.schema;

import com.arsuhinars.secret_santa.model.Participant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BriefParticipantSchema {
    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private String wish;

    public BriefParticipantSchema(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();

        if (participant.getWish() == null) {
            this.wish = "";
        } else {
            this.wish = participant.getWish();
        }
    }

    public BriefParticipantSchema(ParticipantSchema participant) {
        this.id = participant.getId();
        this.name = participant.getName();
        this.wish = participant.getWish();
    }
}
