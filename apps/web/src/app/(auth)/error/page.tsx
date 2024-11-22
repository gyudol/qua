import Link from "next/link";

function page() {
  return (
    <div className=" w-full flex flex-col gap-5 items-center justify-center h-screen bg-gray-100">
      <p className="text-[40px] text-red-500">Error</p>
      <Link href="./sign-in">
        <button
          type="button"
          className="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition duration-200"
        >
          로그인 화면으로 이동
        </button>
      </Link>
    </div>
  );
}

export default page;
