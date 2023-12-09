import EditUserDetails from "../Components/EditUserDetails"
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import AuthAPI from "../APIs/AuthAPI";
import TokenManager from "../APIs/TokenManager";

const EditUserDetailsPage = () => {
    const { id } = useParams();
    const [user, setUser] = useState();
    const claims = TokenManager.getClaims();

    const getUserDetails = () => {
        if (claims?.userId == id) {
            AuthAPI.getLoggedUserDetails(id)
                .then(userDetails => setUser(userDetails))
                .catch(error => console.error(error));
        }
    }

    useEffect(() => {
        getUserDetails();
    }, []);

    const EditUserDetailsMethod = () => {
        if (!user) {
            return <>Loading...</>
        }
        const updateUser = (username, phoneNumber, firstName, lastName) => {
            const request = {
                "username": username,
                "phoneNumber": phoneNumber,
                "firstName": firstName,
                "lastName": lastName
            };
            AuthAPI.updateUserDetails(id, request)
                .catch(error => {
                    if (error.response?.data === "USER_ID_INVALID") {
                        alert("User ID invalid.");
                    }
                    else {
                        alert("Something went wrong");
                    }
                })
        }

        return (
            
            <EditUserDetails updateUser={updateUser} user={user}/>
        );
    }
    
    return (
        document.title = "Edit account details",
        <EditUserDetailsMethod />
    )
}

export default EditUserDetailsPage;