package com.ebr163.inspector.sample.view_example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.rule.TextLengthRule;
import com.ebr163.inspector.rule.TextNotEmptyRule;
import com.ebr163.inspector.sample.R;
import com.ebr163.inspector.view.InspectionView;
import com.ebr163.inspector.view.TextInputLayoutInspectViewBuilder;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class ViewExampleFragment extends Fragment {

    private Inspector inspector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        inspector = new Inspector();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inspector.setInspectListener((isValid, values) -> Log.d("inspector", "values"));

        getView().findViewById(R.id.checkBtn).setOnClickListener(view1 -> inspector.inspect());

        TextInputLayout til1 = getView().findViewById(R.id.til1);
        TextInputLayout til2 = getView().findViewById(R.id.til2);
        TextInputLayout til3 = getView().findViewById(R.id.til3);
        TextInputLayout til4 = getView().findViewById(R.id.til4);

        InspectionView<TextInputLayout, String> inspectionView1 = new TextInputLayoutInspectViewBuilder(til1)
                .addRule(new TextNotEmptyRule("Поле 1 не должно быть пустым"))
                .build();

        InspectionView<TextInputLayout, String> inspectionView2 = new TextInputLayoutInspectViewBuilder(til2)
                .addRule(new TextNotEmptyRule("Поле 2 не должно быть пустым"))
                .build();

        InspectionView<TextInputLayout, String> inspectionView3 = new TextInputLayoutInspectViewBuilder(til3)
                .addRule(new TextNotEmptyRule("Поле 3 не должно быть пустым"))
                .build();

        InspectionView<TextInputLayout, String> inspectionView4 = new TextInputLayoutInspectViewBuilder(til4)
                .addRules(new TextNotEmptyRule("Поле 4 не должно быть пустым"), new TextLengthRule(5, TextLengthRule.TextLength.EQUAL, "Length is incorrect"))
                .build();

        inspector.addInspection("key1", inspectionView1);
        inspector.addInspection(inspectionView2);
        inspector.addInspection("key3", inspectionView3);
        inspector.addInspection("key4", inspectionView4);
    }

    @Override
    public void onDestroyView() {
        inspector.clear();
        super.onDestroyView();
    }
}
