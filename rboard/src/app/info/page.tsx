"use client"
import {useEffect, useState} from "react";

export default function Info() {
    const [userInfo, setUserInfo] = useState({} as any);
    const [imagePath, setImagePath] = useState('http://localhost:8088/profile/noImg.jpg');
    const openFileInput = (e: any) => {
        const el = document.getElementById("fileInput");

        if (el != null) {
            el.click();
        }
    }

    useEffect(() => {
        fetch("http://localhost:8088/api/v1/members/1")
            .then((res) => res.json())
            .then((data) => {
                console.log(data);
                setUserInfo(data);

                if (data.image !== null)
                    setImagePath(data.image.path);

            })
            .catch((err) => {
                console.error(err);
            })
    }, [imagePath]);

    const changeHandle = (e: any) => {
        const targetFile = e.target.files[0];

        if (targetFile === null) {
            alert("파일을 다시 선택해주세요");
            return;
        }

        const formData = new FormData();
        formData.append("image", targetFile);

        fetch("http://localhost:8088/api/v1/members/1", {
            method: "post",
            body: formData
        })
            .then((res) => res.json())
            .then((data) => {
                // console.log(data);
                // setUserInfo(data);

                console.log(imagePath);
                if (data.image !== null) {
                    console.log("setImage");
                    setImagePath(data.image.path);
                    console.log(imagePath);
                }
            })
            .catch((err) => {
                console.error(err);
            });
    }

    return (
        <div>
            <input type="file" id="fileInput" className="hidden" onChange={changeHandle}/>
            <h1>info</h1>
            <div className={"flex mt-[100px]"}>
                <div className={"profile-img bg-cyan-600 w-[30%] h-[200px] flex flex-col"}>
                    <img className="w-[100%] h-[90%] rounded-[50%] object-fill" src={imagePath} alt=""/>
                    <button className={"bg-green-500 p-[5px]"} onClick={openFileInput}>사진 변경</button>
                </div>
                <div className={"bg-amber-800 w-[70%]"}>profile</div>
            </div>
        </div>
    );
}