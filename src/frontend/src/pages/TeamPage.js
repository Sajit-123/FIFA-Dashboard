import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { MatchSmallCard } from '../component/MatchSmallCard';
import { useParams } from 'react-router-dom';

export const TeamPage = () => {

    const [teams, setTeam] = useState();
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/Teams/${teamName}`);
                    const data = await response.json();
                    // console.log(data);
                    setTeam(data);
                } catch (error) {
                    console.log(error);
                }

            };
            fetchMatches();
        }, [teamName]
    );

    if (!teams || !teams.teamName) {
        return <h1>Team not found</h1>
    }

    return (
        teams ? (
            <div className="TeamPage">
                <h1>{teams.teamName}</h1>
                <MatchDetailCard teamName={teams.teamName} match={(teams.matches)} />
                {(teams || {}).matches.slice(1).map((match, index) => <MatchSmallCard teamName={teams.teamName} keys={index} match={match} />)}
            </div>) : null
    );
}