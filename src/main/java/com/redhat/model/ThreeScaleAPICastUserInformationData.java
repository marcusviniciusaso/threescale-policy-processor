package com.redhat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThreeScaleAPICastUserInformationData {

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("user_key")
	private String userKey;
}
