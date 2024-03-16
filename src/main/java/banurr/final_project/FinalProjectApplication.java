package banurr.final_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class FinalProjectApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(FinalProjectApplication.class, args);
	}
}
