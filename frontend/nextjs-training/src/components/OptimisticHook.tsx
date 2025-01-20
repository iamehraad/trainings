import {useOptimistic, useState, useRef} from "react";


const OptimisticHook = () => {
    function Thread({messages, sendMessage}) {
        const formRef = useRef();
        // Actual state
        const [messages, setMessages] = useState([
            {text: "Hello there!", sending: false, key: 1}
        ]);

        async function formAction(formData) {
            addOptimisticMessage(formData.get("message"));
            formRef.current.reset();
            await sendMessage(formData);
        }

        // Show something while state is being updated while showing something as value to user:
        // ${value} is sending...
        const [optimisticMessages, addOptimisticMessage] = useOptimistic(
            messages,
            // Refers to state
            (state, newMessage) => [
                ...state,
                {
                    text: newMessage,
                    sending: true
                }
            ]
        );

        return (
            <>
                {optimisticMessages.map((message, index) => (
                    <div key={index}>
                        {message.text}
                        {!!message.sending && <small> (Sending...)</small>}
                    </div>
                ))}
                <form action={formAction} ref={formRef}>
                    <input type="text" name="message" placeholder="Hello!"/>
                    <button type="submit">Send</button>
                </form>
            </>
        );
    }
}

export default OptimisticHook;