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

**Step 3 - add Inspection in Inspector**

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Code…
    inspector.addInspection(inspectionView);
    inspector.addInspection(inspectionValue);
    //or
    inspector.addInspection(KEY1, inspectionView);
    inspector.addInspection(KEY2, inspectionValue);
}

[init Inspector]: https://github.com/pavel163/Inspector/blob/master/app/src/main/java/com/ebr163/inspector/sample/value_example/ValueExampleActivity.java
