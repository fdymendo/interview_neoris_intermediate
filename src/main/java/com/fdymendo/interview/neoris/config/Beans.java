package com.fdymendo.interview.neoris.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public ModelMapper beanModelMapper() {
    return new ModelMapper();
  }

}
