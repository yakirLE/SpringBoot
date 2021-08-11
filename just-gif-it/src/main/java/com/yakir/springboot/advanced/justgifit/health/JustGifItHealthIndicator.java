package com.yakir.springboot.advanced.justgifit.health;

import javax.inject.Inject;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.yakir.springboot.advanced.justgifit.utils.JustGifItProperties;

@Component
public class JustGifItHealthIndicator implements HealthIndicator {

	@Inject
	private JustGifItProperties props;
	
	@Override
	public Health health() {
		if(!props.getGifLocation().canWrite()) {
			return Health.down().build();
		}
		
		return Health.up().build();
	}

}
