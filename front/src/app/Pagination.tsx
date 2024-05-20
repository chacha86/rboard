import { usePathname, useSearchParams, useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

export function Pagination() {
  const params = useSearchParams();
  const pageParam = params.get("page");

  const initialPage = pageParam == null ? 1 : Number(pageParam);
  console.log(initialPage);
  const [page, setPage] = useState(initialPage);

  const [pageCount, setPageCount] = useState(10);
  const [itemCount, setItemCount] = useState(10);
  const router = useRouter();
  const pathname = usePathname();

  const numbers: number[] = Array.from({ length: pageCount }, (_, i) => i + 1);

  useEffect(() => {
    router.push(`${pathname}?page=${page}`);
  }, [page]);

  return (
    <div className="join flex justify-center w-[800px] mt-[20px]">
      {numbers.map((number) => (
        <button
          key={number}
          className={
            number == page ? `join-item btn btn-active` : `join-item btn`
          }
          onClick={() => {
            setPage(number);
          }}
        >
          {number}
        </button>
      ))}
    </div>
  );
}
