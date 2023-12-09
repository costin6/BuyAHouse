import { useState } from "react";

const SendMessagePlaceholder = (props) => {
  const [message, setMessage] = useState('');

  if (!props.username) {
    return <></>;
  }

  const onMessageSend = () => {
    if (!message) {
      alert('Please type a message!');
    }

    props.onMessageSend({ 'text': message});
    
    setMessage('');
  }

  const onSubmit = (event) => {
    event.preventDefault();
  }

  return (
    <form onSubmit={onSubmit}>
      <div>
        <input className="input-message-field" id='message' autoComplete="off" type='text' placeholder="Your message..." onChange={(event) => setMessage(event.target.value)} value={message}></input>        
        <button className="send-message-button" onClick={onMessageSend}>Send</button>
      </div>
    </form>
  );
}

export default SendMessagePlaceholder;