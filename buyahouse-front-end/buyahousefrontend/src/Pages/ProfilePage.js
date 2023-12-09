import AuthAPI from "../APIs/AuthAPI";
import { useState, useEffect } from "react";
import TokenManager from "../APIs/TokenManager";
import UserDetails from "../Components/UserDetails";

const ProfilePage = () => {
    const [user, setUser] = useState();
    const claims = TokenManager.getClaims();

    const getUserDetails = () => {
        if (claims?.roles?.includes('USER') && claims?.userId) {
            AuthAPI.getLoggedUserDetails(claims.userId)
                .then(userDetails => setUser(userDetails))
                .catch(error => console.error(error));
        }
    }

    useEffect(() => {
        getUserDetails();
    }, []);

    const ShowUserDetails = () => {
        if (!user) {
            return <>Loading...</>
        }
        return (
            <UserDetails userDetails={user} />
        );
    }

    return (
        document.title = "My account",
        <ShowUserDetails/>
    );
};

export default ProfilePage;