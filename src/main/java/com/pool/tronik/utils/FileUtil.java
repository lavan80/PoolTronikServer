package com.pool.tronik.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.dataRequests.PushEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreivasilevitsky on 08/05/2019.
 */
public class FileUtil {

    public static void saveToken(PushEntity pushEntity) {
        try {
            Gson gson = new Gson();
            RandomAccessFile randomAccessFile = new RandomAccessFile("token.txt","rw");
            Type listType = new TypeToken<List<PushEntity>>() {}.getType();
            String json = randomAccessFile.readLine();
            List<PushEntity> entityList = null;
            if (json != null) {
                entityList = gson.fromJson(json, listType);
                int i =0;
                for (;i < entityList.size(); i++) {
                    PushEntity tmp = entityList.get(i);
                    if (tmp.getUniqId().equals(pushEntity.getUniqId())) {
                        entityList.remove(tmp);
                        break;
                    }
                }
                entityList.add(pushEntity);
            }
            else {
                entityList = new ArrayList<>();
                entityList.add(pushEntity);
            }
            json = gson.toJson(entityList,listType);
            randomAccessFile.setLength(0);
            randomAccessFile.write(json.getBytes());
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PushEntity> getToken() {
        List<PushEntity> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            RandomAccessFile randomAccessFile = new RandomAccessFile("token.txt","rw");
            Type listType = new TypeToken<List<PushEntity>>() {}.getType();
            String json = randomAccessFile.readLine();
            list = gson.fromJson(json, listType);
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveScheduledDate(PTScheduleDate mScheduleData) {
        Gson gson = new Gson();
        String file;
        if (mScheduleData.getStatus() == StaticVariables.STATUS_OFF)
            file = "scheduledOn.txt";
        else if (mScheduleData.getStatus() == StaticVariables.STATUS_ON)
            file = "scheduledOn.txt";
        else
            return;//????
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
