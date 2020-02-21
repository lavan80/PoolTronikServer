package com.pool.tronik;

import com.google.gson.Gson;
import com.pool.tronik.dataRequests.LeakEntety;
import com.pool.tronik.dataRequests.PushEntity;
import com.pool.tronik.pushServer.Message;
import com.pool.tronik.pushServer.Notification;
import com.pool.tronik.pushServer.Result;
import com.pool.tronik.pushServer.Sender;
import com.pool.tronik.utils.FileUtil;
import com.pool.tronik.utils.StaticVariables;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pool.tronik.utils.PushParams.ANDROID_SERVER_KEY;
import static com.pool.tronik.utils.PushParams.IOS_SERVER_KEY;

/**
 * Created by andreivasilevitsky on 29/04/2019.
 */
@RestController
@RequestMapping("/leak")
public class WaterDetectorController {

    private static final String iosToken = "dW5zG7Cuaz4:APA91bEsX051awSNBWBgBpqQ5m1PEitkYnhMtNFkeLkT4gIZZM38r5ee1OW6GS" +
            "ssMn05LLm6kyOUKLcAoRIposWSC3-PyQ4gdN5nRkC2vwJOPOMODgv6ZnykzsBzs_kcWU3oSiPXcSpF";

    private static final String SRUN_TOKEN = "fOnKJOabBJ0:APA91bGteJKFrQg3dNsKQSjjlB0FJ4SQ6-jPF_6YLa4e1xfHE1IIKpiazwMxJ" +
            "PuXWvUK-kq4AWQ4Jzm1_zhyj6N2tcOKgJHdGiAIQXxGOknNazQygHYGSjMudTfW0n_jXu8Sf5ELm9pC";

    @GetMapping
    public void showStatus(Integer gpio) {
        LeakEntety leakEntety = new LeakEntety();
        Gson gson = new Gson();
        leakEntety.setStatus(""+gpio);
        String str = gson.toJson(leakEntety);
        System.out.println("Hi "+gpio+"; str="+str+"; request="+gpio);
        sendNotifications(str);
    }

    public List<Result> sendNotifications(String msg) {
        List<Result> resultList = new ArrayList<>();
        try {
            List<PushEntity> tokenList = FileUtil.getToken();
            for (PushEntity pushEntity : tokenList) {
                Message message;
                Sender sender;
                if (pushEntity.getPlatform().equalsIgnoreCase(StaticVariables.PLATFORM_ANDROID)) {
                    sender = new Sender(ANDROID_SERVER_KEY);
                    message = createAndroidMsg(msg);
                }
                else {
                    sender = new Sender(IOS_SERVER_KEY);
                    message = createIosMsg(msg);
                }
                Result result = sender.send(message, pushEntity.getToken(), 1);
                resultList.add(result);
            }
            /*Message message = createIosMsg(msg);
            Result result = sender.send(message, SRUN_TOKEN, 1);
            resultList.add(result);*/
            //result = sender.send(message, GOOGLE_PER_USER_KEY,1);
            return resultList;
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public Message createAndroidMsg(String msg) {
        Message message = new Message.Builder()
                .timeToLive(120)
                .delayWhileIdle(false)
                .addData("MESSAGE_KEY", msg)
                .priority(Message.Priority.HIGH)
                .build();
        return message;
    }

    public Message createIosMsg(String msg) {
        Notification notification = new Notification.Builder("default")
                .title("Warning")
                .body("Attention water leaking").build();
        Message message = new Message.Builder()
                .timeToLive(120)
                .delayWhileIdle(false)
                .notification(notification)
                .priority(Message.Priority.HIGH)
                .build();
        return message;
    }
}
