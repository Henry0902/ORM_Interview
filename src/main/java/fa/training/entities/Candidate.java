package fa.training.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "CANDIDATE")
@Entity
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CANDIDATE_ID",unique = true)
    public Integer candidateId;

    @Column(name = "FULL_NAME", columnDefinition = "varchar(225)", nullable = false)
    private String fullName;

    @Column(name = "DATE_BIRTH",nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "GENDER",nullable = false)
    @Min(0)
    @Max(1)
    private Integer gender;

    @Column(name = "GRADUATION_YEAR",nullable = false)
    private LocalDate graduationYear;

    @Column(name = "PHONE", columnDefinition = "varchar(225)",unique = true, nullable = false)
    private String phone;

    @Column(name = "EMAIL", columnDefinition = "varchar(225)",unique = true, nullable = false)
    private String email;
    @Column(name = "SKILL", columnDefinition = "varchar(225)", nullable = false)
    private String skill;
    @Column(name = "FOREIGN_LANGUAGE", columnDefinition = "varchar(225)", nullable = false)
    private String foreignLanguage;
    @Column(name = "LEVEL",nullable = false)
    @Min(1)
    @Max(7)
    private Integer level;
    @Column(name = "CV", columnDefinition = "varchar(225)", nullable = false)
    private String cv;
    @Column(name = "ALLOCATION_STATUS",nullable = false)
    private Integer allocationStatus;
    @Column(name = "REMARK", columnDefinition = "varchar(225)", nullable = false)
    private String remark;

    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Interview> interviews;

    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EntryTest> entryTests;

    public void addInterview(Interview interview){
        if(interviews == null){
            interviews = new ArrayList<>();
        }
        this.interviews.add(interview);
    }

    public void addEntryTest(EntryTest entryTest){
        if(entryTests == null){
            entryTests = new ArrayList<>();
        }
        this.entryTests.add(entryTest);
    }

}
