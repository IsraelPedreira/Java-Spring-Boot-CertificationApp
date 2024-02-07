package com.project.israel.certification.modules.students.useCases;

import com.project.israel.certification.modules.students.dto.VerifyIfHasCertificationDTO;
import com.project.israel.certification.modules.students.entities.CertificationStudentEntity;
import com.project.israel.certification.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    public boolean execute(VerifyIfHasCertificationDTO dto){
        List<CertificationStudentEntity> resultDB = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());

        return (!resultDB.isEmpty()) ;

    }
}
