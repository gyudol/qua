package com.mulmeong.contest.application;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ValidationServiceImpl implements ValidationService {

    private final ImageAnnotatorClient imageAnnotatorClient;

    @Override
    public boolean isFish(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        ByteString imgBytes = ByteString.readFrom(url.openStream());

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();

        BatchAnnotateImagesResponse response = imageAnnotatorClient.batchAnnotateImages(List.of(request));
        List<AnnotateImageResponse> responses = response.getResponsesList();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                System.out.printf("Error: %s\n", res.getError().getMessage());
                return false;
            }

            for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                if (annotation.getDescription().toLowerCase().contains("fish")) {
                    return true;
                }
            }
        }
        return false;
    }
}