package kz.iitu.project.configuration;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by Kasyanov Maxim on 2/9/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "kz.iitu.project.*")
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                        SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)
                .featuresToEnable(SerializationFeature.INDENT_OUTPUT);
        builder.indentOutput(true);
        return new MappingJackson2HttpMessageConverter(builder.build());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }
}
