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
public class ParticipantSchema {
    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    private String wish;

    @NotNull
    private BriefParticipantSchema recipient;

    public ParticipantSchema(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();

        if (participant.getWish() == null) {
            this.wish = "";
        } else {
            this.wish = participant.getWish();
        }

        this.recipient = new BriefParticipantSchema(participant.getRecipient());
    }
}
