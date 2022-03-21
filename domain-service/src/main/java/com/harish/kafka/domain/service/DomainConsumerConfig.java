package com.harish.kafka.domain.service;

import com.harish.kafka.domain.producer.Domain;
import java.util.function.Consumer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConsumerConfig {

  @Bean
  public Consumer<KStream<String, Domain>> domainService() {

    return stringDomainKStream ->
        stringDomainKStream.foreach(
            (key, domain) -> {
              System.out.println(
                  String.format(
                      "Domain consumed [%s] and Status is [%s]",
                      domain.getDomain(), domain.isDead()));
            });
  }
}
