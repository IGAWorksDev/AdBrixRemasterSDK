package adbrix.sample_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.igaworks.v2.core.AdBrixRm;

/*
-----------------------------------------------------------------------------

이 딥링크 액티비티는 AndroidManifest.xml에 내에 설정한 host://scheme 값을 통해 오픈됩니다.

<intent-filter android:label="@string/app_name">
    <data
        android:host="deeplink"
        android:scheme="david" />
</intent-filter>

-----------------------------------------------------------------------------
 */
public class DeeplinkActivity extends AppCompatActivity implements View.OnClickListener{

    private Button goToMain,goToCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);
        goToMain = (Button) findViewById(R.id.goToMain);
        goToCustom = (Button) findViewById(R.id.goToCustom);

        goToMain.setOnClickListener(this);
        goToCustom.setOnClickListener(this);

        onNewIntent(DeeplinkActivity.this.getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        /*
        딥링크를 통해 오픈 되었는 지 트랙킹하기 위해 다음 API를 반드시 호출해줍니다.
         */
        AdBrixRm.deeplinkEvent(DeeplinkActivity.this);

        /*
        intent.getScheme();
        상황에 따라 딥링크 액티비티 스킴값을 통해 특정 액티비티로 랜딩시키도록 구현하시면 됩니다.
         */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goToMain:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.goToCustom:
                /*
                커스텀 액티비티
                 */
                break;
        }
    }
}
