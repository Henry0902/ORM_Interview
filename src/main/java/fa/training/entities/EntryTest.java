package fa.training.entities;

import fa.training.dao.converter.EntryTestResultsConverter;
import fa.training.dao.enums.EntryTestResults;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ENTRY_TEST")
@Entity
@Builder
public class EntryTest {

    @Id
    @Column(name = "TEST_ID", unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;
    @Column(name = "TIME", columnDefinition = "varchar(225)",nullable = false)
    private String time;
    @Column(name = "DATE",nullable = false)
    private LocalDate date;
    @Column(name = "LANGUAGE_VALUATOR",columnDefinition = "varchar(225)",nullable = false)
    private String languageValuator;
    @Column(name = "LANGUAGE_RESULT", scale = 2,precision = 4,nullable = false)
    @Min(0)
    @Max(10)
    private BigDecimal languageResult;
    @Column(name = "TECHNICAL_VALUATOR", columnDefinition = "varchar(225)",nullable = false)
    private String technicalValuator;

    //Decimal(4,2)
    @Column(name = "TECHNICAL_RESULT", scale = 2, precision = 4 ,nullable = false)
    private BigDecimal technicalResult;
    @Convert(converter = EntryTestResultsConverter.class)
    @Column(name = "RESULT",columnDefinition = "varchar(225)",nullable = false)
    private EntryTestResults result;
    @Column(name = "REMARK",columnDefinition = "varchar(1000)",nullable = false)
    private String remark;
    @Column(name = "ENTRY_TEST_SKILL",columnDefinition = "varchar(225)",nullable = false)
    private String entryTestSkill;

    @ManyToOne
    @JoinColumn(name = "CANDIDATE_ID",referencedColumnName = "CANDIDATE_ID")
    @ToString.Exclude
    private Candidate candidate;
}
