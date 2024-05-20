export default function Home() {

    return (
        <>
            <div className="bg-blue-300">
                <a href="/">logo</a>
            </div>
            <div className="flex">
                <div className="bg-indigo-300 w-[20%]">
                    <ul className="menu h-[100%] overflow-scroll">
                        <li>aaa</li>
                        <li>aaa</li>
                        <li>aaa</li>
                    </ul>

                    <form>
                        <input type="submit" value="추가" className="postActionBtn btn"/>
                    </form>

                    <form method="post">
                        <input type="submit" value="하위 노트북 추가" className="postActionBtn btn"/>
                    </form>
                    <form method="post">
                        <input type="submit" value="삭제" className="postActionBtn btn"/>
                    </form>
                    <a href="/signup" className="btn">회원 가입</a>
                    <a href="/logout" className="btn">로그아웃</a>

                    <button className="btn">노트 이동</button>
                    <button className="btn">검색</button>
                    <button className="btn">노트 이름 변경</button>
                    <button className="btn">태그 목록</button>
                </div>
                <div className="bg-red-300 w-[20%] h-[800px] text-center ">
                    <ul className="h-[100%] overflow-scroll">
                        <li>
                            <a className="getActionBtn">aaa</a>
                        </li>
                    </ul>

                    <form>
                        <input type="submit" value="추가" className="postActionBtn"/>
                    </form>

                    <a className="btn sortDate">날짜순</a>
                    <a className="btn sortTitle">이름순</a>
                </div>
                <div className="w-[60%]">
                    <form method="post" id="updateForm">
                        <div>
                            <input type="hidden" name="id"/>
                        </div>
                        <div>
                            <input type="text" name="title"/>
                        </div>
                        <input type="hidden" name="content" id="editor-body"/>
                        <div id="editor"></div>
                        <div>
                            <input type="button" value="수정" className="postActionBtn"/>
                        </div>
                    </form>
                    <form id="deleteForm">
                        <input type="button" value="삭제" className="postActionBtn"/>
                    </form>
                    <ul className="flex gap-5">
                        <li>
                            <form>
                                <input type="submit" className="btn"/>
                            </form>
                        </li>
                    </ul>
                    <form>
                        <input type="text" name="name" className="input input-bordered" placeholder="태그 추가"/>
                        <input type="submit" className="postActionBtn btn" value="추가"/>
                    </form>
                </div>
            </div>
        </>
    );
}
