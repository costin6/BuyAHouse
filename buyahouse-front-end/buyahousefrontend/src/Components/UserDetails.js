import TokenManager from "../APIs/TokenManager";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import ConfirmationDialog from "./ConfirmationDialog";
import AuthAPI from "../APIs/AuthAPI";
import PropertyAPI from "../APIs/PropertyAPI"
import AccountPropertyList from "./AccountPropertyList";

function UserDetails(props) {
    const [properties, setProperties] = useState([]);
    const userDetails = props.userDetails;
    const claims = TokenManager.getClaims();
    const id = claims.userId;
    const IsTheSameUser = () => {
        if (userDetails.userId === id) {
            return (
                <>
                    <Link to={`/edit-account/${userDetails.userId}`} className="account-details-button">
                        Edit account details
                    </Link>
                    <br />
                    <button onClick={() => handleDelete(1)} className="account-delete-button">
                        Delete account
                    </button>
                    {dialog.isLoading && (
                        <ConfirmationDialog
                            nameProduct={dialog.nameProduct}
                            onDialog={areUSureDelete}
                            message={dialog.message}
                        />
                    )}
                    <p className="user-details">Your properties:</p>
                    <AccountPropertyList propertyItems={properties} />
                </>
            )
        }
    }

    const [dialog, setDialog] = useState({
        message: "",
        isLoading: false,
        nameProduct: ""
    });
    const handleDialog = (message, isLoading) => {
        setDialog({
            message,
            isLoading
        });
    };

    const handleDelete = (id) => {
        handleDialog("Are you sure you want to delete your account? This will also delete all the properties you have created!", true);
    };

    const areUSureDelete = (choose) => {
        if (choose) {
            handleDialog("", false);
            if (claims?.roles?.includes('USER') && claims?.userId) {
                AuthAPI.deleteUserAccount(claims.userId)
                    .catch(error => console.error(error));
            }
            alert("Account successfully deleted!")
            window.location.href = '/'
            TokenManager.clear();
        } else {
            handleDialog("", false);
        }
    };

    const getPropertyList = () => {
        PropertyAPI.getPropertyOverviewByUser(claims?.userId)
            .then((properties) => setProperties(properties))
            .catch((error) => console.error(error));
    }

    useEffect(() => {
        getPropertyList();
    }, []);

    return (
        <div className="container-account-details">
            <h1 className="page-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.01), #ffb366,  rgba(0,0,0,0.01))` }}>My account</h1>
            <p className="user-details">First name: <b> {userDetails.firstName} </b> </p>
            <p className="user-details">Last name: <b> {userDetails.lastName} </b> </p>
            <p className="user-details">Phone number: <b> {userDetails.phoneNumber} </b> </p>
            <p className="user-details">Username: <b> {userDetails.username} </b> </p>
            <IsTheSameUser />
        </div>
    )
}

export default UserDetails;