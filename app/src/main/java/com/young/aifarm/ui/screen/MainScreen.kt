package com.young.aifarm.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakao.sdk.user.UserApiClient

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable {
                    logoutKakao()
                }
        )
        Text(text = "Welcome to Main Screen!", fontSize = 24.sp)
    }
}

private fun logoutKakao() {
    UserApiClient.instance.logout { error ->
        if (error != null) {
            Log.e("logouts", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
        }
        else {
            Log.i("logoute", "로그아웃 성공. SDK에서 토큰 삭제됨")
        }
    }
}