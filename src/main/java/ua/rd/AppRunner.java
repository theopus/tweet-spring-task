package ua.rd;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import ua.rd.config.TweetsConfig;
import ua.rd.config.UsersConfig;
import ua.rd.domain.Tweet;
import ua.rd.domain.User;
import ua.rd.service.*;

import java.util.Arrays;

/**
 * Hello world!
 */

@Configuration
@ComponentScan(value = "ua.rd")
public class AppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppRunner.class, TweetFactory.class);

        System.out.println(Arrays.toString(ctx.getBeanNamesForType(TweetService.class)));

        TweetService tweetService = (TweetService) ctx.getBean(TweetService.class);
        UserTimeLineService timeLine = (UserTimeLineService) ctx.getBean(UserTimeLineService.class);
        UserService service = ctx.getBean(UserService.class);

        //pacan
        User true_pacan = service.createUser("True Pacan");
        //ego friend
        User friend_true_pacana = service.createUser("Friend true pacana");

        //action start

        //true pacan post tweet
        Tweet sdelal_task_tweet = tweetService.tweet(true_pacan, "Sdelal task, yo.");
        System.out.println("Posted:");
        System.out.println(sdelal_task_tweet);
        System.out.println();

        //ego friend do like
        tweetService.like(friend_true_pacana, sdelal_task_tweet);
        System.out.println("After like: ");
        System.out.println(sdelal_task_tweet);
        System.out.println();

        // that friend otvechaet to tweet
        Tweet reply_frienda = tweetService.reply(friend_true_pacana, "Molodetz, krasava voobshe, dai pyatishu.", sdelal_task_tweet);
        System.out.println("FriendReply:");
        System.out.println(reply_frienda);
        System.out.println("AfterReply:");
        System.out.println(sdelal_task_tweet);
        System.out.println();

        //initial pacan pitaetca otvetit`
        try {
            //no msg length is too big :(
            tweetService.reply(true_pacan, "Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.Da Ya takoi.",
                    reply_frienda);
        } catch (TweetSizeException exeption) {
            System.out.println("After big message: ");
            System.out.println(exeption.getMessage());
            System.out.println();
        }
        //potom reshil retweetnut reply
        Tweet retweet_replya = tweetService.reTweet(true_pacan,"Lovi retvit, brat", reply_frienda);
        System.out.println("After retweet:");
        System.out.println(retweet_replya);
        System.out.println();


        //iv vot takie timeline
        System.out.println("Lenta " + true_pacan.getNickname());
        timeLine.getUserTimeline(true_pacan).forEach(tweet -> System.out.println(tweet));
        System.out.println();

        System.out.println("Lenta " + friend_true_pacana.getNickname());
        timeLine.getUserTimeline(friend_true_pacana).forEach(tweet -> System.out.println(tweet));




    }


}