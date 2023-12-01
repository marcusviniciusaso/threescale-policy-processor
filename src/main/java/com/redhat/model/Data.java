package com.redhat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Data {

	// private ThreeScaleAPICastData threeScaleAPICastData;

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

	private ThreeScaleAPIManagerApplicationData application;

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
