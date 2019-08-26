package com.dian.project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dian.project.R;
import com.dian.project.data.utils.SharePreUtil;
import com.dian.project.ui.activity.LoginActivity;

public class Fragment_Mine extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String sessionId = SharePreUtil.getStringData(getActivity(), "sessionId", "");
        String userId = SharePreUtil.getStringData(getActivity(), "userId", "");
        mView = View.inflate(getActivity(), R.layout.fragment_mine, null);

        return mView;
    }
}
