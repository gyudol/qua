async function uploadFileToS3(
  file: File,
  uniqueFileName: string
): Promise<string> {
  const formData = new FormData();
  if (file.type.startsWith('video')) {
    formData.append('fileName', `video/original/${uniqueFileName}`);
  } else if (file.type.startsWith('image')) {
    formData.append('fileName', `image/${uniqueFileName}`);
  }
  formData.append('fileType', file.type);

  // FileReader를 사용하여 파일을 Base64로 변환
  const base64File = await new Promise<string>((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result as string);
    reader.onerror = () => reject(new Error('Failed to read file'));
  });

  // Base64 데이터에서 실제 파일 데이터만 추출 (data URL의 앞부분 제거)
  const base64Data = base64File.split(',')[1];
  formData.append('fileContent', base64Data);

  // App Router 방식의 API 호출 경로
  const res = await fetch('/api/s3/client/feed', {
    method: 'POST',
    body: JSON.stringify({
      fileName: formData.get('fileName'),
      fileType: file.type,
      fileContent: base64Data,
    }),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!res.ok) {
    throw new Error('Failed to upload file');
  }

  const data = (await res.json()) as { imageUrl: string };
  return data.imageUrl; // S3에 업로드된 파일의 URL 반환
}

async function deleteFileFromS3(fileUrl: string) {
  const res = await fetch('/api/s3/client/feed', {
    method: 'DELETE',
    body: JSON.stringify({
      fileUrl,
    }),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!res.ok) {
    throw new Error('Failed to delete file');
  }
  return true;
}

export { uploadFileToS3, deleteFileFromS3 };
