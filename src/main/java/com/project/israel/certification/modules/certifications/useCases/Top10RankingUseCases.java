package com.project.israel.certification.modules.certifications.useCases;

import com.project.israel.certification.modules.students.entities.CertificationStudentEntity;
import com.project.israel.certification.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCases {
    @Autowired
    CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute(){

        return this.certificationStudentRepository.findTop10ByOrderByGradeDesc();

    }
}
