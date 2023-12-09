import AuthAPI from '../APIs/AuthAPI';
import InputLogIn from '../Components/InputLogIn';


function LoginForm(props) {

  const logIn = (username, password) => {
    const user = {
      "username": username,
      "password": password
    };

    AuthAPI.login(user.username, user.password)
      .then(data => { if (data) window.location.href = '/home' })
      .catch(error => {
        if (error.response?.status === 400) {
          alert("Invalid credentials!");
        }
      })
  }

  return (
    document.title = "Log in",
    <InputLogIn logIn={logIn} />
  )
}

export default LoginForm;
