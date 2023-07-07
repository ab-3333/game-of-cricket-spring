import { useEffect, useState } from "react";
import { useResultContext } from "../../context/ResultContext";

import "./result.css";

const Result = ({ resId }) => {
  const { results } = useResultContext();

  const [playingTeams, setPlayingTeams] = useState([]);
  const [scoreBoard, setScoreBoard] = useState([]);
  const [oversTable, setOversTable] = useState([]);
  const [winnerOfCurrentMatch, setWinnerOfCurrentMatch] = useState("");

  const onChangeOvers = (e, oversByTwo) => {
    setOversTable(scoreBoard[oversByTwo].scorecardList);
  };

  const onChangeMatches = (e) => {
    const stringKey = e.target.value.split(" ");
    const matchIndex = parseInt(stringKey[1]);
    setWinnerOfCurrentMatch(results[resId][matchIndex].winner);
    setScoreBoard(results[resId][matchIndex].scoreCard);
    setOversTable(results[resId][matchIndex].scoreCard[0].scorecardList);
  };

  useEffect(() => {
    if (!results) return;

    setPlayingTeams(
      results[resId].map((result) => `${result.winner} vs ${result.loser}`)
    );

    setWinnerOfCurrentMatch(results[resId][0].winner);
    setScoreBoard(results[resId][0].scoreCard);
    setOversTable(results[resId][0].scoreCard[0].scorecardList);
  }, [results, resId]);

  return (
    <>
      <div className="winning__team">
        <h2>Winning Team: </h2>
        <h4>{winnerOfCurrentMatch}</h4>
      </div>

      <div>{results[resId].winner}</div>

      <div className="result">
        <div className="result__teams">
          <select onChange={(e) => onChangeMatches(e)}>
            {playingTeams?.map((team, idx) => (
              <option
                key={idx}
                className="button"
                value={`Match ${idx} - ${team}`}
              >
                {team}
              </option>
            ))}
          </select>
        </div>
        <div className="result__scoreboard">
          <div className="scoreboard__table__container">
            <div className="scoreboard">
              <div className="scoreboard__table table__header">
                <div className="table__item">Overs</div>
                <div className="table__item">
                  Overall runs till current ball
                </div>
                <div className="table__item">Score</div>
                <div className="table__item">Wicket(s)</div>
              </div>
            </div>
            {oversTable &&
              oversTable.map(({ overs, runsCurrBall, score, wickets }, idx) => (
                <div className="scoreboard" key={idx}>
                  <div className="scoreboard__table">
                    <div className="table__item">{overs}</div>
                    <div className="table__item">{runsCurrBall}</div>
                    <div className="table__item">{score}</div>
                    <div className="table__item">{wickets}</div>
                  </div>
                </div>
              ))}
          </div>
          <div className="scoreboard__button__container">
            {scoreBoard &&
              scoreBoard.map(({ scorecardList }, idx) => (
                <div className="scoreboard__button" key={idx}>
                  <button
                    className="button"
                    onClick={(e) => onChangeOvers(e, idx)}
                  >
                    {scorecardList[0].currTeam.name}'s &nbsp;
                    {idx + 1}
                  </button>
                </div>
              ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default Result;
