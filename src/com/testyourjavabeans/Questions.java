package com.testyourjavabeans;

public class Questions {

    private String questionText;
    private String answerText;

    public Questions(String questionText, String answerText) {
        setAnswerText(answerText);
        setQuestionText(questionText);
    }

    public  boolean isCorrectAnswer(String answer) {
        return answer.equals(answerText);
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

}