"use client";

import React, {useEffect, useState} from 'react';
import {useRouter, useSearchParams} from 'next/navigation';
import {number} from "prop-types";

export default function List2() {

    const router = useRouter();
    const params = useSearchParams();
    const pageParam = params.get('page') === null ? 1 : Number(params.get('page'));
    const keywordParam = params.get('keyword') === null ? '' : String(params.get('keyword'));

    const [articles, setArticles] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [page, setPage] = useState(pageParam);
    const [lastPageNo, setLastPageNo] = useState(0);
    const [keyword, setKeyword] = useState(keywordParam);

    // useEffect를 사용하지 않으면 무한 호출. useEffect를 사용하면 한번만 호출
    useEffect(() => {
        fetch(`http://localhost:8088/api/v1/articles?page=${page}&keyword=${keyword}`)
            .then((res) => res.json())
            .then((data) => {
                setArticles(data.content);
                setLastPageNo(data.totalPages - 1);
                setIsLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setIsLoading(false);
            });
    }, [page, keyword]);

    const movePage = (page: number) => {
        setPage(page);
        router.push(`/list2?page=${page}&keyword=${keyword}`);
    }

    const searchKeyword = (keyword: string) => {
        setKeyword(keyword);
        router.push(`/list2?page=${page}&keyword=${keyword}`);
    }

    // fetch가 비동기이기 때문에 통신 완료 될때까지 로딩화면 띄우기
    if (isLoading) return (<div>로딩중...</div>);

    // 그럼 return 이후에 fetch가 완료되면 리렌더링 되는 것인가?
    // 그렇다. 리액트에서 state가 변경되면 리렌더링 된다.
    // useEffect의 코드는 한번만 실행되고 만일 useEffect의 의존성 배열이 있다면 그 배열의 값이 변경될 때 다시 실행된다.
    return (
        <div>
            <h1 className="font-bold m-[10px] text-[2rem]">list</h1>
            <ul>
                {articles.map((article: { id: number, title: String }) => (
                    <li key={article.id}>
                        <a href={`/detail/${article.id}`}>{article.title}</a>
                    </li>
                ))}
            </ul>
            <hr/>
            <Pagination movePage={movePage} page={page} lastPageNo={lastPageNo}/>
            <Search searchKeyword={searchKeyword}/>
        </div>
    );
}

function Search({searchKeyword}:any) {
    const [keyword, setKeyword] = useState('');

    const changeHandle = (e: any) => {
        setKeyword(e.target.value);
    }

    const clickHandle = (e: any) => {
        searchKeyword(keyword);
    }

    return (
        <div>
            <input type="text" value={keyword} onChange={changeHandle}/>
            <button onClick={clickHandle}>검색</button>
        </div>
    )
}

function Pagination({movePage, page, lastPageNo}: any) {
    const [pageCountPerBlock, setPageCountPerBlock] = useState(5);

    const clickHandle = (e: any) => {
        e.preventDefault();
        movePage(Number(e.target.dataset.idx));
    }

    const arrayRange = (start:number, stop:number, step:number) =>
        Array.from(
            {length: (stop - start) / step + 1},
            (value, index) => start + index * step
        );

    const halfCount: number = Math.floor(pageCountPerBlock / 2);
    let start: number = page - halfCount;
    let end: number = page + halfCount;

    if (start < 1) {
        start = 1;
        end = pageCountPerBlock;
    }

    if (end > lastPageNo) {
        end = lastPageNo;
    }

    if(end - start < 0) {
        return;
    }

    return (
        <div>
            {
                arrayRange(start, end, 1).map((i) => (
                    <a key={i} onClick={clickHandle} data-idx={i} className="cursor-pointer mr-[5px]">{i}</a>
                ))
            }
        </div>
    )
}