package com.company.velogradiobutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.velogradiobutton.ui.theme.VelogRadioButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VelogRadioButtonTheme {
                Column {
                    RadioButtonsStudy()
                }

            }
        }
    }
}
// 라디오 버튼 객체로 활용 할 수 있도록 데이터 클래스를 선언해준다.
data class RadioButtonInfo(
    val isChecked: Boolean,
    val text: String
)

@Composable
fun RadioButtonsStudy() {
    // remember 키워드를 활용해서 radioButtons를 컴포즈가 추적 할 수 있게 도와준다.
    val radioButtons = remember {
        mutableStateListOf(
            RadioButtonInfo(
                isChecked = true,
                text = "1"
            ),
            RadioButtonInfo(
                isChecked = false,
                text = "2"
            ),
            RadioButtonInfo(
                isChecked = false,
                text = "3"
            ),
        )
    }

    // forEachIndexed는 radioButtons(mutableStateList)를 순회하면서
    // {}코드 블락 내부에서 인덱스에 알맞는 객체가 소비된다.
    // index는 radioButton의 인덱스 번호를 나타낸다.
    // info는 radioButton의 인덱스에 해당하는 객체의 정보를 갖는다.
    radioButtons.forEachIndexed { index, info ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    // replaceAll은 조건에 알맞게 리스트의 모든 요소를 대체한다.
                    radioButtons.replaceAll {
                        it.copy(
                            // info는 radioButtons(데이터클래스가 모인 리스트)
                            // it은 내가 누른 라디오버튼 클래스
                            isChecked = it.text == info.text
                        )
                    }
                }
                .padding(end = 16.dp)
        ) {
            RadioButton(
                selected = info.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            // info는 radioButtons(데이터클래스가 모인 리스트)
                            // it은 내가 누른 라디오버튼 클래스
                            isChecked = it.text == info.text
                        )
                    }
                }
            )
            Text(text = info.text)
        }
    }
}