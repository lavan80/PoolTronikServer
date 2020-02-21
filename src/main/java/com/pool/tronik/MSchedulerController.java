package com.pool.tronik;

import com.pool.tronik.dataRequests.MScheduleData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andreivasilevitsky on 14/06/2019.
 */
@RestController
@RequestMapping("/schedule")
public class MSchedulerController {

    @GetMapping
    public Boolean schedule(MScheduleData mScheduleData) {


        return true;
    }
}
