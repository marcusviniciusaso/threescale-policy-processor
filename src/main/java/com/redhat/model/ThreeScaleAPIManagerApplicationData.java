package com.redhat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThreeScaleAPIManagerApplicationData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("plan_id")
    private String planId;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("plan_name")
    private String planName;

    public ThreeScaleAPIManagerApplicationData(
            String id, String serviceId, String serviceName,
            String name, String description, String planId,
            String accountId, String planName) {
        this.id = id;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.name = name;
        this.description = description;
        this.planId = planId;
        this.accountId = accountId;
        this.planName = planName;
    }
}
