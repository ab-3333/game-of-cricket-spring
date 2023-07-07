import axios from "axios";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { useTeamContext } from "../../context/TeamContext";
import { useResultContext } from "../../context/ResultContext";
import { Loader } from "../loader";
import "./start.css";

const Start = () => {
  const api = "http://localhost:8080/match/play";
  const tournamentApi = "http://localhost:8080/tournament"

  const { teams } = useTeamContext();
  const { setResults, setTournamentWinners } =
    useResultContext();

  const [isDisabled, setIsDisabled] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const onStartMatch = async (e) => {
    try {
      setIsLoading(true);
      const data = teams
        .filter(({ isPlaying }) => isPlaying)
        .map(({ teamId, teamName }) => ({ id: teamId, name: teamName }));

      await axios.post(api, data);
      const response = await axios.get(tournamentApi);
      setResults(response.data.map(item => item.match));
      setTournamentWinners(response.data.map(item => item.winner));
    } catch (error) {
      console.error(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    const numberOfPlayingTeams = teams.reduce(
      (acc, team) => acc + (team.isPlaying ? 1 : 0),
      0
    );
    const value = Math.log2(numberOfPlayingTeams);
    setIsDisabled(value === 0 || value % 1 !== 0);
  }, [teams]);

  return (
    <div className="start">
      <button
        className="button button--primary"
        disabled={isDisabled}
        onClick={onStartMatch}
      >
        <Loader isLoading={isLoading}>Play</Loader>
      </button>
      <Link to="/tournament/results/" className="link--secondary">Tournament Results</Link>
      
    </div>
  );
};

export default Start;
