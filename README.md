Vacation Payment Calculator - веб-приложение, которое вычисляет сумму отпускных, которая будет выплачена для переданной зарплаты за год и количеству дней в отпуске. 
Вместо количества дней отпуска можно передать дату начала и конца отпуска, тогда при расчете будут учитываться праздничные дни.
 
Пример запроса:
GET http://localhost:8080/api/v1/calculate?totalSalary=100000&vacationStartDate=2024-03-01&vacationEndDate=2024-04-01
Ответ:
{
  "data": 8196.6
}

Пример запроса с ошибками:
GET http://localhost:8080/api/v1/calculate?vacationStartDate=2024-04-03&vacationEndDate=2024-04-01
Ответ:
{
  "status": 400,
  "errors": [
    {
      "fieldName": "totalSalary",
      "detail": "totalSalary must be provided"
    },
    {
      "fieldName": "vacationPaymentRequestDto",
      "detail": "vacationStartDate must be earlier than vacationEndDate"
    }
    ]
}

