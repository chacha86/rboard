"use client"
export default function Info() {

    const openFileInput = (e:any) => {
        const el = document.getElementById("fileInput");

        if(el != null) {
            el.click();
        }
    }

    return (
        <div>
            <input type="file" id="fileInput" className="hidden"/>
            <h1>info</h1>
            <div className={"flex mt-[100px]"}>
                <div className={"profile-img bg-cyan-600 w-[30%] h-[200px] flex flex-col"}>
                    <img className="w-[100%] h-[90%] rounded-[50%] object-fill" src="data:image/jpeg;base64,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" alt=""/>
                    <button className={"bg-green-500 p-[5px]"} onClick={openFileInput}>사진 변경</button>
                </div>
                <div className={"bg-amber-800 w-[70%]"}>profile</div>
            </div>
        </div>
    );
}