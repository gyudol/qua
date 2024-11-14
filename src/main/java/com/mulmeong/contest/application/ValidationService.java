package com.mulmeong.contest.application;

import java.io.IOException;

public interface ValidationService {
    boolean isFish(String imageUrl) throws IOException;
}
