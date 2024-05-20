"use client";
import { useEffect, useRef, useState } from "react";
import { Article } from "@/app/page";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/react-editor";
import { useRouter } from "next/navigation";
export default function Detail({ params }: { params: { id: number } }) {
  const [article, setArticle] = useState<Article | null>(null);
  const [title, setTitle] = useState("");
  const editorRef = useRef<Editor | null>(null);
  const router = useRouter(); 

  useEffect(() => {
    fetch(`http://localhost:8088/api/v1/articles/${params.id}`)
      .then((res) => res.json())
      .then((data: Article) => {
        setArticle(data);
        setTitle(data.title);
      });
  }, []);

  const doUpdate = () => {
    const editor: Editor = editorRef.current?.getInstance();
    console.log(editor);
    const content: string = editor.getMarkdown();
    const updateArticle: Article = {
      id: article?.id ?? 0,
      title: title,
      content: content,
    };

    fetch(`http://localhost:8088/api/v1/articles/${params.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateArticle),
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("error");
        }
        return res.json();
      })
      .then((data) => {
        setArticle(data);
      });
  };

  const doDelete = () => {
    fetch(`http://localhost:8088/api/v1/articles/${params.id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({}),
    })
      .then((res) => {
        if (!res.ok) {
          throw new Error("error");
        }
        console.log(res);
        router.push("/");
      });
  };

  if (article == null) return <div>loading..</div>;

  return (
    <div className="card w-[800px] h-[800px] bg-base-100 shadow-xl">
      <div className="card-body">
        <h2 className="card-title">
          <input
            type="text"
            value={title}
            onChange={(e) => {
              setTitle(e.target.value);
            }}
          />
        </h2>
        <Editor ref={editorRef} height="650px" initialValue={article.content} />
        <div className="flex justify-end gap-7">
          <button className="btn" onClick={doUpdate}>
            수정
          </button>
          <button className="btn" onClick={doDelete}>
            삭제
          </button>
        </div>
      </div>
    </div>
  );
}
