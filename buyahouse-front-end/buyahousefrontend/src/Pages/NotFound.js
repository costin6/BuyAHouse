import { Link } from "react-router-dom";

function NotFound() {

    const goHome = () => {
        window.location.href = '/home'
    }

    return (
        document.title = "Not found",
        <div className="not-found">
            <h1>Not found :(</h1>
            <h3 className="oops">Oops, the page you are looking for could not be found</h3>
            <Link className="go-home" onClick={goHome} to="/home"> Go back to home  </Link>
        </div>
    )
}

export default NotFound;