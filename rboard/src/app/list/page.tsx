"use client";

import React, {useEffect, useState} from 'react';
import {useSearchParams} from 'next/navigation';
import {useRouter} from 'next/navigation';
import {Simulate} from "react-dom/test-utils";
import change = Simulate.change;

export default function List() {


    const initPage:number = 1;
    const initKeyword:string = '';

    const [articles, setArticles] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [keyword, setKeyword] = useState(initKeyword);
    const [page, setPage] = useState(initPage);
    const [pagination, setPagination] = useState({} as any);

    const getSearchData = () => {
        fetch(`http://localhost:8088/api/v1/articles?page=${page}&keyword=${keyword}`)
            .then((res) => res.json())
            .then((data) => {
                setArticles(data.content);
                setPagination(data);
                console.log(data);
                setIsLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setIsLoading(false);
            });
    }
    const router = useRouter();
    const changeKeyword = (keyword:string) => {
        setKeyword(keyword);
        setPage(1);
        router.push(`/list?page=${page}&keyword=${keyword}`);
    }

    const changePage = (page:number) => {
        setPage(page);
        router.push(`/list?page=${page}&keyword=${keyword}`);
    }

    useEffect(() => {
        console.log('useEffect1');
        getSearchData();
        console.log('useEffect2');
    }, [keyword, page]);

    if (isLoading) return (<div>로딩중...</div>);

    return (
        <div>
            <h1 className="font-bold text-[2rem] mb-[30px]">게시물 목록</h1>
            <ul>
                {articles.map((article:{id:number, title:String}) => (
                    <li key={article.id}>
                        <a href={`/detail/${article.id}`}>{article.title}</a>
                    </li>
                ))}
            </ul>
            <hr/>
            <Pagination pagination={pagination} changePage={changePage}/>
            <Search page={page} changeKeyword={changeKeyword}/>
        </div>
    )
}

function Pagination({pagination, changePage}:any) {
    return (
        <div>
            {Array.from({length: pagination.totalPages - 1}, (_, i) => i + 1).map((i) => (
                <a key={i} className="mr-[5px] cursor-pointer" onClick={() => changePage(i)}>{i}</a>
            ))}
        </div>
    );
}

function Search({changeKeyword}:any) {
    const [keyword, setKeyword] = useState('');
    const router = useRouter();

    const changeHandler = (e:any) => {
        setKeyword(e.target.value);
    }
return (
        <div>
            <input type="text" className="border mr-5" value={keyword} onChange={changeHandler}/>
            <button onClick={() => changeKeyword(keyword)}>검색</button>
        </div>
    );
}
