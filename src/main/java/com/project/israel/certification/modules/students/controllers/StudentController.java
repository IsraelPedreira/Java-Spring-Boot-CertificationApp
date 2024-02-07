package com.project.israel.certification.modules.students.controllers;

import com.project.israel.certification.modules.students.dto.VerifyIfHasCertificationDTO;
import com.project.israel.certification.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDTO verifyIfHasCertificationDTO ){
        boolean result = this.verifyIfHasCertificationUseCase.execute(verifyIfHasCertificationDTO);

        if (result) return "O usuário já fez a prova";

        return "O usuário pode fazer a prova";
    }
}
