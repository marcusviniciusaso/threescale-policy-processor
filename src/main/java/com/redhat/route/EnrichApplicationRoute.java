package com.redhat.route;

import java.util.Map;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.model.ThreeScaleAPIManagerApplicationData;
import com.redhat.model.Data;
import com.redhat.model.ThreeScaleAPICastData;
import com.redhat.model.ThreeScaleAPIManagerApplicationResponseData;
import com.redhat.service.TheeScaleService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EnrichApplicationRoute extends RouteBuilder {

	@Inject
	ObjectMapper mapper;

	@Inject
	@RestClient
	TheeScaleService theeScaleService;

	@ConfigProperty(name = "camel.component.kafka.brokers")
	String camelComponentKafkaBrokers;

	@ConfigProperty(name = "kafka.topic.incoming")
	String kafkaTopicIncoming;

	@ConfigProperty(name = "kafka.topic.incoming.groupId")
	String kafkaTopicIncomingGroupId;

	@ConfigProperty(name = "kafka.topic.outgoing")
	String kafkaTopicOutgoing;

	@ConfigProperty(name = "kafka.topic.outgoing.groupId")
	String kafkaTopicOutgoingGroupId;

	@ConfigProperty(name = "theescale.api.token")
	String theeScaleApiToken;

	// @ConfigProperty(name = "elasticsearch.cluster.name")
	// String elasticsearchClusterName;

	// @ConfigProperty(name = "elasticsearch.index")
	// String elasticsearchIndex;

	@Override
	public void configure() throws Exception {

		from("kafka:" + kafkaTopicIncoming + "?brokers=" + camelComponentKafkaBrokers + "&groupId="
				+ kafkaTopicIncomingGroupId)
				.log(LoggingLevel.DEBUG, "Kafka Message - Incoming: " + "${body}")
				.process(exchange -> {
					// Obtém o corpo da mensagem Kafka
					String mensagekafkaTopicIncoming = exchange.getIn().getBody(String.class);
					System.out.println("Kafka Message - Incoming: " + mensagekafkaTopicIncoming);

					// Realiza o parse do JSON
					ThreeScaleAPICastData threeScaleAPICastData = parseJson(mensagekafkaTopicIncoming,
							ThreeScaleAPICastData.class);
					System.out.println("ThreeScaleAPICastData: " + threeScaleAPICastData);

					ThreeScaleAPIManagerApplicationResponseData threeScaleAPIManagerApplicationResponseData = new ThreeScaleAPIManagerApplicationResponseData();
					// Faz a chamada REST para obter dados adicionais
					if (threeScaleAPICastData.getResponse().getStatus() >= 200 && threeScaleAPICastData.getResponse().getStatus() <= 299) {
						threeScaleAPIManagerApplicationResponseData = theeScaleService
								.findApplication(
										theeScaleApiToken,
										threeScaleAPICastData.getUserInformation().getUserKey(),
										threeScaleAPICastData.getUserInformation().getAppId());
					}

					Data data = enrichData(threeScaleAPICastData, threeScaleAPIManagerApplicationResponseData);

					String jsonResult = convertToJson(data);
					exchange.getIn().setBody(jsonResult);
				})
				.log(LoggingLevel.DEBUG, "Kafka Message - Outgoing: " + "${body}")
				.to("kafka:" + kafkaTopicOutgoing + "?brokers=" + camelComponentKafkaBrokers + "&groupId="
						+ kafkaTopicIncomingGroupId);

				//.to("elasticsearch://" + elasticsearchClusterName + "?operation=Index&indexName=" + elasticsearchIndex);
	}

	private Data enrichData(ThreeScaleAPICastData threeScaleAPICastData,
			ThreeScaleAPIManagerApplicationResponseData threeScaleAPIManagerApplicationResponseData) {

		Data data = mapper.convertValue(threeScaleAPICastData, Data.class);

		Map<String, Object> applicationData = threeScaleAPIManagerApplicationResponseData.getApplication();
		ThreeScaleAPIManagerApplicationData additionalApplicationData = mapper.convertValue(applicationData,
				ThreeScaleAPIManagerApplicationData.class);
		data.setApplication(additionalApplicationData);

		return data;
	}

	private <T> T parseJson(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		return mapper.readValue(json, clazz);
	}

	private String convertToJson(Data data) {
		try {
			return mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
