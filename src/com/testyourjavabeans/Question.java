package com.testyourjavabeans;

public class Question {

    private String questionText;
    private String answerText;

    public Question(String questionText, String answerText) {
        setAnswerText(answerText);
        setQuestionText(questionText);
    }

    public boolean isCorrectAnswer(String answer) {
        return answer.toLowerCase().equals((answerText.trim()));
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

//    public String getAnswerText() {
//        return answerText;
//    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return questionText;
    }
}