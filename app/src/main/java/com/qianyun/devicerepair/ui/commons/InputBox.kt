package com.qianyun.devicerepair.ui.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7))
    ) {
        Box(Modifier.padding(16.dp), contentAlignment = Alignment.CenterStart) {
            Text(text = hint, color = Color.Black.copy(alpha = if (value.isEmpty()) 0.4f else 0f), fontSize = 18.sp)
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                visualTransformation = visualTransformation,
                textStyle = LocalTextStyle.current.copy(color = Color.Black, fontSize = 18.sp),
                maxLines = 1
            )
        }
    }
}