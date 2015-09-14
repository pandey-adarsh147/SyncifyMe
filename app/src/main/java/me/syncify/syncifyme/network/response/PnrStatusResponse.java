package me.syncify.syncifyme.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class PnrStatusResponse {

    @SerializedName("response_code")
    public int responseCode;

    @SerializedName("train_num")
    public String trainNumber;

    @SerializedName("train_name")
    public String trainName;

}
