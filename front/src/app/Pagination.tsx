import { usePathname, useSearchParams, useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

export function Pagination({ currentPage, endPageNo, handlePage }: { currentPage: number, endPageNo: number, handlePage: any }) {

  const [page, setPage] = useState(currentPage);
  const [pageCount, setPageCount] = useState(10);
  const [itemCount, setItemCount] = useState(10);
  const router = useRouter();
  const pathname = usePathname();

  const halfCount: number = Math.floor(pageCount / 2);
  let start: number = page - halfCount;
  let end: number = page + halfCount;

  if (start < 1) {
    start = 1;
    end = pageCount;
  }

  if (end >= endPageNo) {
    end = endPageNo;
  }

  if (end - start < 0) {
    return;
  }

  const numbers: number[] = Array.from({ length: end - start}, (_, i) => start + i);
  useEffect(() => {
    const url = `${pathname}?page=${page}`;
    router.push(url)
  }, [page]);

  return (
    <div className="flex w-[800px] gap-7 items-center justify-center">
      <button className="join-item btn btn-outline" onClick={() => {
        let tmp = page - pageCount < 0 ? 0 : page - pageCount
        setPage(tmp);
        handlePage(tmp);
      }}>Previous page</button>
      <div className="join flex justify-center mt-[20px]">
        {numbers.map((number) => (
          (
            <button
              key={number}
              className={
                number == page ? `join-item btn btn-active` : `join-item btn`
              }
              onClick={() => {
                setPage(number);
                handlePage(number);
              }}
            >
              {number}
            </button>
          )))}
      </div>
      <button className="join-item btn btn-outline" onClick={() => {
        let tmp = page + pageCount > endPageNo + 1 ? endPageNo : page + pageCount
        setPage(tmp);
        handlePage(tmp);
      }}>Next</button>
    </div>
  );
}
