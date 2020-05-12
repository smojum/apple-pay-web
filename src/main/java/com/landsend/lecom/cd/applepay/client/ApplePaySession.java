package com.landsend.lecom.cd.applepay.client;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class ApplePaySession {
    @Value("${lecom.applepay.merchantIdentifier}")
    private String merchantIdentifier;
    @Value("${lecom.applepay.domainName}")
    private String domainName;
    @Value("${lecom.applepay.initiative}")
    private String initiative;
    @Value("${lecom.applepay.initiativeContext}")
    private String initiativeContext;
    @Value("${lecom.applepay.displayName}")
    private String displayName;
}
