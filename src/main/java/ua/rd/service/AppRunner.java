package ua.rd.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import ua.rd.domain.Tweet;

import java.util.Map;

/**
 * Hello world!
 *
 */

@Configuration
@ComponentScan("ua.rd")
public class AppRunner
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppRunner.class);
//        TimelineOperationService bean = ctx.getBean(TimelineOperationService.class);
//        bean.tweet()
//        System.out.println( "Hello World!" );

        Map<String, TweetService> beansOfType = ctx.getBeansOfType(TweetService.class);
        beansOfType.forEach((s, tweetService) -> System.out.println(s + " " + tweetService));
        TweetService bean = ctx.getBean(TweetService.class);
        Tweet sssss = bean.tweet(null, "sddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsdjmjjjjhjho;hiluguyfgiytfukgjhvlbj.drytjfukyjgluhrydtufygiuhdsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsddsdd");
        sssss.getText();

    }


}