package com.byka.humanlibrary.data;

import java.io.Serializable;

public class RegistrationEvent implements Serializable {
    private Boolean isSuccess;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}
