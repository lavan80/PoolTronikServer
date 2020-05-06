package com.pool.tronik.database;

import com.pool.tronik.database.util.ControllerType;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "settings")
@Proxy(lazy = false) // FIX IT !!!!  lazyinitializationexception no session
public class ControllerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "long")
    private long id;
    private String controllerIp;
    private int controllerType = ControllerType.WEB_RELAY.ordinal();//currently the switcher only

    public String getControllerIp() {
        return controllerIp;
    }

    public void setControllerIp(String controllerIp) {
        this.controllerIp = controllerIp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getControllerType() {
        return controllerType;
    }

    public void setControllerType(int controllerType) {
        this.controllerType = controllerType;
    }
}
