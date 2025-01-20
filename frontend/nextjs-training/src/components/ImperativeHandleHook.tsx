import {useRef, useImperativeHandle} from 'react';

import React from 'react';

const ImperativeHandleHook = ({ref}) => {
    const inputRef = useRef(null);
    // Replaces actual ref, with some customized functions that we only need
    useImperativeHandle(ref, () => {
        return {
            focus() {
                inputRef.current.focus();
            },
            scrollIntoView() {
                inputRef.current.scrollIntoView();
            },
        };
    }, []);

    return <input ref={inputRef}/>;
}

export default ImperativeHandleHook;