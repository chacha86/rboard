'use client'
import { InputHTMLAttributes, useState } from "react";

export default function Test() {
    return (<div>
        <Parent />
    </div>)
}

function Parent() {

    const [num, setNum] = useState(1);

    const onChangeNum = (e: React.ChangeEvent<HTMLInputElement>) => {
        setNum(Number(e.target.value));
    }

    const testArr = [1, 2, 3, 4, 5];

    return (
        <>
            <input type="text" value={num} onChange={onChangeNum} />
            {testArr.map((i) => 
                (<Child key={i} num={num} />)
            )}
        </>
    );
}

function Child({ num }: { num: number }) {
    const [value, setValue] = useState(0);

    const increase = () => {
        setValue(value + num);
    }

    return (
        <div className="flex gap-2">
            <div>{value}</div>
            <button onClick={increase}>+</button>
        </div>
    )
}