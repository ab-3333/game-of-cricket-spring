import { useResultContext } from "../../context/ResultContext";
import { Link } from "react-router-dom";
import "./tournament.css";

const Tournament = () => {
  const { results, tournamentWinners } = useResultContext();

  return (
    <ul className="start__goto">
      <h2>
        Tournament Results
      </h2>
      {results &&
        results.map((_, id) => (
          <li key={id}>
            <span>{id + 1}.</span>
            <Link
              key={id}
              to={`/tournament/results/${id}`}
              className="goto__tournament"
            >
              Tournament {id + 1} Winner:
              {tournamentWinners[id]}
            </Link>
          </li>
        ))}
    </ul>
  );
};

export default Tournament;
