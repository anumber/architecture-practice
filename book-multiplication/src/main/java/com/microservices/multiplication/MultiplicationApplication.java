package com.microservices.multiplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
@Slf4j
public class MultiplicationApplication {


    static ConfigurableApplicationContext run;
    private static String[] args;

    public static void main(String[] args) throws JsonProcessingException {
        MultiplicationApplication.args = args;
        run = SpringApplication.run(MultiplicationApplication.class, args);
    }

    @GetMapping("/refresh")
    public void restart(){
        ExecutorService threadPool = new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>( 1 ),new ThreadPoolExecutor.DiscardOldestPolicy ());
        threadPool.execute (()->{
            run.close ();
            run = SpringApplication.run ( MultiplicationApplication.class, args);
        } );
        threadPool.shutdown();
    }
}
