package com.picpay.desafiopicpay.business.apiexterna;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autorizar {

    @JsonAlias("status")
    private String status;

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
