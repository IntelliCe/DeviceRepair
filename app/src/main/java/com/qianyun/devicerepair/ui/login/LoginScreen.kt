package com.qianyun.devicerepair.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qianyun.devicerepair.R
import com.qianyun.devicerepair.model.UiState
import com.qianyun.devicerepair.model.asContent
import com.qianyun.devicerepair.model.asError
import com.qianyun.devicerepair.model.isContent
import com.qianyun.devicerepair.model.isLoading
import com.qianyun.devicerepair.ui.commons.ActionBar
import com.qianyun.devicerepair.ui.commons.InputBox
import com.qianyun.devicerepair.ui.commons.PrimaryFilledButton
import com.qianyun.devicerepair.ui.commons.PrimaryOutlinedButton
import com.qianyun.devicerepair.ui.main.MainActivity
import com.qianyun.devicerepair.util.finishActivity
import com.qianyun.devicerepair.util.launchActivityThenFinishSelf

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        ActionBar(title = "维修系统", end = { Text(text = "设置") })
        Box {
            Image(
                painter = painterResource(id = R.drawable.pic_login_header),
                contentDescription = null,
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Column {
                Text(
                    text = "欢迎使用\n设备维修系统",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(28.dp),
                    lineHeight = 36.sp
                )
                Card(
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                            .padding(top = 12.dp)
                    ) {
                        InputBox(Modifier.fillMaxWidth(), value = viewModel.username, onValueChange = { viewModel.username = it }, hint = "请输入用户名")
                        Spacer(modifier = Modifier.height(16.dp))
                        InputBox(
                            Modifier.fillMaxWidth(),
                            value = viewModel.password,
                            onValueChange = { viewModel.password = it },
                            hint = "请输入密码",
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if (viewModel.uiState is UiState.Error) {
                            Text(
                                text = "登录失败：${viewModel.uiState.asError().message}",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        PrimaryFilledButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "登录",
                            loading = viewModel.uiState.isLoading(),
                            onClick = { viewModel.login() }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        PrimaryOutlinedButton(modifier = Modifier.fillMaxWidth(), text = "退出", onClick = { context.finishActivity() })
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "苏州杄云互联科技有限公司",
                            fontSize = 12.sp,
                            color = Color.Black.copy(alpha = 0.4f),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(viewModel.uiState) {
        if (viewModel.uiState.isContent() && viewModel.uiState.asContent().data) {
            context.launchActivityThenFinishSelf(MainActivity::class)
        }
    }
}
