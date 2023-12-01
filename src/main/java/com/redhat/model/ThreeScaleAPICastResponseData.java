package com.redhat.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThreeScaleAPICastResponseData {

    @JsonProperty("status")
    private int status;

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("response_headers_size")
    private int responseHeadersSize;

    @JsonProperty("response_body_size")
    private int responseBodySize;
}
