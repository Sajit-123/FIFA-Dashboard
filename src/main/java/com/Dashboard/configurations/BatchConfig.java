package com.Dashboard.configurations;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.Dashboard.data.MatchDataProcessor;
import com.Dashboard.data.MatchInput;
import com.Dashboard.model.Match;

@Configuration
public class BatchConfig {

        private final String[] FIELD_NAMES = new String[] {
                        "year", "date_time", "stage", "stadium", "city", "home_team_name",
                        "home_team_goals",
                        "away_team_goals", "away_team_name", "win_conditions", "attendance", "half_time_home_goals",
                        "half_time_away_goals", "referee", "assistant1", "assistant2", "round_id", "match_id",
                        "home_team_initials",
                        "away_team_initials" };

        @Bean
        public FlatFileItemReader<MatchInput> reader() {
                return new FlatFileItemReaderBuilder<MatchInput>()
                                .name("matchDataReader")
                                .resource(new ClassPathResource("WorldCupMatches.csv"))
                                .delimited()
                                .names(FIELD_NAMES)
                                .targetType(MatchInput.class)
                                .build();
        }

        @Bean
        public MatchDataProcessor processor() {
                return new MatchDataProcessor();
        }

        @Bean
        public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
                return new JdbcBatchItemWriterBuilder<Match>()
                                .sql("INSERT INTO match (year,date_time,stage,stadium,city,home_team_name,home_team_goals,away_team_goals,away_team_name,win_conditions,attendance,half_time_home_goals,half_time_away_goals,referee,assistant1,assistant2,round_id,match_id,home_team_initials,away_team_initials,winning_team)"
                                                +
                                                "VALUES (:year,:dateTime,:stage,:stadium,:city,:homeTeamName,:homeTeamGoals,:awayTeamGoals,:awayTeamName,:winConditions,:attendance,:halfTimeHomeGoals,:halfTimeAwayGoals,:referee,:assistant1,:assistant2,:roundID,:matchID,:homeTeamInitials,:awayTeamInitials,:winningTeam)")
                                .dataSource(dataSource)
                                .beanMapped()
                                .build();
        }

        @Bean
        public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
                return new JobBuilder("importUserJob", jobRepository)
                                .listener(listener)
                                .start(step1)
                                .build();
        }

        @Bean
        public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                        FlatFileItemReader<MatchInput> reader, MatchDataProcessor processor,
                        JdbcBatchItemWriter<Match> writer) {
                return new StepBuilder("step1", jobRepository)
                                .<MatchInput, Match>chunk(5, transactionManager)
                                .reader(reader)
                                .processor(processor)
                                .writer(writer)
                                .build();
        }
}
