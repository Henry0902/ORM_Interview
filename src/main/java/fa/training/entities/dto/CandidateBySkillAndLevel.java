package fa.training.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class CandidateBySkillAndLevel {
    private String skill;
    private Integer level;
}
