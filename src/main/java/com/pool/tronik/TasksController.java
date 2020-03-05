package com.pool.tronik;

import com.pool.tronik.dataRequests.PTScheduleDate;
import com.pool.tronik.database.PoolTronickRepository;
import com.pool.tronik.database.ScheduleEntity;
import com.pool.tronik.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private PoolTronickRepository poolTronickRepository;

    @RequestMapping(method = GET)
    @ResponseBody
    public List<PTScheduleDate> getTasks(@RequestParam("relay") int relay) {
        System.out.println("TasksController relay="+relay);
        List<ScheduleEntity> entityList = poolTronickRepository.findByRelay(relay);
        List<PTScheduleDate> ptScheduleDateList = new ArrayList<>();
        for (ScheduleEntity entity : entityList) {
            ptScheduleDateList.add(MapUtils.mapToPTScheduleDate(entity));
        }
        System.out.println("TasksController relay="+ptScheduleDateList.size());
        return ptScheduleDateList;
    }
    @RequestMapping(value = "/delete",method = GET)
    @ResponseBody
    public Boolean deleteTasks(@RequestParam("id") int id) {
        System.out.println("TasksController deleteTasks id="+id);
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(id);
        try {
            entity = poolTronickRepository.getOne(entity.getId());
            if (entity != null)
                poolTronickRepository.delete(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  true;
    }
}
