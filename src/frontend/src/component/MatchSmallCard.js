import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({ match, keys, teamName }) => {
    // console.log(match.matchID);
    const otherTeam = match.homeTeamName === teamName ? match.awayTeamName : match.homeTeamName;
    const winningTeamGoals = match.winningTeam === match.homeTeamName ? match.homeTeamGoals : match.awayTeamGoals;
    const otherTeamRoute = `/Teams/${otherTeam}`;
    return (
        <div className="MatchSmallCard" key={match.matchID}>
            <h3>vs<Link to={otherTeamRoute}> {otherTeam}</Link></h3>
            <p>{match.winningTeam} won by {winningTeamGoals} goal(s)</p>
        </div>
    );
}