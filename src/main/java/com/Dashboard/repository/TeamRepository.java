package com.Dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Dashboard.model.TeamData;

@Repository
public interface TeamRepository extends CrudRepository<TeamData, Integer> {

    TeamData findByTeamName(String teamName);
}
