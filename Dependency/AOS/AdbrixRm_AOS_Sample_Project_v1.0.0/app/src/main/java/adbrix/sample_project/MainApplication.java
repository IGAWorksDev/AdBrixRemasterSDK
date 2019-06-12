package adbrix.sample_project;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.igaworks.v2.core.application.AbxActivityHelper;
import com.igaworks.v2.core.application.AbxActivityLifecycleCallbacks;

/*
커스텀 Application 클래스를 사용하는 경우, 다음과 같이 애드브릭스 초기화를 진행합니다.
 */
public class MainApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();


        AbxActivityHelper.initializeSdk(MainApplication.this, "r4BAvqXh8kKGADKzICgQHg", "A2GT7AOdDESgy0kBttR3GA");
        /*
        애드브릭스 콘솔 웹페이지에서 발급받은 "앱키"와 "시크릿 키"를 AbxActivityHelper.initializeSdk() 함수에 파라미터로 재설정해줍니다.
         */
        if (Build.VERSION.SDK_INT >= 14) {
            registerActivityLifecycleCallbacks(new AbxActivityLifecycleCallbacks());
        }
        /*
        Adbrix 세션 등록을 위해 액티비티 생명주기에 AbxActivityLifecycleCallbacks클래스를 등록해줍니다.
         */

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
