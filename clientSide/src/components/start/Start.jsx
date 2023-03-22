import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useTeamContext } from "../../context/TeamContext";
import "./start.css";

const Start = () => {
  const api = "http://localhost:8080/match/play";
  const { teams } = useTeamContext();
  const [isDisabled, setIsDisabled] = useState(false);

  const onStartMatch = async (e) => {
    try {
      const data = teams
        .filter(({ isPlaying }) => isPlaying)
        .map(({ teamId, teamName }) => ({ id: teamId, name: teamName }));
      await axios.post(api, data);
    } catch (error) {
      console.error(error.message);
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
        Play
      </button>
      <Link to="/match/results" className="link--secondary"> 
      Match Results
      </Link>
    </div>
  );
};

export default Start;
