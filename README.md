# Inspector

Inspector is a simple and powerful rule-based UI form validation library for Android.

Quick Start
-----------
**Step 1 - [init Inspector].**
```java
public class MainActivity extends Activity {

    private Inspector inspector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inspector = new Inspector();
    }

    @Override
    protected void onDestroy() {
        inspector.clear();
        super.onDestroy();
    }
}
```
If you use AppCompat library:
```java
public class MainActivity extends AppCompatActivity {

    private Inspector inspector;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inspector = new Inspector(getLifecycle());
    }
}
```

**Step 2 - Instantiate a new Inspection**
```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Code…
    Inspection<String> inspectionView = new TextInputLayoutInspectViewBuilder(til1)
                .addRule(new TextNotEmptyRule("Поле 1 не должно быть пустым"))
                .build();
                
    Inspection<String> inspectionValue = new InspectValueBuilder<>("ss")
                .addRule(new TextNotEmptyRule("ins3"))
                .build();
    // More code…
}
```

**Step 3 - add Inspection in Inspector**
```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Code…
    inspector.addInspection(inspectionView);
    inspector.addInspection(inspectionValue);
    //or
    inspector.addInspection(KEY_1, inspectionView);
    inspector.addInspection(KEY_2, inspectionValue);
}
```

**Step 4 - Validate**
```java
button.setOnClickListener(new OnClickListener() {

    @Override
    public void onClick(View v) {
       boolean isValid = inspector.inspect();
       //more code...
    }
});
```
You can use the [OnInspectListener] to get the result of the check and the variables:
```java
inspector.setInspectListener(new Inspector.OnInspectListener() {
            @Override
            public void onInspect(boolean isValid, Map<String, Object> values) {
                if (isValid){
                    presenter.setKey1(values.get(KEY_1));
                    presenter.setKey2(values.get(KEY_2));
                }
            }
        });
```

[init Inspector]: https://github.com/pavel163/Inspector/blob/master/app/src/main/java/com/ebr163/inspector/sample/value_example/ValueExampleActivity.java
[OnInspectListener]: https://github.com/pavel163/Inspector/blob/master/app/src/main/java/com/ebr163/inspector/sample/view_example/ViewExampleFragment.java
