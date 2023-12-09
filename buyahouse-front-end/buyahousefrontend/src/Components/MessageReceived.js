import TokenManager from "../APIs/TokenManager";
const MessageReceived = (props) => {
    const claims = TokenManager.getClaims();
    let owner = null;
    console.log("props seller", props.seller)
    console.log("props from", props.from)
    if (props.seller == props.from) {
        owner = true;
        console.log("owner", owner)
    }
    if (props.from == claims.sub) {
        return (
            <div className="right-message">
                <div className="message-box">
                    {
                        owner ?
                            <p className="sender">{props.from} <b>(Owner)</b></p>
                            :
                            <p className="sender">{props.from}</p>
                    }
                    <p className="message">{props.text}</p>
                </div>
                <br />
            </div>
        );
    }
    else {
        return (
            <div className="left-message">
                <div className="message-box">
                    {
                        owner ?
                            <p className="sender">{props.from} <b>(Owner)</b></p>
                            :
                            <p className="sender">{props.from}</p>
                    }
                    <p className="message">{props.text}</p>
                </div>
                <br />
            </div>
        );
    }
};

export default MessageReceived;