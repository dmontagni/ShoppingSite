package it.iagica.shoppingsite;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShoppingsiteApplication.class);  //questo dispatcher sevlet indica quale sara il file di controller della nostra webapp
	}

}
