package com.ftn.android.reimagined_tribble.io.swagger.client.auth;

import com.ftn.android.reimagined_tribble.io.swagger.client.Pair;

import java.util.Map;
import java.util.List;

public interface Authentication {
    /**
     * Apply authentication settings to header and query params.
     */
    void applyToParams(List<Pair> queryParams, Map<String, String> headerParams);
}