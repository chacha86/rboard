"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Pagination } from "./Pagination";

export interface Article {
  id: number;
  title: string;
  content: string;
}
export default function Home() {
  const [articles, setArticles] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);

  useEffect(() => {
    fetch("http://localhost:8088/api/v1/articles")
      .then((response) => {
        console.log("response success");
        return response.json();
      })
      .then((data) => {
        setArticles(data.content);
        setIsLoading(true);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  if (!isLoading) {
    return <div>loading..</div>;
  }

  return (
    <>
      <ul className="menu bg-base-200 w-[800px] rounded-box">
        {articles.map((article: Article) => (
          <li key={article.id}>
            <div className="flex justify-between">
              <div>{article.id}</div>
              <Link href={`/detail/${article.id}`}>{article.title}</Link>
              <div>author</div>
            </div>
          </li>
        ))}
      </ul>
      <Pagination />
    </>
  );
}
