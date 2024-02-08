package com.project.israel.certification.modules.students.useCases;

import com.project.israel.certification.modules.questions.entities.AlternativesEntity;
import com.project.israel.certification.modules.questions.entities.QuestionEntity;
import com.project.israel.certification.modules.questions.repositories.QuestionRepository;
import com.project.israel.certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.project.israel.certification.modules.students.dto.VerifyIfHasCertificationDTO;
import com.project.israel.certification.modules.students.entities.AnswersCertificationEntity;
import com.project.israel.certification.modules.students.entities.CertificationStudentEntity;
import com.project.israel.certification.modules.students.entities.StudentEntity;
import com.project.israel.certification.modules.students.repositories.CertificationStudentRepository;
import com.project.israel.certification.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        //Verifications
        boolean hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyIfHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification){
            throw new Exception("You've already gotten your certification!");
        }

        List<AnswersCertificationEntity> answersCertifications = new ArrayList<>();

        AtomicInteger countCorrectAnswers = new AtomicInteger();

        //Search question by technology
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionsAnswers().stream().forEach( questionAnswer -> {
           Stream<QuestionEntity> question = questionsEntity.stream().filter(element -> element.getId().equals(questionAnswer.getQuestionId()));

           AlternativesEntity findCorrectAlternative = question.findFirst().get().getAlternatives().stream().filter(alternative -> alternative.is_correct()).findFirst().get();

           if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())){
               questionAnswer.setCorrect(true);
               countCorrectAnswers.set(countCorrectAnswers.get() + 1);
           }else{
               questionAnswer.setCorrect(false);
           }

           AnswersCertificationEntity answersCertificationEntity = AnswersCertificationEntity.builder()
                   .answerId(questionAnswer.getAlternativeId())
                   .questionId(questionAnswer.getQuestionId())
                   .isCorrect(questionAnswer.isCorrect())
                   .build();

           answersCertifications.add(answersCertificationEntity);

        });

        //Verify if student exists

        Optional<StudentEntity> student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if(student.isEmpty()){
            StudentEntity studentCreate = StudentEntity.builder()
                    .email(dto.getEmail())
                    .build();

            studentRepository.save(studentCreate);
            studentId = studentCreate.getId();
        }else{
            studentId = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .Student_id(studentId)
                .technology(dto.getTechnology())
                .grade(countCorrectAnswers.get())
                .build();

        CertificationStudentEntity certificatonCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answer -> {
            answer.setCertificationId(certificationStudentEntity.getId());
            answer.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationEntity(answersCertifications);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificatonCreated;

    }
}
