import axios from "axios";
import TokenManager from "./TokenManager";

const AuthAPI = {
    login: (username, password) => axios.post('http://localhost:8080/accounts/tokens', { username, password })
        .then(response => response.data.accessToken)
        .then(accessToken => TokenManager.setAccessToken(accessToken)),
    signup: (user) => axios.post('http://localhost:8080/accounts', user)
        .then(response => response.data.id),
    getLoggedUserDetails: (id) => axios.get(`http://localhost:8080/accounts/${id}`)
        .then(repsonse => repsonse.data.userDetails),
    updateUserDetails: (id, request) => axios.put(`http://localhost:8080/accounts/${id}`, request),
    deleteUserAccount: (id) => axios.delete(`http://localhost:8080/accounts/${id}`)
}

export default AuthAPI;