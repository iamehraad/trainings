import {TimerType} from "../types/Timen.types.ts";
import {useState} from "react";

const Countdown = () => {
    const [countdown, setCountdown] = useState({hours: 0, minutes: 0, seconds: 0})

    const handleInputChange = (type: TimerType, value: string) => {
        console.log(type, value)
    }

    return (
        <div className={"w-full h-dvh bg-cyan-950 flex justify-center items-center"}>
            <div className={"flex-col"}>
                <h1 className={"text-white text-3xl mb-8"}>Simple countdown</h1>
                <div>
                    <form className={"flex-col gap-4 items-center"}>
                        <div className={"flex gap-4 items-center"}>
                            <input type="number" max={24}
                                   onChange={(e) => handleInputChange(TimerType.HOURS, e.target.value)}
                                   className="block min-w-0 grow py-1.5 pl-1 pr-3 text-base text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Hours"/>
                            <input type="number" max={60}
                                   onChange={(e) => handleInputChange(TimerType.MINUTES, e.target.value)}
                                   className="block min-w-0 grow py-1.5 pl-1 pr-3 text-base text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Minutes"/>
                            <input type="number" max={60}
                                   onChange={(e) => handleInputChange(TimerType.SECONDS, e.target.value)}
                                   className="block min-w-0 grow py-1.5 pl-1 pr-3 text-base text-gray-900 placeholder:text-gray-400 focus:outline focus:outline-0 sm:text-sm/6 rounded-xl"
                                   placeholder="Seconds"/>
                        </div>
                        <button type={"submit"} className={"mt-10 text-white rounded-xl bg-blue-500 p-2 w-40"}>Start!
                        </button>
                    </form>

                </div>
            </div>
        </div>
    )
}

export default Countdown;