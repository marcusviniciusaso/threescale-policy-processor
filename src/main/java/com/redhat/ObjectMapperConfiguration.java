package com.redhat;

import jakarta.inject.Singleton;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.jackson.ObjectMapperCustomizer;

@Singleton
public class ObjectMapperConfiguration implements ObjectMapperCustomizer {

   @Override
   public void customize(ObjectMapper objectMapper) {
      objectMapper
            .disable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
   }
}