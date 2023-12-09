import { Link } from "react-router-dom";
import TokenManager from "../APIs/TokenManager";

function Home() {
    const auth = TokenManager.getAccessToken();
    const claims = TokenManager.getClaims();
    let role = null;
    if (claims?.roles?.includes('USER')) {
        role = "user"
    }
    return (
        document.title = "BuyAHouse",
        <div>
            <h1 className="home-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.01), #cc6600,  rgba(0,0,0,0.01))` }}>BuyAHouse</h1>
            <h2 className="home-subtitle" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.01), #ff9933,  rgba(0,0,0,0.01))` }}>Your new home is waiting for you</h2>

            {
                auth ?
                    <>
                        {
                            role ?
                                <div className="home-links">
                                    <Link to="/create-listing" className="home-navigation">
                                        List your property
                                    </Link>
                                    <Link to="/properties" className="home-navigation">
                                        See available properties
                                    </Link>
                                </div>
                                :
                                <div className="home-link-statistics">
                                    <Link to="/statistics" className="home-navigation">
                                        See statistics
                                    </Link>
                                </div>
                        }
                    </>
                    :
                    <div className="home-links">
                        <Link to="/accounts/tokens" className="home-navigation">
                            Log in
                        </Link>
                        <Link to="/accounts" className="home-navigation">
                            Sign up
                        </Link>
                    </div>
            }
        </div>
    );
}

export default Home;