package com.harish.kafka.domain.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainProducerService {

  private final String KAFKA_TOPIC_NAME = "web-domains";
  @Autowired KafkaTemplate<String, Domain> kafkaTemplate;

  public void crawlName(String name) {
    Mono<DomainList> domainListMono =
        WebClient.create()
            .get()
            .uri("https://api.domainsdb.info/v1/domains/search?domain=" + name)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(DomainList.class);

    domainListMono.subscribe(
        domainList -> {
          domainList.domains.forEach(
              domain -> {
                kafkaTemplate.send(KAFKA_TOPIC_NAME, domain);
                System.out.println("domain message -> " + domain.getDomain());
              });
        });
  }
}
