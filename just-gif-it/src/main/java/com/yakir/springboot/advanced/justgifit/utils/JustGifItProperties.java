package com.yakir.springboot.advanced.justgifit.utils;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.justgifit")
public class JustGifItProperties {

	private File gifLocation;

	public File getGifLocation() {
		return gifLocation;
	}

	public void setGifLocation(File gifLocation) {
		this.gifLocation = gifLocation;
	}
}
