"use client";

import React, { useState, useRef } from "react";
import Image from "next/image";
import { UploadCloud, XCircleIcon } from "lucide-react";
import type { CreateContestType } from "@/types/request/requestType";
import {
  deleteFileFromS3,
  uploadFileToS3,
} from "@/actions/common/awsMediaUploader";
import { UuidConverter } from "@/functions/utils/uuidConverter";

export interface PreviewImageType {
  s3Url: string;
  fileType: string;
}

function ImageUploader({
  setPayload,
}: {
  setPayload: React.Dispatch<React.SetStateAction<CreateContestType>>;
}) {
  const mediaRef = useRef<HTMLInputElement>(null);
  const [previewMedia, setPreviewMedia] = useState<PreviewImageType | null>(
    null,
  );

  const handleFeedImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    const { files } = e.target;
    if (!files || files.length === 0) return;

    const file = files[0];

    const fileExtention = file.name.split(".").pop();
    const uniqueFileName = `${UuidConverter()}.${fileExtention}`;

    const s3Url = await uploadFileToS3(file, uniqueFileName);

    if (!s3Url) return;

    setPreviewMedia({ s3Url, fileType: "IMAGE" });

    setPayload((prev) => ({
      ...prev,
      mediaUrl: s3Url,
      mediaType: "IMAGE",
    }));
  };

  const handleDeleteImage = async (s3Url: string) => {
    try {
      if (s3Url) {
        const res = await deleteFileFromS3(s3Url);
        if (!res) throw new Error("Failed to delete from S3");
      }

      // 삭제 후 초기화
      setPreviewMedia(null);
      setPayload((prev) => ({
        ...prev,
        mediaUrl: "",
        mediaType: "",
      }));

      // input file 초기화
      if (mediaRef.current) {
        mediaRef.current.value = "";
      }
    } catch (error) {
      // console.error("Error deleting image:", error);
    }
  };

  return (
    <div className="flex flex-col items-center">
      {/* 업로드 라벨 */}
      <label
        htmlFor="feedImg"
        className="items-center gap-2 w-full flex justify-center border-dotted border-2 border-gray-300 p-4 rounded-lg bg-[#F1F4F9] h-[110px] cursor-pointer"
      >
        <UploadCloud />
        <span className="text-gray-600 text-sm">Upload attachment</span>
      </label>

      {/* 파일 입력 */}
      <input
        ref={mediaRef}
        type="file"
        id="feedImg"
        name="feedImg"
        className="hidden"
        accept="image/*"
        onChange={(e) => {
          void handleFeedImage(e);
        }}
      />
      <div className="mt-4 flex justify-center">
        {previewMedia ? (
          <div className="relative w-[120px] h-[150px] bg-gray-200 rounded-lg flex items-center justify-center text-gray-400 border-dashed border-2 overflow-hidden">
            {previewMedia.fileType === "IMAGE" ? (
              <Image
                src={previewMedia.s3Url}
                alt={`Preview ${previewMedia.fileType}`}
                className="w-full h-full object-cover"
                width={150}
                height={120}
              />
            ) : null}
            <button
              type="button"
              onClick={() => void handleDeleteImage(previewMedia.s3Url)}
              className="absolute inset-0 flex items-center justify-center bg-black bg-opacity-50 text-white opacity-0 hover:opacity-100 transition-opacity"
            >
              <XCircleIcon />
            </button>
          </div>
        ) : null}
      </div>
    </div>
  );
}

export default ImageUploader;
