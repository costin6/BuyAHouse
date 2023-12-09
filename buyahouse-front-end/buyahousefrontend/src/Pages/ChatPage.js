import React, { useEffect, useState } from 'react';
import { Client } from '@stomp/stompjs';
import { v4 as uuidv4 } from 'uuid';
import ChatMessagesPlaceholder from '../Components/ChatMessagesPlaceHolder';
import SendMessagePlaceholder from '../Components/SendMessagePlaceholder';
import TokenManager from '../APIs/TokenManager';
import AuthAPI from '../APIs/AuthAPI';
import { useParams } from 'react-router-dom';
import PropertyAPI from '../APIs/PropertyAPI';


function Chat() {
  const [stompClient, setStompClient] = useState();
  const [user, setUser] = useState();
  const [messagesReceived, setMessagesReceived] = useState([]);
  const [property, setProperty] = useState();
  const { id } = useParams();
  const claims = TokenManager.getClaims();

  const setupStompClient = () => {
    const stompClient = new Client({
      brokerURL: 'ws://localhost:8080/ws',
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    stompClient.onConnect = () => {
      stompClient.subscribe(`/propertyId/${id}/queue/inboxmessages`, (data) => {
        onMessageReceived(data);
      });
    };

    stompClient.activate();

    setStompClient(stompClient);
  };

  const getPropertyDetails = () => {
    return PropertyAPI.getPropertyDetails(id)
      .then(propertyDetails => setProperty(propertyDetails))
      .catch(error => console.error(error));
  }

  const getUserDetails = () => {
    if (claims?.userId) {
      return AuthAPI.getLoggedUserDetails(claims.userId)
        .then(user => setUser(user))
        .catch(error => console.error(error));
    }
  }

  let payload;

  const sendMessage = (newMessage) => {
    if (property.user.userId) {
      payload = { 'id': uuidv4(), 'propertyId': id, 'from':user.username, 'text': newMessage.text };
      if (payload.propertyId) {
        stompClient.publish({ 'destination': `/propertyId/${payload.propertyId}/queue/inboxmessages`, body: JSON.stringify(payload) });
      }
    }
  };

  const onMessageReceived = (data) => {
    const message = JSON.parse(data.body);
    setMessagesReceived(messagesReceived => [...messagesReceived, message]);
  };

  useEffect(() => {
    getPropertyDetails()
      .then(getUserDetails)
  }, []);

  useEffect(() => {
    setupStompClient();
  }, [])

  if (!user) {
    return (
      <h1>Loading...</h1>
    )
  }
  else {
    console.log("userid", user.userId)
    return (
      document.title = "Chat",
      <div className="App">
        <p className='chat-property-name'><b>{property.name}</b> (€{property.price})</p>
        <div className="chat-messages">
          <ChatMessagesPlaceholder seller={property.user.username} username={user} messagesReceived={messagesReceived} />
        </div>
        <SendMessagePlaceholder username={user} onMessageSend={sendMessage} />
      </div>
    );
  }
}

export default Chat;