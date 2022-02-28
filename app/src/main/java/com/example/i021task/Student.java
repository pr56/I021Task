package com.example.i021task;

public class Student {

    private String id;
    private String name;
    private String question;

    public Student(String name,String question,String id)
    {
        this.name = name;
        this.question = question;
        this.id = id;

    }
    public String getName()
    {
        return name;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Student()
    {


    }

    public String toString()
    {
        return name + ": " + question;
    }


}
