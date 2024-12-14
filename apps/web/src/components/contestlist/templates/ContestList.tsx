"use client";
import React from "react";
import ContestFilter from "../atoms/ContestFilter";

function ContestList() {
  const dummyContests = [
    { id: 1, type: "video", src: "https://www.w3schools.com/html/mov_bbb.mp4" },
    {
      id: 2,
      type: "file",
      src: "https://via.placeholder.com/400x300?text=Image+1",
    },
    { id: 3, type: "video", src: "https://www.w3schools.com/html/movie.mp4" },
    {
      id: 4,
      type: "file",
      src: "https://via.placeholder.com/400x300?text=Image+2",
    },
  ];

  return (
    <div className="min-h-screen bg-gray-100 pt-5 px-4 border-2">
      <ContestFilter />
      <div className="grid grid-cols-2 gap-4 w-full">
        {dummyContests.map((contest) => (
          <div key={contest.id} className="flex flex-col">
            {/* <div className="h-[200px] overflow-hidden round-t-lg">
              {contest.type === "video" ? (
                <video
                  autoPlay
                  muted
                  loop
                  src={contest.src}
                  className="w-full h-full object-cover rounded-lg"
                >
                  <track
                    kind="captions"
                    src="path_to_captions.vtt"
                    srcLang="en"
                    label="English"
                    default
                  />
                </video>
              ) : (
                <img
                  src={contest.src}
                  alt="Contest File"
                  className="w-full h-full object-cover rounded-lg"
                />
              )}
            </div> */}
            <button
              type="button"
              className="py-2 border-2 border-solid rounded-b-lg round-t-lg bg-[#47D0BF] text-white"
            >
              투표하기
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ContestList;
