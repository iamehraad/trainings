import React, { useEffect, useState } from "react";
import CountDownInputs from "@/presentation/components/countdown/CountDownInputs";

const CountDown = () => {
  const [hours, setHours] = useState("00");
  const [minutes, setMinutes] = useState("00");
  const [seconds, setSeconds] = useState("00");
  const [isPlaying, setIsPlaying] = useState(false);

  const onPlayClick = () => {
    setIsPlaying(true);
  };

  useEffect(() => {
    let timeInterval = null;
    if (hours == "00" && minutes == "00" && seconds == "00") {
      setIsPlaying(false);
      return;
    }
    if (isPlaying) {
      timeInterval = setInterval(() => {
        if (seconds !== "00") {
          const newValue = parseInt(seconds) - 1;
          setSeconds(newValue < 10 ? `0${newValue}` : newValue.toString());
        }
        if (seconds == "00") {
          setSeconds("59");
        }
      }, 1000);
    }
    return () =>
      timeInterval ? clearInterval(timeInterval) : console.log("Done");
  }, [isPlaying, seconds]);

  useEffect(() => {
    if (minutes === "00" && hours !== "00") {
      const newValue = parseInt(hours) - 1;
      setHours(newValue < 10 ? `0${newValue}` : newValue.toString());
    }
  }, [minutes]);

  useEffect(() => {
    if (seconds === "00" && minutes === "00" && hours !== "00") {
      setMinutes("59");
    }
    console.log("here");
    if (seconds === "59" && minutes !== "00") {
      const newValue = parseInt(minutes) - 1;
      setMinutes(newValue < 10 ? `0${newValue}` : newValue.toString());
    }
  }, [seconds]);

  return (
    <div className={"flex flex-col gap-5"}>
      <div>
        <CountDownInputs
          onTimeChange={(hours, minutes, seconds) => {
            setHours(hours);
            setMinutes(minutes);
            setSeconds(seconds);
          }}
        />
      </div>
      <button
        onClick={onPlayClick}
        className={"bg-blue-500 hover:bg-blue-600 py-2 px-3 text-sm rounded-sm"}
      >
        Play
      </button>
      <div>
        <p>{`${hours}h ${minutes}m ${seconds}s`}</p>
      </div>
    </div>
  );
};

export default CountDown;
