package br.edu.ifpb.exemplosjpa;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class BilheteriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilheteriaApplication.class, args);
    }

}
