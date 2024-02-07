package com.project.israel.certification.modules.questions.controllers;

import com.project.israel.certification.modules.questions.dto.AlternativeResultDTO;
import com.project.israel.certification.modules.questions.dto.QuestionResultDTO;
import com.project.israel.certification.modules.questions.entities.AlternativesEntity;
import com.project.israel.certification.modules.questions.entities.QuestionEntity;
import com.project.israel.certification.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology){
        List<QuestionEntity> responseDB = this.questionRepository.findByTechnology(technology);

        List<QuestionResultDTO> filteredQuestions = responseDB.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());

        return filteredQuestions;
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question){
        QuestionResultDTO questionResultDTO =  QuestionResultDTO.builder()
                .description(question.getDescription())
                .id(question.getId())
                .technology(question.getTechnology())
                .build();

        List<AlternativeResultDTO> alternativeResultDTOs = question.getAlternatives().stream().map(alternative -> mapAlternativeDTO(alternative)).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativeResultDTOs);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(AlternativesEntity alternatives){
        return AlternativeResultDTO.builder()
                .description(alternatives.getDescription())
                .id(alternatives.getId())
                .build();
    }


}
