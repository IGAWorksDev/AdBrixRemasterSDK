package adbrix.sample_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.igaworks.v2.core.AdBrixRm;


import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button pDetail, cart;
    private Button list_view;
    private Button level_achieved, tutorial_completed, character_created;
    private Button purchase,use_credit,sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = (Button) findViewById(R.id.viewHome);
        tutorial_completed = (Button) findViewById(R.id.tutorial_completed);
        character_created = (Button) findViewById(R.id.character_created);
        level_achieved = (Button) findViewById(R.id.level_achieved);
        pDetail = (Button) findViewById(R.id.pDetail);
        cart = (Button) findViewById(R.id.cart);
        purchase = (Button) findViewById(R.id.purchase);
        sign_up = (Button) findViewById(R.id.sign_up);
        use_credit = (Button) findViewById(R.id.use_credit);

        list_view.setOnClickListener(this);
        tutorial_completed.setOnClickListener(this);
        character_created.setOnClickListener(this);
        level_achieved.setOnClickListener(this);
        pDetail.setOnClickListener(this);
        cart.setOnClickListener(this);
        sign_up.setOnClickListener(this);
        use_credit.setOnClickListener(this);
        purchase.setOnClickListener(this);


        /*
        애드브릭스 SDK 이벤트를 상황에 맞게 호출해줍니다.
         */


    }


    /*

    애드브릭스 이벤트 예시

    아래 이벤트는 애드브릭스에서 제공하는 이벤트 중 일부를 포함하고 있습니다.
    자세한 사항은 아래 링크를 확인해주세요.
    link : https://help.adbrix.io/hc/ko/articles/360003279994-애드브릭스-Android-연동하기-Java-

     */

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.purchase:
                /*
                ---------------------------------------------------------------------

                Purchase Event
                구매 이벤트

                유저가 상품 구매를 했을 때, 분석을 원한다면 다음 이벤트를 호출해줍니다.

                예시 : 색상이 각각 다른 3개의 크록스를 일괄 구매, 신용카드 결제, 배송료 3500원

                ---------------------------------------------------------------------
                */

                /* 카테고리 모델 생성 */
                final AdBrixRm.CommerceCategoriesModel purchaseCategory = new AdBrixRm.CommerceCategoriesModel().setCategory("기획전");

                /* 상품 정보 모델 생성 */
                ArrayList<AdBrixRm.CommerceProductModel> product = new ArrayList<AdBrixRm.CommerceProductModel>() {
                    {
                        add(new AdBrixRm.CommerceProductModel()
                                .setProductID("30290121")
                                .setProductName("여름한정 떨이상품 크록스 20%할인")
                                .setPrice(50000)
                                .setQuantity(1)
                                .setDiscount(10000)
                                .setCurrency(AdBrixRm.Currency.KR_KRW)
                                .setCategory(purchaseCategory)
                                .setAttrModel(new AdBrixRm.AttrModel().setAttrs("color", "메탈블랙")));
                        add(new AdBrixRm.CommerceProductModel()
                                .setProductID("30290121")
                                .setProductName("여름한정 떨이상품 크록스 20%할인")
                                .setPrice(50000)
                                .setQuantity(1)
                                .setDiscount(10000)
                                .setCurrency(AdBrixRm.Currency.KR_KRW)
                                .setCategory(purchaseCategory)
                                .setAttrModel(new AdBrixRm.AttrModel().setAttrs("color", "크림화이트")));

                        add(new AdBrixRm.CommerceProductModel()
                                .setProductID("30290121")
                                .setProductName("여름한정 떨이상품 크록스 20%할인")
                                .setPrice(50000)
                                .setQuantity(1)
                                .setDiscount(10000)
                                .setCurrency(AdBrixRm.Currency.KR_KRW)
                                .setCategory(purchaseCategory)
                                .setAttrModel(new AdBrixRm.AttrModel().setAttrs("color", "크리스탈 블루")));
                    }};

                /* 상품 결제 */
                AdBrixRm.Common.purchase("290192012", product, 0.00, 3500.00, AdBrixRm.CommercePaymentMethod.CreditCard);
                break;

            case R.id.sign_up:
                /*
                ---------------------------------------------------------------------

                Sign up Event
                회원가입 이벤트

                유저의 회원가입 분석을 원하신다면 다음 이벤트를 호출해줍니다.

                예시 : 위치정보 이용을 동의하는 서울에 거주하는 회원가입자

                ---------------------------------------------------------------------
                */

                AdBrixRm.AttrModel attrModel = new AdBrixRm.AttrModel()
                        .setAttrs("location","SEOUL")
                        .setAttrs("location_access",true)
                        .setAttrs("key1", "value1");

                AdBrixRm.CommonProperties.SignUp signUp = new AdBrixRm.CommonProperties.SignUp()
                        .setAttrModel(attrModel);
                AdBrixRm.Common.signUp(AdBrixRm.CommonSignUpChannel.Kakao, signUp);

                break;

            case R.id.use_credit:
                /*
                ---------------------------------------------------------------------

                Use Credit Event
                크레딧 사용 이벤트

                앱내 현금성 화폐 사용에 대한 이벤트를 분석합니다.

                예시 : 가상화폐 'credit' 1000을 사용했을 때

                ---------------------------------------------------------------------
                */

                AdBrixRm.CommonProperties.UseCredit useCredit = new AdBrixRm.CommonProperties.UseCredit()
                        .setAttrModel(new AdBrixRm.AttrModel().setAttrs("credit", "1000"));
                AdBrixRm.Common.useCredit(useCredit);

                break;

            case R.id.character_created:
                /*
                ---------------------------------------------------------------------

                character_created Event
                캐릭터 생성 이벤트

                유저가 게임 캐릭터를 생성했을 때, 분석을 원한다면 호출해줍니다.
                예시 : adbrix짱짱123 이름을 갖는 마법사 캐릭터를 생성했을 때
                ---------------------------------------------------------------------

                */
                AdBrixRm.GameProperties.CharacterCreated gameProperties_characterCreated = new AdBrixRm.GameProperties.CharacterCreated()
                        .setAttrModel(new AdBrixRm.AttrModel()
                                .setAttrs("캐릭터 이름", "adbrix짱짱123")
                                .setAttrs("캐릭터 직업", "마법사"));

                AdBrixRm.Game.characterCreated(gameProperties_characterCreated);
                break;

            case R.id.tutorial_completed:

                /*
                ---------------------------------------------------------------------

                tutorial_completed Event
                튜토리얼 완료 이벤트

                유저가 튜토리얼을 완료했을 때 분석을 원한다면 다음 이벤트를 호출해줍니다.
                예시 : 유저가 튜토리얼을 스킵하지않고 튜토리얼1까지 클리어했을 때
                ---------------------------------------------------------------------
                */

                AdBrixRm.GameProperties.TutorialComplete gameProperties = new AdBrixRm.GameProperties.TutorialComplete()
                        .setIsSkip(false)
                        .setAttrModel(new AdBrixRm.AttrModel()
                                .setAttrs("tutorial_1", true));
                AdBrixRm.Game.tutorialComplete(gameProperties);
                break;
            case R.id.level_achieved:

                /*
                ---------------------------------------------------------------------

                level_acheived Event
                레벨 달성 이벤트

                유저가 특성 레벨을 달성한 시점을 분석하고 싶다면 다음 이벤트를 호출합니다.
                예시 : 유저가 처음으로 레벨 10을 달성했을 때
                ---------------------------------------------------------------------
                */


                AdBrixRm.GameProperties.LevelAchieved gameProperties_levelAchieved = new AdBrixRm.GameProperties.LevelAchieved()
                        .setLevel(10)
                        .setAttrModel(new AdBrixRm.AttrModel().
                                setAttrs("first_achievement", true));
                AdBrixRm.Game.levelAchieved(gameProperties_levelAchieved);
                break;


            case R.id.viewHome:

                /*
                ---------------------------------------------------------------------

                view_home Event
                메인화면 진입 이벤트

                유저가 메인화면 진입했을 때 분석을 원한다면 다음 이벤트를 호출합니다.
                예시 : 이벤트8751 이란 페이지에서 메인화면으로 진입하였을 때 ( 로딩시간 1초 )
                ---------------------------------------------------------------------
                */

                AdBrixRm.AttrModel attrModel2 = new AdBrixRm.AttrModel();
                attrModel2.setAttrs("previous_page", "event8751")
                        .setAttrs("LoadingTimeMs", 1000);
                AdBrixRm.Commerce.viewHome(attrModel2);

                break;

            case R.id.pDetail:
                /*
                ---------------------------------------------------------------------

                product_view Event
                상품 상세보기

                유저가 특정 상품 상세보기를 클릭했을 시, 분석을 원한다면 다음 이벤트를 호출해줍니다.
                예시 : 여름한정 떨이상품 크록스 회색 20%할인
                ---------------------------------------------------------------------
                */

                AdBrixRm.AttrModel attrModel1 = new AdBrixRm.AttrModel()
                        .setAttrs("Test11", "productModelVal1")
                        .setAttrs("test-123", "productModelVal2");

                AdBrixRm.CommerceCategoriesModel productCat = new AdBrixRm.CommerceCategoriesModel().setCategory("기획전");
                AdBrixRm.CommerceProductModel productModel1 = new AdBrixRm.CommerceProductModel()
                        .setProductID("30290121")
                        .setProductName("여름한정 떨이상품 크록스 회색 20%할인")
                        .setPrice(50000)
                        .setDiscount(10000)
                        .setCurrency(AdBrixRm.Currency.KR_KRW)
                        .setCategory(productCat)
                        .setAttrModel(attrModel1);


                /*
                추가적으로 전송할 수 있는 키:밸류 예시
                 */
                AdBrixRm.AttrModel attrModelpDetail = new AdBrixRm.AttrModel();
                attrModelpDetail.setAttrs("key1", "value1");
                attrModelpDetail.setAttrs("key2", 12345);
                attrModelpDetail.setAttrs("key3", false);
                attrModelpDetail.setAttrs("key4", 3.141592);


                AdBrixRm.Commerce.productView(productModel1, attrModelpDetail);
                break;

            case R.id.cart:
                /*
                ---------------------------------------------------------------------

                add_to_cart Event
                상품 상세보기

                유저가 상품을 장바구니에 담았을 때, 분석을 원한다면 다음 이벤트를 호출해줍니다.
                예시 : 이틀 내 배송완료가 보장되는 특별 기획 청소기
                ---------------------------------------------------------------------
                */

                /* 카테고리 모델 생성 */
                final AdBrixRm.CommerceCategoriesModel productCategory = new AdBrixRm.CommerceCategoriesModel().setCategory("special_day");

                /* 상품 정보 모델 생성 */
                ArrayList<AdBrixRm.CommerceProductModel> productModels = new ArrayList<AdBrixRm.CommerceProductModel>() {{

                    add(new AdBrixRm.CommerceProductModel()
                            .setProductID("87519765")
                            .setProductName("50%할인 내일모래 도착!! 초강력 청소기")
                            .setPrice(200000)
                            .setQuantity(1)
                            .setDiscount(100000)
                            .setCurrency(AdBrixRm.Currency.KR_KRW)
                            .setCategory(productCategory)
                            .setAttrModel(new AdBrixRm.AttrModel()
                                    .setAttrs("color", "블랙")
                                    .setAttrs("배송기간", 2)));

                    add(new AdBrixRm.CommerceProductModel()
                            .setProductID("87519766")
                            .setProductName("50%할인 내일모래 도착!! 초강력 청소기")
                            .setPrice(200000)
                            .setQuantity(2)
                            .setDiscount(100000)
                            .setCurrency(AdBrixRm.Currency.KR_KRW)
                            .setCategory(productCategory)
                            .setAttrModel(new AdBrixRm.AttrModel()
                                    .setAttrs("color", "화이트")
                                    .setAttrs("배송기간", 3)));
                    add(new AdBrixRm.CommerceProductModel()
                            .setProductID("87519767")
                            .setProductName("50%할인 내일모래 도착!! 초강력 청소기")
                            .setPrice(200000)
                            .setQuantity(1)
                            .setDiscount(100000)
                            .setCurrency(AdBrixRm.Currency.KR_KRW)
                            .setCategory(productCategory)
                            .setAttrModel(new AdBrixRm.AttrModel()
                                    .setAttrs("color", "로얄블루")
                                    .setAttrs("배송기간", 3)));
                }};

                /* 상품 장바구니 담기 */
                AdBrixRm.Commerce.addToCart(productModels);
                break;

        }
    }
}
