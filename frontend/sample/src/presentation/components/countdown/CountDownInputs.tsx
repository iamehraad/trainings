import React, { useEffect, useState } from "react";
import NumberInput from "@/presentation/components/input/NumberInput";

interface Props {
  onTimeChange: (hours: string, minutes: string, seconds: string) => void;
}

const CountDownInputs = ({ onTimeChange }: Props) => {
  const [hours, setHours] = useState("00");
  const [minutes, setMinutes] = useState("00");
  const [seconds, setSeconds] = useState("00");

  useEffect(() => {
    onTimeChange(hours, minutes, seconds);
  }, [hours, minutes, seconds]);

  return (
    <div className={"flex gap-3 items-end"}>
      <div className={"flex flex-col gap-2"}>
        <p className="text-center">Hours</p>
        <div className="flex gap-1">
          <NumberInput
            onChange={(value) => setHours((prev) => value + prev[1])}
          />
          <NumberInput
            onChange={(value) => setHours((prev) => prev[0] + value)}
          />
        </div>
      </div>
      <p className="text-center">:</p>
      <div className={"flex flex-col gap-2"}>
        <p className="text-center">Minutes</p>
        <div className="flex gap-1">
          <NumberInput
            onChange={(value) => setMinutes((prev) => value + prev[1])}
          />
          <NumberInput
            onChange={(value) => setMinutes((prev) => prev[0] + value)}
          />
        </div>
      </div>
      <p className="text-center">:</p>
      <div className={"flex flex-col gap-2"}>
        <p className="text-center">Seconds</p>
        <div className="flex gap-1">
          <NumberInput
            onChange={(value) => setSeconds((prev) => value + prev[1])}
          />
          <NumberInput
            onChange={(value) => setSeconds((prev) => prev[0] + value)}
          />
        </div>
      </div>
    </div>
  );
};

export default CountDownInputs;
