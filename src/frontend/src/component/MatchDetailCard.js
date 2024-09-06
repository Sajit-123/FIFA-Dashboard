import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({ match, teamName }) => {
    let matchData = match || {};
    const otherTeam = matchData.homeTeamName === teamName ? matchData.awayTeamName : matchData.homeTeamName;
    const winningTeamGoals = matchData.winningTeam === matchData.homeTeamName ? matchData.homeTeamGoals : matchData.awayTeamGoals;
    const otherTeamRoute = `/Teams/${otherTeam}`;

    return (
        <div className="MatchDetailCard">
            <h3>Latest Matches</h3>
            <h1>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h1>
            <h2>On {matchData.dateTime}</h2>
            <h3>At {matchData.stadium}</h3>
            <h3>{matchData.winningTeam} won with {winningTeamGoals} goal(s)</h3>

        </div>
    );
}