package com.example.qrscanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BottomDialog extends BottomSheetDialogFragment {

    private String fetchURL;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_dialog,container,false);

        TextView title = view.findViewById(R.id.text_title);
        TextView link = view.findViewById(R.id.text_link);
        TextView btn_visit = view.findViewById(R.id.visit);
        ImageView close = view.findViewById(R.id.cross);

        title.setText(fetchURL);

        btn_visit.setOnClickListener(v -> {
            Intent i = new Intent("android.intent.action.VIEW");
            i.setData(Uri.parse(fetchURL));
            startActivity(i);
        });

        close.setOnClickListener(v -> dismiss());

        return view;
    }

    public  void fetchUrl(String url){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> fetchURL = url);
    }
}
