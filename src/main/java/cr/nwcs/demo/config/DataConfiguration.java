package cr.nwcs.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//  @Bean
//  @Profile("dev")
//  public DataSource dataSource() {
//      DriverManagerDataSource dataSource = new DriverManagerDataSource();
//      dataSource.setDriverClassName("org.h2.Driver");
//      dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//      dataSource.setUsername("sa");
//      dataSource.setPassword("sa");
//
//      return dataSource;
//  }
}
