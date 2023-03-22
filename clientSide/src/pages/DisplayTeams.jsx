import { Display } from "../components/display";
import { Start } from "../components/start";

const DisplayTeams = () => {
    const styles = {
        textAlign: "center",
        padding: "12px 0",
    }
    return (
        <>
            <h1 style={styles}>All Teams</h1>
            <Display />
            <Start />
        </>
    )
};

export default DisplayTeams;