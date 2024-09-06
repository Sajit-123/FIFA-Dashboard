import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../component/MatchDetailCard';
import { MatchSmallCard } from '../component/MatchSmallCard';
import { useParams } from 'react-router-dom';

export const MatchPage = () => {

    const [matches, setMatch] = useState([]);
    const { teamName } = useParams();
    const { year } = useParams();
    // const teamName = "Germany";


    useEffect(
        () => {
            const fetchMatches = async () => {
                try {
                    const response = await fetch(`http://localhost:8080/Teams/${teamName}/matches?year=${year}`);
                    const data = await response.json();
                    // console.log(data);
                    setMatch(data);
                } catch (error) {
                    console.log(error);
                }

            };
            fetchMatches();
        }, []
    );

    // if (!matches) {
    //     return <h1>Team not found</h1>
    // }

    return (

        <div className="MatchPage">
            <h1>Match page</h1>
            {matches.map(match => <MatchDetailCard teamName={teamName} match={match} />)}
        </div>
    );
}