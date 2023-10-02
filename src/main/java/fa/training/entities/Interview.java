package fa.training.entities;

import fa.training.dao.enums.EntryTestResults;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "INTERVIEW")
@Entity
@Builder
public class Interview {
    @Id
    @Column(name = "INTERVIEW_ID" , unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interviewId;
    @Column(name = "TIME",columnDefinition = "varchar(225)",nullable = false)
    private String time;
    @Column(name = "DATE",nullable = false)
    private LocalDate date;
    @Column(name = "INTERVIEWER",columnDefinition = "varchar(225)",nullable = false)
    private String interviewer;
    @Column(name = "COMMENTS",columnDefinition = "varchar(2000)",nullable = false)
    private String comments;
    @Column(name = "INTERVIEW_RESULT",columnDefinition = "varchar(225)",nullable = false)
    private EntryTestResults interviewResult;
    @Column(name = "REMARK",columnDefinition = "varchar(1000)",nullable = false)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID")
    private Candidate candidate;
}
