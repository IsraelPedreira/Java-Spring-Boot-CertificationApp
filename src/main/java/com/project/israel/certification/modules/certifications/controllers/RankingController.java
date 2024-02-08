package com.project.israel.certification.modules.certifications.controllers;

import com.project.israel.certification.modules.certifications.useCases.Top10RankingUseCases;
import com.project.israel.certification.modules.students.entities.CertificationStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    Top10RankingUseCases top10RankingUseCases;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> topStudents(){
        return this.top10RankingUseCases.execute();

    }
}
