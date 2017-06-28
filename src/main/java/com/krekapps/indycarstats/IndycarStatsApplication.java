package com.krekapps.indycarstats;

import com.krekapps.indycarstats.models.AdminSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndycarStatsApplication {
	public static AdminSession adminSession = new AdminSession(false);

	public static void main(String[] args) {
		SpringApplication.run(IndycarStatsApplication.class, args);
	}
}
