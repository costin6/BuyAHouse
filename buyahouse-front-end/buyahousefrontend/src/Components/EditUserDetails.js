import React, { useState, useEffect } from "react"
import { useParams } from "react-router-dom";
import TokenManager from "../APIs/TokenManager";
import AuthAPI from "../APIs/AuthAPI";

function EditUserDetails(props) {

    const [user, setUser] = useState(props.user);
    const [username, setUsername] =
        useState(user.username);
    const [phoneNumber, setPhoneNumber] =
        useState(user.phoneNumber);
    const [firstName, setFirstName] =
        useState(user.firstName);
    const [lastName, setLastName] =
        useState(user.lastName);

    const handleSubmit = e => {
        e.preventDefault();

        props.updateUser(username, phoneNumber, firstName, lastName);
        alert("Details successfully updated!")
        window.location.href='/my-account'
    }

    const Cancel = () => {
        window.location.href='/my-account'
    }

    return (
        <div className="container-edit">
            <h1 className="page-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.1), #ffb366,  rgba(0,0,0,0.1))` }}>Edit account details</h1>
            <form className="form-container"
                onSubmit={handleSubmit}>
                <div className="signUp">
                    <div className="login-field">
                        <label className="field-label">
                            First name
                        </label>
                        <br />
                        <input
                            className="input-field"
                            type="text"
                            name="firstName"
                            id="firstName"
                            autoComplete="off"
                            value={firstName}
                            onChange={(e)=> setFirstName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="login-field">
                        <label className="field-label">
                            Last name
                        </label>
                        <br />
                        <input
                            className="input-field"
                            type="text"
                            name="lastName"
                            id="lastName"
                            autoComplete="off"
                            value={lastName}
                            onChange={(e)=> setLastName(e.target.value)}
                            required
                        />
                    </div>
                    <div className="login-field">
                        <label className="field-label">
                            Phone number
                        </label>
                        <br />
                        <input
                            className="input-field"
                            type="text"
                            name="phoneNumber"
                            id="phoneNumber"
                            autoComplete="off"
                            value={phoneNumber}
                            onChange={(e)=> setPhoneNumber(e.target.value)}
                            required
                        />
                    </div>
                    <div className="login-field">
                        <label className="field-label">
                            Username
                        </label>
                        <br />
                        <input
                            className="input-field"
                            type="text"
                            name="username"
                            id="username"
                            autoComplete="off"
                            value={username}
                            onChange={(e)=> setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <button className="loginButton">Save</button>
                </div>
            </form>
            <button onClick={Cancel} className="loginButton">Cancel</button>
        </div>
    )
}

export default EditUserDetails;