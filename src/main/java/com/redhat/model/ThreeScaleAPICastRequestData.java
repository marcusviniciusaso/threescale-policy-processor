package com.redhat.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThreeScaleAPICastRequestData {

	@JsonProperty("id")
	private String id;

	@JsonProperty("method")
	private String method;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("query")
	private Map<String, String> query;

	@JsonProperty("headers")
	private Map<String, String> headers;

	@JsonProperty("request_headers_size")
	private int requestHeadersSize;

	@JsonProperty("request_body_size")
	private int requestBodySize;
}
