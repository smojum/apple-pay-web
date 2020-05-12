package com.landsend.lecom.cd.applepay.client;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors
public class StartSessionRequest {

    @NotNull
    private String validationUrl;
}


