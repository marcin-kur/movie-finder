package uj.jwzp.w4.launchers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import uj.jwzp.w4.logic.MovieLister;
import uj.jwzp.w4.model.Movie;
import uj.jwzp.w4.tools.CommandLineDefaultParser;

@Slf4j
public class SpringMain {

    public static void main(String[] args) {
        CommandLineDefaultParser defaultParser = new CommandLineDefaultParser();
        String fileName = defaultParser.parse(args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        MovieLister lister = (MovieLister) ctx.getBean("movieLister", fileName);

        lister.moviesDirectedBy("Hoffman").stream()
                .map(Movie::toString)
                .forEach(log::info);
    }

    @Configuration
    public static class Config {
        @Bean
        @Scope(value = "prototype")
        public MovieLister movieLister(String fileName) {
            return new MovieLister(fileName);
        }
    }
}
