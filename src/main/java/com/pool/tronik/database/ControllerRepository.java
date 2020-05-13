package com.pool.tronik.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ControllerRepository extends JpaRepository<ControllerEntity, Long> {
    ControllerEntity findByControllerKind(int kind);
    @Modifying
    @Query("UPDATE settings SET controllerIp = ?1 where controllerKind = ?2")
    void updateByControllerKind(int ip, int kind);
}
