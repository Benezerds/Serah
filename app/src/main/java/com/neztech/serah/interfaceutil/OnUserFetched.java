package com.neztech.serah.interfaceutil;

import com.neztech.serah.model.User;

public interface OnUserFetched {
    void onFetched(User user);
    void onError(Exception e);
}
