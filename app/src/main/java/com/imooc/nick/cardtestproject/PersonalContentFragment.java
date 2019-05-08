package com.imooc.nick.cardtestproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonalContentFragment extends Fragment {

    private View rootView;
    private TextView content;


    public static Fragment newInstance(int id, int type, String name, int position, int currentSelectedPage, String url) {
        PersonalContentFragment classifyFragment = new PersonalContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("classifyId", id);
        bundle.putInt("classifyType", type);
        bundle.putString("classifyName", name);
        bundle.putInt("position", position);
        bundle.putInt("currentSelectedPage", currentSelectedPage);
        bundle.putString("url", url);
        classifyFragment.setArguments(bundle);

        return classifyFragment;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        content = rootView.findViewById(R.id.content);
        String name = getArguments().getString("classifyName");
        content.setText(name);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_personal_content_fragment, null);
        return rootView;
    }
}
