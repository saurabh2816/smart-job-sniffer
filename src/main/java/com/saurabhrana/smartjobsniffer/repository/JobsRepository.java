package com.saurabhrana.smartjobsniffer.repository;

import com.saurabhrana.smartjobsniffer.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, String> {

    @Query(value="select * from Jobs where smartRecruiterId=?1", nativeQuery = true)
    Optional<Jobs> findBySmartRecruiterId(String id);

    @Query("SELECT j FROM Jobs j WHERE j.notified = false")
    List<Jobs> findByNotifiedFalse();
}
