import { BrowserRouter, Route, Routes } from "react-router-dom"
import NavBar from "./NavBar"
import PrivateRouter from "./PrivateRouter"
import PropertyOverviewPage from '../Pages/PropertyOverviewPage.js';
import PropertyDetailsPage from '../Pages/PropertyDetailsPage.js';
import LoginPage from '../Pages/LoginPage.js';
import SignUp from '../Pages/SignUpPage';
import Home from '../Pages/HomePage';
import CreateListing from '../Pages/CreateListing';
import NotFound from '../Pages/NotFound';
import ProfilePage from "../Pages/ProfilePage";
import EditUserDetailsPage from "../Pages/EditUserDetailsPage";
import StatisticsPage from "../Pages/StatisticsPage";
import ChatPage from "../Pages/ChatPage";
import TokenManager from "../APIs/TokenManager";
import EditPropertyDetailsPage from "../Pages/EditPropertyDetailsPage";
import AdminPropertyOverviewPage from "../Pages/AdminPropertyOverviewPage";
import { Link } from "react-router-dom";

const AppRouter = () => {

    const claims = TokenManager.getClaims();
    function UserElement({ children }) {
        if (claims?.roles?.includes('USER')) {
            return children
        }
        else {
            return (
                <div className="access-denied">
                    <h1>You do not have access to this page!</h1>
                    <Link className="go-home" onClick={goHome} to="/home"> Go back to home  </Link>
                </div>
            )
        }
    }

    function AdminElement({ children }) {
        if (claims?.roles?.includes('ADMIN')) {
            return children
        }
        else {
            return (
                <div className="access-denied">
                    <h1>You do not have access to this page!</h1>
                    <Link className="go-home" onClick={goHome} to="/home"> Go back to home  </Link>
                </div>
            )
        }
    }

    const goHome = () => {
        window.location.href = '/home'
    }

    return (
        <BrowserRouter>
            <NavBar />
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/home" element={<Home />} />
                <Route path="/create-listing" element={<PrivateRouter />}>
                    <Route path="/create-listing" element={
                        <UserElement>
                            <CreateListing />
                        </UserElement>
                    } />
                </Route>
                <Route path="/properties" element={<PrivateRouter />}>
                    <Route path="/properties" element={<PropertyOverviewPage />} />
                    <Route path="/properties/:id" element={<PropertyDetailsPage />} />
                </Route>
                <Route path='/admin-properties' element={<PrivateRouter />}>
                    <Route path="/admin-properties" element={
                        <AdminElement>
                            <AdminPropertyOverviewPage />
                        </AdminElement>
                    } />
                </Route>
                <Route path="/statistics" element={<PrivateRouter />}>
                    <Route path="/statistics" element={
                        <AdminElement>
                            <StatisticsPage />
                        </AdminElement>
                    } />
                </Route>
                <Route path="/my-account" element={<PrivateRouter />}>
                    <Route path="/my-account" element={
                        <UserElement>
                            <ProfilePage />
                        </UserElement>
                    } />
                </Route>
                <Route path="/chat" element={<PrivateRouter />}>
                    <Route path="/chat:id" element={<ChatPage />} />
                </Route>
                <Route path="/edit-account" element={<PrivateRouter />}>
                    <Route path="/edit-account/:id" element={
                        <UserElement>
                            <EditUserDetailsPage />
                        </UserElement>
                    } />
                </Route>
                <Route path="/edit-property" element={<PrivateRouter />}>
                    <Route path="/edit-property/:id" element={
                        <EditPropertyDetailsPage />
                    } />
                </Route>
                <Route path="/accounts" element={<SignUp />} />
                <Route path="/accounts/tokens" element={<LoginPage />} />
                <Route path="/*" element={<NotFound />} />
            </Routes>
        </BrowserRouter>
    )
}

export default AppRouter;