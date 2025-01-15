"use client";
import React, { useState } from "react";

const Calculator = () => {
  const keypadValues = [
    "7",
    "8",
    "9",
    "4",
    "5",
    "6",
    "1",
    "2",
    "3",
    "0",
    ".",
    "=",
    "%",
    "/",
    "+",
    "*",
    "-",
    "C",
  ];
  const [inputValue, setInputValue] = useState<string>("");
  const [result, setResult] = useState<string>("");

  const handleKeypadClick = (value: string) => {
    setInputValue((prevValue) => prevValue + value);
  };

  const handleOnKeyPress = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "=") {
      event.preventDefault()
      handleCalculate();
    }
  };

  const handleCalculate = () => {
    setResult(eval(inputValue));
  };

  const handleButtonRender = (item: string) => {
    if (item === "=") {
      return (
        <button
          className="w-full bg-blue-400 rounded-md"
          onClick={handleCalculate}
          key={item}
        >
          <p>{item}</p>
        </button>
      );
    }
    if (item === "C") {
      return (
        <button
          className="w-full bg-blue-400 rounded-md"
          onClick={() => {
            setInputValue("");
            setResult("");
          }}
          key={item}
        >
          <p>{item}</p>
        </button>
      );
    }
    return (
      <button
        className="w-full bg-gray-400 rounded-md"
        onClick={() => handleKeypadClick(item)}
        key={item}
      >
        <p>{item}</p>
      </button>
    );
  };

  return (
    <div className="flex flex-col gap-8">
      <p>Result: {result}</p>
      <div>
        <input
          type="text"
          dir="rtl"
          id="calculator"
          className="bg-gray-50 min-h-12 border border-gray-300 text-white text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="0"
          onChange={(e) => setInputValue(e.target.value)}
          onKeyDown={handleOnKeyPress}
          value={inputValue}
          required
        />
      </div>
      <div className="grid grid-cols-3 gap-3">
        {keypadValues.map((item) => handleButtonRender(item))}
      </div>
    </div>
  );
};
export default Calculator;
