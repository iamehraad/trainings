import {TimerType} from "../types/Timen.types.ts";
import React, {useState} from "react";

const Countdown = () => {
    const [countdown, setCountdown] = useState({hours: 0, minutes: 0, seconds: 0})

    const handleInputChange = (type: TimerType, value: string) => {
        console.log(type, value)
        switch (type) {
            case TimerType.HOURS:
                setCountdown({...countdown, hours: parseInt(value)})
                break
            case TimerType.MINUTES:
                setCountdown({...countdown, minutes: parseInt(value)})
                break
            case TimerType.SECONDS:
                setCountdown({...countdown, seconds: parseInt(value)})
                break
            default:
                break
        }
    }

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()
        if (countdown.hours === 0 && countdown.minutes === 0 && countdown.seconds === 0) {
            return
        }
        const interval = setInterval(() => {
            setCountdown(prev => {
                if (prev.seconds > 0) {
                    return {...prev, seconds: prev.seconds - 1}
                } else if (prev.minutes > 0) {
                    return {...prev, minutes: countdown.minutes - 1, seconds: 59}
                } else if (prev.hours > 0) {
                    return {...prev, hours: countdown.hours - 1, minutes: 59, seconds: 59}
                } else {
                    clearInterval(interval)
                    return prev
                }
            })
        }, 1000)
    }

    return (
        <div className={"w-5/12"}>
            <div className={"flex gap-8 items-center justify-between mb-40"}>
                {Object.keys(countdown).map(item =>
                    <div className={"p-4"} key={item}>
                        <p className={"text-white text-xl"}>{item.substring(0, 1).toUpperCase()} - {countdown[item as keyof typeof countdown]}</p>
                    </div>
                )}
            </div>
            <div className={"flex-col"}>
                <h1 className={"text-white text-3xl mb-8"}>Simple countdown</h1>
                <div>
                    <form className={"flex-col gap-4 items-center"} onSubmit={handleSubmit}>
                        <div className={"flex gap-4 items-center"}>
                            <input type="number" min={0} max={24}
                                   onChange={(e) => handleInputChange(TimerType.HOURS, e.target.value)}
                                   className="block min-w-0 grow py-3 pl-3 pr-3 text-xl text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Hours"/>
                            <input type="number" min={0} max={60}
                                   onChange={(e) => handleInputChange(TimerType.MINUTES, e.target.value)}
                                   className="block min-w-0 grow py-3 pl-3 pr-3 text-xl text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Minutes"/>
                            <input type="number" min={0} max={60}
                                   onChange={(e) => handleInputChange(TimerType.SECONDS, e.target.value)}
                                   className="block min-w-0 grow py-3 pl-3 pr-3 text-xl text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Seconds"/>
                        </div>
                        <button type={"submit"} className={"mt-10 text-white rounded-xl bg-blue-500 p-2 w-40 text-xl"}>
                            Start!
                        </button>
                    </form>

                </div>
            </div>
        </div>
    )
}

export default Countdown;