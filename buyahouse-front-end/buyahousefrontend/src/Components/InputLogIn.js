import React, { useState } from "react";

function InputLogIn(props) {


    const [username, setUsername] =
        useState("dummyUsername");
    const [password, setPassword] =
        useState("dummyPassword");

    const handleSubmit = e => {
        e.preventDefault();
        props.logIn(username, password);
    }

    const censorPassword = () => {
        var x = document.getElementById("password");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }

    const usernameChanged = e => {
        setUsername(e.target.value)
    }

    const passwordChanged = e => {
        setPassword(e.target.value)
    }

    return (
        <div className="container-login">
            <h1 className="page-title" style={{ backgroundImage: `linear-gradient(to right, rgba(0,0,0,0.1), #ffb366,  rgba(0,0,0,0.1))` }}>Log in</h1>
            <form className="form-container"
                onSubmit={handleSubmit}>
                <div className="login">
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

                    <div className="login-field">
                        <label>
                            Don't have an account? <a className="signUpLink" href="/accounts">Sign up here</a>
                        </label>
                    </div>
                    <button className="loginButton">Log in</button>
                </div>
            </form>
        </div>
    )
}

export default InputLogIn;