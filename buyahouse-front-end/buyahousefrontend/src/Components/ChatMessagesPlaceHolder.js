import MessageReceived from "./MessageReceived";

const ChatMessagesPlaceholder = (props) => {
    return (
        <>
            {props.messagesReceived
                .map(message => <MessageReceived key={message.id} from={message.from} seller={props.seller} text={message.text} />)}
        </>
    );
}

export default ChatMessagesPlaceholder;