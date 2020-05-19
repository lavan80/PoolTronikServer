package com.pool.tronik.database;

import com.pool.tronik.database.util.ControllerKind;
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
    @Column(name="controllerip")
    private String controllerIp;
    @Column(name="controllertype")
    private int controllerType = ControllerType.WEB_RELAY.ordinal();//currently the switcher only
    @Column(name="controllerkind")
    private int controllerKind = ControllerKind.POOL.ordinal();//what is connected in

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

    public int getControllerKind() {
        return controllerKind;
    }

    public void setControllerKind(int controllerKind) {
        this.controllerKind = controllerKind;
    }
}
