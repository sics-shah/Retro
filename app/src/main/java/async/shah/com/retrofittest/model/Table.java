package async.shah.com.retrofittest.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shah on 4/27/2017.
 */

public class Table {
    @SerializedName("SNo")
    @Expose
    private String sNo;
    @SerializedName("TableName")
    @Expose
    private String tableName;

    public String getSNo() {
        return sNo;
    }

    public void setSNo(String sNo) {
        this.sNo = sNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}

