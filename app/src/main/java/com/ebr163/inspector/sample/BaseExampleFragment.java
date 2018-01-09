package com.ebr163.inspector.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebr163.inspector.Inspector;
import com.ebr163.inspector.builder.TextInputLayoutInspectBuilder;
import com.ebr163.inspector.inspection.Inspection;
import com.ebr163.inspector.rule.NotNullRule;
import com.ebr163.inspector.rule.TextNotEmptyRule;

/**
 * Created by Bakht
 * on 09.01.2018.
 */

public class BaseExampleFragment extends Fragment {

    private Inspector inspector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inspector = new Inspector();

        getView().findViewById(R.id.checkBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inspector.inspect();
            }
        });

        TextInputLayout til1 = getView().findViewById(R.id.til1);
        TextInputLayout til2 = getView().findViewById(R.id.til2);
        TextInputLayout til3 = getView().findViewById(R.id.til3);
        TextInputLayout til4 = getView().findViewById(R.id.til4);

        Inspection<TextInputLayout, CharSequence> inspection1 = new TextInputLayoutInspectBuilder(til1)
                .addRule(new NotNullRule<CharSequence>("Поле 1 не должно быть null"))
                .addRule(new TextNotEmptyRule("Поле 1 не должно быть пустым"))
                .build();

        Inspection<TextInputLayout, CharSequence> inspection2 = new TextInputLayoutInspectBuilder(til2)
                .addRule(new NotNullRule<CharSequence>("Поле 2 не должно быть null"))
                .addRule(new TextNotEmptyRule("Поле 2 не должно быть пустым"))
                .build();

        Inspection<TextInputLayout, CharSequence> inspection3 = new TextInputLayoutInspectBuilder(til3)
                .addRule(new NotNullRule<CharSequence>("Поле 3 не должно быть null"))
                .addRule(new TextNotEmptyRule("Поле 3 не должно быть пустым"))
                .build();

        Inspection<TextInputLayout, CharSequence> inspection4 = new TextInputLayoutInspectBuilder(til4)
                .addRule(new NotNullRule<CharSequence>("Поле 4 не должно быть null"))
                .addRule(new TextNotEmptyRule("Поле 4 не должно быть пустым"))
                .build();

        inspector.addInspection(inspection1);
        inspector.addInspection(inspection2);
        inspector.addInspection(inspection3);
        inspector.addInspection(inspection4);
    }

    @Override
    public void onDestroyView() {
        inspector.clear();
        super.onDestroyView();
    }
}
