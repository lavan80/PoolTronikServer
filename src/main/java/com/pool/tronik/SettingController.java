package com.pool.tronik;

import com.pool.tronik.database.ControllerEntity;
import com.pool.tronik.database.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private ControllerRepository controllerRepository;

    @PostMapping(value = "/put", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> setIp(@RequestBody ControllerEntity controllerEntity) {
        try {
            ControllerEntity entity = controllerRepository.findByControllerKind(controllerEntity.getControllerKind());
            if (entity != null)
                return new ResponseEntity<>("Already exists",HttpStatus.CONFLICT);

            controllerRepository.save(controllerEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateIp(@RequestBody ControllerEntity controllerEntity) {
        try {
            if (controllerEntity.getControllerIp() == null) {
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
            }
            ControllerEntity entity = controllerRepository.findByControllerKind(controllerEntity.getControllerKind());
            if (entity == null) {
                entity = controllerRepository.save(controllerEntity);
                if (entity == null)
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                int id = controllerRepository.
                            updateByControllerKind(controllerEntity.getControllerIp(), controllerEntity.getControllerKind());
                if (id == -1) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*@RequestMapping(method = GET)
    public String setIp(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "controller_ip";
    }*/
}
