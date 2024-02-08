package com.project.israel.certification.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCertificationAnswerDTO {

    private String email;
    private String technology;
    private List<QuestionAnswerDTO> questionsAnswers;

}
