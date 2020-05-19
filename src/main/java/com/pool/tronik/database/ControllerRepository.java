package com.pool.tronik.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ControllerRepository extends JpaRepository<ControllerEntity, Long> {
    ControllerEntity findByControllerKind(int kind);
    @Transactional
    @Modifying
    @Query(value = "UPDATE settings s SET s.controllerIp = ? where s.controllerKind = ?",
    nativeQuery = true)
    int updateByControllerKind(String controllerIp, int controllerKind);
}
