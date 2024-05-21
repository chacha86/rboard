"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import Link from "next/link";
import { Pagination } from "./Pagination";

export interface Article {
  id: number;
  title: string;
  content: string;
  member: {
    username: string
  };
}
export default function Home() {
  const params = useSearchParams();
  const pageParam = params.get("page");
  const initialPage = pageParam ? Number(pageParam) : 0;
  const [page, setPage] = useState(initialPage);
  const [articles, setArticles] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const baseUrl = "http://localhost:8088/api/v1/articles";
    const param = "?page=" + page;
    const url = baseUrl + param;

    fetch(url)
      .then((response) => {
        console.log("response success");
        return response.json();
      })
      .then((data) => {
        console.log(data);
        setArticles(data.content);
        console.log(data.content[0].member);
        setIsLoading(true);
        setTotalPages(data.totalPages);
      })
      .catch((error) => {
        console.error(error);
      });

  }, [page]);

  const handlePage = (page: number) => {
    setPage(page);
  }

  if (!isLoading) {
    return <div>loading..</div>;
  }

  return (
    <div>
      <div>
        <h1 className="py-[20px] font-bold text-[1.5rem] text-center">목록</h1>
      </div>
      <div>
        <ul className="menu bg-base-200 w-[800px] rounded-box">
          {articles.map((article: Article) => (
            <li key={article.id}>
              <Link href={`/detail/${article.id}`} className="flex justify-between">
                <div>{article.id}</div>
                <div>{article.title}</div>
                <div>{article.member.username}</div>
              </Link>
            </li>
          ))}
        </ul>
        <Pagination currentPage={page} endPageNo={totalPages} handlePage={handlePage} />
      </div>
    </div>
  );
}
