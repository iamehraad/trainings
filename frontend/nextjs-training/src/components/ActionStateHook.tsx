import {useActionState} from "react";

import React from 'react';

const ActionStateHook = () => {
    function AddToCartForm() {
        const [message, formAction, isPending] = useActionState(
            // Action to trigger. Usually an async job which returns something, that return value will replace actual state value
            () => {
                return "Done!"
            },
            // Default value
            null);
        return (
            <form action={formAction}>
                <input type="hidden" name="itemID" value={1}/>
                <button type="submit">Add to Cart</button>
                {isPending ? "Loading..." : message}
            </form>
        );
    }

    return (
        <div>
            <h1>useActionState</h1>
            <AddToCartForm/>
        </div>
    );
}
export default ActionStateHook;
