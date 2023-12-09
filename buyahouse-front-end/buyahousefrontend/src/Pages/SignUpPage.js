import AuthAPI from "../APIs/AuthAPI";
import InputNewUser from '../Components/InputNewUser';

function SignUp() {
    const addUser = (username, password, phoneNumber, firstName, lastName) => {
        const user = {
            "username": username,
            "password": password,
            "phoneNumber": phoneNumber,
            "firstName": firstName,
            "lastName": lastName
        };
        AuthAPI.signup(user)
            .then(id => {
                if (id !== null) {
                    alert("Account created!");
                    window.location.href='/accounts/tokens'
                }
            })
            .catch(error => {
                if (error.response?.status === 400
                    && error.response?.data === "USERNAME_DUPLICATED") {
                    alert("User item duplicated.");
                } else {
                    alert("Something went wrong. Please try again later.");
                }
            })
    }

    return (
        document.title = "Sign up",
        <InputNewUser addUser={addUser} />
    );
}

export default SignUp;