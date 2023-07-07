import axios from "axios";
import { useRef, useState } from "react";
import { useTeamContext } from "../../context/TeamContext";
import "./team.css";

const Team = ({ data }) => {
  const api = "http://localhost:8080/team";
  const { teamId, teamName, isPlaying, idx } = data;
  const { setTeams } = useTeamContext();
  const [isEdit, setIsEdit] = useState(false);
  const inputRef = useRef(null);

  const togglePlayingTeam = () => {
    setTeams((prev) =>
      prev.map((team) => {
        if (team.teamId === teamId)
          return { ...team, isPlaying: !team.isPlaying };
        return team;
      })
    );
  };

  const onUpdate = async (e) => {
    if (!isEdit) {
      setIsEdit(true);
    } else {
      const { current } = inputRef;
      try {
        await axios.put(api + "/update", {
          id: teamId,
          name: current.value,
        });
        setTeams((prev) =>
          prev.map((team) => {
            if (team.teamId === teamId)
              return { teamId, teamName: current.value, isPlaying };
            return team;
          })
        );
        setIsEdit(false);
      } catch (error) {
        console.error(error.message);
      }
    }
  };

  const onDelete = async (e) => {
    try {
      await axios.delete(api + "/delete/" + teamId);
      setTeams((prev) => prev.filter((team) => team.teamId !== teamId));
    } catch (error) {}
  };

  const toggleEditOrName = () => {
    if (isEdit)
      return (
        <input
          type="text"
          placeholder={teamName}
          defaultValue={teamName}
          ref={inputRef}
        />
      );

    return <span>{teamName}</span>;
  };

  return (
    <li className="team">
      <div className="team__data">
        <span>{idx + 1}</span>
        <input
          type="checkbox"
          onChange={togglePlayingTeam}
          checked={isPlaying}
        />
        {toggleEditOrName()}
      </div>
      <div className="team__actions">
        <button className="button" onClick={onUpdate}>
          {isEdit ? "Confirm" : "Update"}
        </button>
        <button className="button" onClick={onDelete}>
          Delete
        </button>
      </div>
    </li>
  );
};

export default Team;
