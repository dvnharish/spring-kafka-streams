package com.harish.kafka.domain.consumer;

import com.harish.kafka.domain.producer.Domain;
import java.util.function.Function;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainKafkaProcessor {

  @Bean
  public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {

    return kStream ->
        kStream.filter(
            (key, domain) -> {
              if (domain.isDead()) {
                System.out.println("inactive Domain " + domain.getDomain());
              } else {
                System.out.println("active domain " + domain.getDomain());
              }
              return !domain.isDead();
            });
  }
}
