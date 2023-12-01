package com.redhat.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.redhat.model.ThreeScaleAPIManagerApplicationResponseData;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/admin/api")
@RegisterRestClient(configKey = "theescale-api")
public interface TheeScaleService {

	@GET
	@Path("/applications/find.json")
	ThreeScaleAPIManagerApplicationResponseData findApplication(
			@QueryParam("access_token") String accessToken,
			@QueryParam("user_key") String userKey,
			@QueryParam("app_id") String appId);

}