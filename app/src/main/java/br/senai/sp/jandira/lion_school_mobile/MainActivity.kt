package br.senai.sp.jandira.lion_school_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lion_school_mobile.ui.theme.LionSchoolMobileTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolMobileTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 135.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(183.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(height = 183.dp, width = 140.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier
                            .height(84.dp)
                            .width(4.dp),
                        color = Color(229, 182, 87)
                    )
                    Spacer(modifier = Modifier.width(9.dp))
                    Text(
                        text = stringResource(id = R.string.title_name).uppercase(),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color(51, 71, 176)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .height(84.dp)
                        .width(4.dp),
                    color = Color(229, 182, 87)
                )
                Spacer(modifier = Modifier.width(9.dp))
                Text(
                    text = stringResource(id = R.string.Home_desc),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left,
                    color = Color.Black
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                border = BorderStroke(4.dp, color = Color(229, 182, 87)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(51, 71, 176))

            ) {
                Text(
                    text = stringResource(id = R.string.Button_get_started),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolMobileTheme {
        HomeScreen()
    }
}