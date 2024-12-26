"use client";

import React, { useState, useRef } from "react";
import Image from "next/image";
import { UploadCloud, XCircleIcon } from "lucide-react";
import type { CreateFeedType } from "@/types/request/requestType";
import {
  deleteFileFromS3,
  uploadFileToS3,
} from "@/actions/common/awsMediaUploader";
import { MediaAssetConverter } from "@/functions/utils/mediaListConverter";
import { UuidConverter } from "@/functions/utils/uuidConverter";

export interface PreviewImageType {
  s3Url: string;
  fileType: string;
  uniqueUuid: string;
}

function ImageUploader({
  setPayload,
}: {
  setPayload: React.Dispatch<React.SetStateAction<CreateFeedType>>;
}) {
  const mediaRef = useRef<HTMLInputElement>(null);
  const [mediaList, setMediaList] = useState<PreviewImageType[]>(
    [] as PreviewImageType[],
  );

  const handleFeedImage = async (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    const { files } = e.target;
    if (!files || files.length === 0) return;

    const file = files[0];
    const fileType = file.type.startsWith("image") ? "IMAGE" : "VIDEO";
    const fileExtention = file.name.split(".").pop();
    const uniqueUuid = UuidConverter();
    const uniqueFileName = `${uniqueUuid}.${fileExtention}`;

    const s3Url = await uploadFileToS3(file, uniqueFileName);

    if (!s3Url) return;
    setMediaList((prev) => {
      const updated = [...prev];
      updated.push({ s3Url, fileType, uniqueUuid });
      return updated;
    });

    const convertedMedia = await MediaAssetConverter(s3Url, fileType);

    setPayload((prev) => {
      const updatedPayload = { ...prev };
      updatedPayload.mediaList = [...prev.mediaList, convertedMedia];
      return updatedPayload;
    });
  };

  const handleDeleteImage = async (
    uniqueUuid: string,
    s3Url: string,
    fileType: string,
  ) => {
    try {
      if (s3Url && fileType) {
        const res = await deleteFileFromS3(s3Url);
        if (!res) throw new Error("Failed to delete from S3");
      }

      setMediaList((prev) => {
        const updated = prev.filter((media) => media.s3Url !== s3Url);
        return updated;
      });

      setPayload((prev) => {
        const updatedPayload = { ...prev };
        updatedPayload.mediaList = prev.mediaList.filter(
          (media) => media.mediaUuid !== uniqueUuid,
        );
        return updatedPayload;
      });
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
        className="items-center gap-2 w-full flex justify-center border-dotted border-[1px] border-primary p-4 rounded-lg bg-[#ebfbfa] h-[110px] cursor-pointer"
      >
        <UploadCloud color="#48d0bf" />
        <span className="text-primary text-sm">Upload attachment</span>
      </label>

      {/* 파일 입력 */}
      <input
        ref={mediaRef}
        type="file"
        id="feedImg"
        name="feedImg"
        className="hidden"
        accept="image/*,video/*"
        multiple
        onChange={(e) => {
          void handleFeedImage(e);
        }}
      />
      <ul className="grid grid-cols-3 gap-2 mt-4 w-full justify-left">
        {mediaList.map((previewMedia: PreviewImageType) => (
          <li
            key={previewMedia.s3Url}
            className="relative col-span-1 w-full h-[150px] bg-gray-200 rounded-lg flex items-start justify-left text-gray-400 border-[1px] border-primary overflow-hidden"
          >
            {previewMedia.fileType === "IMAGE" ? (
              <Image
                src={previewMedia.s3Url}
                alt={`Preview ${previewMedia.fileType}`}
                className="w-full h-full object-cover"
                width={150}
                height={120}
              />
            ) : (
              <video width="100%" preload="metadata" autoPlay muted>
                <source src={previewMedia.s3Url} />
                <track kind="captions" default />
              </video>
            )}
            <button
              type="button"
              onClick={() =>
                void handleDeleteImage(
                  previewMedia.uniqueUuid,
                  previewMedia.s3Url,
                  previewMedia.fileType,
                )
              } // fileType 전달
              className="absolute inset-0 flex items-center justify-center bg-black bg-opacity-50 text-white opacity-0 hover:opacity-100 transition-opacity"
            >
              <XCircleIcon />
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ImageUploader;
