package com.furkank.springdemo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "text")
public class Text {

    @Id
    private String id;

    private String firstInput;
    private String secondInput;
    private String output;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstInput() {
        return firstInput;
    }

    public void setFirstInput(String firstInput) {
        this.firstInput = firstInput;
    }

    public String getSecondInput() {
        return secondInput;
    }

    public void setSecondInput(String secondInput) {
        this.secondInput = secondInput;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
