package com.young.aifarm.ui.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.young.aifarm.R

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
        } else if (token != null) {
            getKakaoNickName()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AIFarm",
            modifier = Modifier.padding(bottom = 65.dp)
        )

        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable {
                    loginKakao(navController, context, kakaoCallback)
                }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_kakao_login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

private fun loginKakao(
    navController: NavController,
    context: Context,
    kakaoCallback: (OAuthToken?, Throwable?) -> Unit
) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                else {
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
                }
            } else if (token != null) {
                navController.navigate("main")
                getKakaoNickName()
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
    }
}

private fun getKakaoNickName() {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
           /* Log.e("Kakao", "사용자 정보 요청 실패", error)*/
        }
        else if (user != null) {
            /* 로그인 정보 Mysql에 저장 구현 예정
            Log.i("Kakao", "사용자 정보 요청 성공" +
                    "\n회원번호: ${user.id}" +
                    "\n이메일: ${user.kakaoAccount?.email}" +
                    "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                    "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")*/
        }
    }
}
