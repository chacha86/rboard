export default function Login() {

    return (
        <div className="flex flex-col gap-10">
            <div>
                <h1 className="font-bold text-[1.5rem] text-center">Login</h1>
            </div>
            <div className="flex flex-col gap-3">
                <input type="text" name="loginId" className="input input-bordered" placeholder="아이디" />
                <input type="password" name="loginPw" className="input input-bordered" placeholder="비밀번호"/>
                <button className="btn btn-primary">로그인</button>
            </div>
        </div>

    );
}