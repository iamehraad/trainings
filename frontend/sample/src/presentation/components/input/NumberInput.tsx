import React from "react";

interface Props {
  onChange: (value: string) => void;
}

const NumberInput = ({ onChange }: Props) => {
  return (
    <input
      type="number"
      className="[appearance:textfield] [&::-webkit-outer-spin-button]:appearance-none [&::-webkit-inner-spin-button]:appearance-none bg-gray-50 border w-12 text-center border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
      placeholder="0"
      defaultValue={0}
      min="0"
      max="9"
      onInput={(e) => {
        const value = e.currentTarget.value;
        if (value.length > 1) {
          e.currentTarget.value = value.slice(-1);
        }
      }}
      onChange={(e) => onChange(e.target.value)}
      required
    />
  );
};

export default NumberInput;
