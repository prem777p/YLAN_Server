package com.prem.ylan;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class YlanApplication implements ApplicationRunner {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(YlanApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws IOException, URISyntaxException {
		String url = "http://localhost:8080/";
		openWebPage(url);
	}

	private void openWebPage(String url) throws IOException, URISyntaxException {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			// Windows
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} else if (os.contains("mac")) {
			// macOS
			Runtime.getRuntime().exec("open " + url);
		} else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
			// Unix/Linux
			Runtime.getRuntime().exec("xdg-open " + url);
		} else {
			// Unsupported platform
			System.out.println("Unsupported platform. Please open the web page manually: " + url);
		}
	}
}
