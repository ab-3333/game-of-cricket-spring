import { useEffect, useState } from "react";
import { Routes, Route } from "react-router-dom";
import { DisplayTeams, Home } from "./pages";
import { TeamContext } from "./context/TeamContext";
import axios from "axios";

const App = () => {
  const [teams, setTeams] = useState([]);

  useEffect(() => {
    const getTeams = async () => {
      try {
        const response = await axios.get("http://localhost:8080/team/all");
        const { data } = response;
        if (!data) throw new Error("No teams found");
        setTeams(data.map(({ id, name }) => ({ teamId: id, teamName: name, isPlaying: false })));
      } catch (error) {
        console.error(error.message);
      }
    };
    getTeams();
  }, []);

  return (
    <div className="container">
      <TeamContext.Provider value={{ teams, setTeams }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/display/teams" element={<DisplayTeams />} />
        </Routes>
      </TeamContext.Provider>
    </div>
  );
};

export default App;
