package com.redhat.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApplicationScoped
@Getter
@NoArgsConstructor
public class ThreeScaleAPIManagerApplicationResponseData {

    @JsonProperty("application")
    private Map<String, Object> application;
}
