import React, { useState } from "react"

function InputNewUser(props) {

    const [username, setUsername] =
        useState("dummyTitle");
    const [password, setPassword] =
        useState("dummyPassword");
    const [phoneNumber, setPhoneNumber] =
        useState("dummyPhoneNumber");
    const [firstName, setFirstName] =
        useState("dummyFN");
    const [lastName, setLastName] =
        useState("dummyLN");

    const handleSubmit = e => {
        e.preventDefault();

        props.addUser(username, password, phoneNumber, firstName, lastName);
    }

    const usernameChanged = e => {
        setUsername(e.target.value)
    }

    const passwordChanged = e => {
        setPassword(e.target.value)
    }

    const phoneNumberChanged = e => {
        setPhoneNumber(e.target.value)
    }

    const firstNameChanged = e => {
        setFirstName(e.target.value)
    }

    const lastNameChanged = e => {
        setLastName(e.target.value)
    }

    const censorPassword = () => {
        var x = document.getElementById("password");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }

    return (
        <div className="container-signup">
            <h1 className="page-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.1), #ffb366,  rgba(0,0,0,0.1))` }}>Create account</h1>
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
                            onChange={firstNameChanged}
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
                            onChange={lastNameChanged}
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
                            onChange={phoneNumberChanged}
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
                            onChange={usernameChanged}
                            required
                        />
                    </div>
                    <div className="login-field">
                        <label className="field-label">
                            Password
                        </label>
                        <br />
                        <input
                            className="input-field"
                            type="password"
                            name="password"
                            id="password"
                            autoComplete="off"
                            onChange={passwordChanged}
                            required
                        />
                    </div>
                    <div className="login-field">
                        <input type="checkbox" onClick={censorPassword} id="show-password" />
                        <label for="show-password">Show password</label>
                    </div>
                    <button className="loginButton">Sign up</button>
                </div>
            </form>
        </div>
    )
}

export default InputNewUser;