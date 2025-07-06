package com.example.expensetracker.repository;

import com.example.expensetracker.entity.InitDataEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface InitDataRepository extends JpaRepository<InitDataEntity, Long> {

    @Modifying
    @Query(value = "Insert into TB_INIT_DATA (DEVICE_ID, UNIQUE_ID, JWT_TOKEN) values (:deviceId, :uniqueId, :jwtToken)", nativeQuery = true)
    void insertInitData(@Param("deviceId") String deviceId, @Param("uniqueId") String uniqueId, @Param("jwtToken") String jwtToken);

    @Modifying
    @Query(value = "update TB_INIT_DATA set JWT_TOKEN =:jwtToken where UNIQUE_ID =:deviceId", nativeQuery = true)
    void updateInitJwt(@Param("deviceId") String deviceId, @Param("jwtToken") String jwtToken);

    @Query(value = "select * from TB_INIT_DATA where DEVICE_ID =:deviceId", nativeQuery = true)
    InitDataEntity getDeviceIdFromDb(@Param("deviceId") String deviceId);


}
