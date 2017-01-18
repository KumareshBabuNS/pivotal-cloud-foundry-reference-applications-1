package org.demo.forwardFacing.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ForwardFacingService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ForwardFacingService.class);

	public String serviceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("BACKEND-SERVICE");
		if (list != null && list.size() > 0) {
			logger.debug("discoveryClient instance : "+"BACKEND-SERVICE" +list.get(0).getHost()+":"+ list.get(0).getPort() );
			logger.debug("discoveryClient instance : "+"BACKEND-SERVICE list.get(0).getUri()" +list.get(0).getUri() );

			return list.get(0).getUri().toString();
		}
		return null;
	}

	final String url = "http://BACKEND-SERVICE/greeting";

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
		String calculatedURL = serviceUrl();
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				String.class);
		return response.getBody();
	}

}
