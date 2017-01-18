package org.demo.forwardFacing.config;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("cloud")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudConfiguration implements ApplicationContextAware {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();

	}

	// @Bean
	// @LoadBalanced
	// public RestTemplate dataSource() {
	// CloudFactory cloudFactory = new CloudFactory();
	// Cloud cloud = cloudFactory.getCloud();
	// List<ServiceInfo> serviceInfoList = cloud.getServiceInfos();
	// for (ServiceInfo sinfo : serviceInfoList) {
	// RestTemplate restTemplate = cloud.getServiceConnector(sinfo.getId(),
	// RestTemplate.class, null);
	// if (restTemplate != null)
	// return restTemplate;
	// }
	// return null;
	// }

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {

		for (String beanName : context.getBeanDefinitionNames()) {

			System.out.println("bean name : " + beanName);
		}

	}

}
