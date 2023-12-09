import { Link, useMatch, useResolvedPath, useNavigate, NavLink } from "react-router-dom"
import TokenManager from "../APIs/TokenManager";

function NavBar() {

    const auth = TokenManager.getAccessToken();
    const claims = TokenManager.getClaims();
    let role = null;
    if (claims?.roles?.includes('ADMIN')) {
        role = "admin"
    }
    const navigate = useNavigate();
    const logout = () => {
        TokenManager.clear();
        navigate('/accounts/tokens')
    }

    return (
        <div>
            <nav className="nav">
                <NavLink to="/home" className="site-title">
                    BuyAHouse
                </NavLink>
                {
                    auth ?
                        <>
                            {
                                role ?
                                    <ul className="changable">
                                        <CustomLink to="/statistics">Statistics</CustomLink>
                                        <CustomLink to="/admin-properties">Properties</CustomLink>
                                        <CustomLink onClick={logout} to="/accounts/tokens"> Logout </CustomLink>
                                    </ul>
                                    :
                                    <ul className="changable">
                                        <CustomLink to="/create-listing">Create Listing</CustomLink>
                                        <CustomLink to="/properties">Properties</CustomLink>
                                        <CustomLink to="/my-account">My account</CustomLink>
                                        <CustomLink onClick={logout} to="/accounts/tokens"> Logout </CustomLink>
                                    </ul>
                            }
                        </>
                        :
                        <ul>
                            <CustomLink to="/accounts">Sign up</CustomLink>
                            <CustomLink to="/accounts/tokens">Log in</CustomLink>
                        </ul>
                }
            </nav>
        </div >
    )

    function CustomLink({ to, children, ...props }) {
        const resolvedPath = useResolvedPath(to)
        const isActive = useMatch({ path: resolvedPath.pathname, end: true })
        return (
            <li className={isActive ? "active" : ""}>
                <NavLink to={to} {...props}>
                    {children}
                </NavLink>
            </li>
        )
    }
}

export default NavBar;