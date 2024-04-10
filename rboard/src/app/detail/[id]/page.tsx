"use client";

import React, {useEffect, useState} from 'react';
import { useParams } from 'next/navigation'

export default function Detail() {
    const params = useParams();
    console.log(params.id);
    return (
        <h1>hihi</h1>
    );
}