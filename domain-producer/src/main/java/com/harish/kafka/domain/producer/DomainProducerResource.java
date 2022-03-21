package com.harish.kafka.domain.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainProducerResource {


    @Autowired
    private DomainProducerService domainProducerService;

    @GetMapping("/lookup/{name}")
    public String lookUp(@PathVariable("name") String name){
        domainProducerService.crawlName(name);
        return "Domain Producer has scrapped your data ";
    }


}
