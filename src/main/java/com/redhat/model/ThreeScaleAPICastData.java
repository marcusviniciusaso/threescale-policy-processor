package com.redhat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThreeScaleAPICastData {

    @JsonProperty("user_information")
    private ThreeScaleAPICastUserInformationData userInformation;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("request")
    private ThreeScaleAPICastRequestData request;

    @JsonProperty("response")
    private ThreeScaleAPICastResponseData response;

    @JsonProperty("transaction_size")
    private int transactionSize;

    public ThreeScaleAPICastData(String jsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ThreeScaleAPICastData data = objectMapper.readValue(jsonString, ThreeScaleAPICastData.class);

            this.setUserInformation(data.getUserInformation());
            this.setServiceId(data.getServiceId());
            this.setRequest(data.getRequest());
            this.setResponse(data.getResponse());
            this.setTransactionSize(data.getTransactionSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
