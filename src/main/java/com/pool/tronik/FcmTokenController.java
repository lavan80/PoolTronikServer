package com.pool.tronik;

import com.pool.tronik.dataRequests.PushEntity;
import com.pool.tronik.utils.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by andreivasilevitsky on 03/05/2019.
 */
@RestController
@RequestMapping("refresh")
public class FcmTokenController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean saveToken(@RequestBody PushEntity pushEntity) {
        System.out.println("Hi "+pushEntity);
        return FileUtil.saveToken(pushEntity);
    }
}
