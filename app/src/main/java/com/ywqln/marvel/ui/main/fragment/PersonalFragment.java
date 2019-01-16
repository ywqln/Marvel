package com.ywqln.marvel.ui.main.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywqln.marvel.R;

/**
 * 描述:个人Fragment.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/16
 */
public class PersonalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, null);
    }
}
