package com.ltsh.util.beetsql.entity.result;

/**
 * Created by fengjianbo on 2018/2/11.
 */

public class ByteBaseResult extends LtshResult {
    /**
     * 请求流水
     */
    private String keep;
    private byte[] context;

    public ByteBaseResult(String code, String message) {
        super(code, message);
    }
    public ByteBaseResult(String code, String message, byte[] context) {
        super(code, message);
        this.context = context;
    }
    public boolean isSucceed() {
        if("000000".equals(this.getCode())) {
            return true;
        }
        return false;
    }

    public byte[] getContext() {
        return context;
    }

    public void setContext(byte[] context) {
        this.context = context;
    }

    public String getKeep() {
        return keep;
    }

    public void setKeep(String keep) {
        this.keep = keep;
    }
}
