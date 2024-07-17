package com.itcourse.mdcc.domain;

import java.util.List;

public class MediaFileProcess_m3u8 {

    private List<String> tslist;

    private String errorMsg;


    public List<String> getTslist() {
        return tslist;
    }

    public void setTslist(List<String> tslist) {
        this.tslist = tslist;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
