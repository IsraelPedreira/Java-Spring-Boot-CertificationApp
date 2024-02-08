# Certifications App

Certifications app with questions and correct answers verifications using java spring boot and Postgres

## Installation

Use the docker compose.

```bash
docker-compose up -d
```
Start Spring Boot project
```bash
mvn spring-boot:run
```

# Endpoints

## VerifyIfHasCertification
Verify is user already has certifications
 - Method: POST
 - {base_url}/students/verifyIfHasCertification

Body example:
```bash
{
    "email": "myemail@gmail.com",
    "technology": "JAVA"
}
```
## GetQuestions
Get all questions
 - Method: GET
 - {base_url}/questions/technology/JAVA


## MakeCertification
Make your certification
 - Method: POST
 - {base_url}/students/certification/answer

Body example:
```bash
{
    "email": "myemail@gmail.com",
    "technology": "JAVA",
    "questionsAnswers": [
        {
            "questionId": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
            "alternativeId": "bafdf631-6edf-482a-bda9-7dce1efb1890"
        },
        {
            "questionId": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
            "alternativeId": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
        },
        {
            "questionId": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
            "alternativeId": "d3e51a56-9b97-4bb8-9827-8bcf89f4b276"
        }
    ]
}
```

## Ranking
Get top 10 ranking users by correct questions
 - Method: GET
 - {base_url}/ranking/top10



