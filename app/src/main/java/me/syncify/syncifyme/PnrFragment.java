package me.syncify.syncifyme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.TreeMap;

import me.syncify.syncifyme.network.GsonRequest;
import me.syncify.syncifyme.network.NetworkUtils;
import me.syncify.syncifyme.network.response.PnrStatusResponse;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class PnrFragment extends Fragment {

    private EditText pnrNumber;
    private TextView trainName;
    private TextView trainNumber;
    private Button submit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pnr_fragment, null);

        pnrNumber = (EditText) view.findViewById(R.id.pnr_number);
        trainName = (TextView) view.findViewById(R.id.train_name);
        trainNumber = (TextView) view.findViewById(R.id.train_number);
        submit = (Button) view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TreeMap<String, String> map = new TreeMap<>();
                map.put("pnr", pnrNumber.getText().toString());
                map.put("format", "json");
                map.put("pbapikey", "ec470031b76e3e421606bb1591130110");

                String URL = "http://railpnrapi.com/test/check_pnr/pnr/" + map.get("pnr") + "/format/json/pbapikey/"+ map.get("pbapikey") + "/pbapisign/" + NetworkUtils.getHashmac(map);

                GsonRequest<PnrStatusResponse> request = new GsonRequest<>(Request.Method.GET, URL, PnrStatusResponse.class, null, new Response.Listener<PnrStatusResponse>() {
                    @Override
                    public void onResponse(PnrStatusResponse pnrStatusResponse) {
                        trainName.setText(pnrStatusResponse.trainName);
                        trainNumber.setText(pnrStatusResponse.trainNumber);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), "kuch ho gya", Toast.LENGTH_SHORT).show();
                    }
                }, null);

                MainApplication.getInstance().getRequestQueue().add(request);
            }
        });




        return view;
    }
}
