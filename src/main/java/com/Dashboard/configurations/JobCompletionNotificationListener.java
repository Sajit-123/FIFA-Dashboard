package com.Dashboard.configurations;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.Dashboard.model.TeamData;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, TeamData> teams = new HashMap<>();

            em.createQuery("select m.homeTeamName, count(*) from Match m group by m.homeTeamName", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new TeamData((String) e[0], ((Long) e[1]).intValue()))
                    .forEach(team -> teams.put(team.getTeamName(), team));

            em.createQuery("select m.awayTeamName, count(*) from Match m group by m.awayTeamName", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        TeamData team = teams.get((String) e[0]);
                        if (team != null)
                            team.setTotalMatches(team.getTotalMatches() + ((Long) e[1]).intValue());
                    });

            em.createQuery("select m.winningTeam, count(*) from Match m group by m.winningTeam", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e -> {
                        TeamData team = teams.get((String) e[0]);
                        if (team != null)
                            team.setTotalWins(((Long) e[1]).intValue());
                    });

            teams.values().forEach(team -> em.persist(team));
            // teams.values().forEach(team -> System.out.println(team));

        }
    }
}
