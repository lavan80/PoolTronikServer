package com.pool.tronik.client;

import com.google.gson.Gson;
import com.pool.tronik.dataRequests.LeakEntety;
import com.pool.tronik.dataRequests.PushEntity;
import com.pool.tronik.pushServer.Message;
import com.pool.tronik.pushServer.Notification;
import com.pool.tronik.pushServer.Result;
import com.pool.tronik.pushServer.Sender;
import com.pool.tronik.utils.FileUtil;
import com.pool.tronik.utils.StaticVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pool.tronik.utils.PushParams.ANDROID_SERVER_KEY;
import static com.pool.tronik.utils.PushParams.IOS_SERVER_KEY;

public class PushClient {

    public String createMsg(LeakEntety msg) {
        String str ="";
        Gson gson = new Gson();
        if (msg == null) {
            LeakEntety leakEntety = new LeakEntety();
            leakEntety.setStatus("" + 1);
            str = gson.toJson(leakEntety);
        }
        else {
            str = gson.toJson(msg);
        }
        return str;
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
