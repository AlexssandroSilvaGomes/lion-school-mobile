package br.senai.sp.jandira.lion_school_mobile.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lion_school_mobile.CoursesActivity

@Composable
fun HeaderConfig() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.baseline_settings_24),
                contentDescription = "",
                tint = Color(51, 71, 176),
                modifier = Modifier.size(42.dp)
            )
            Row(
                modifier = Modifier
                    .height(45.dp)
            ) {
                Image(
                    painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.logo_image),
                    contentDescription = "",
                    modifier = Modifier
                        .height(45.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier
                            .height(84.dp)
                            .width(2.dp),
                        color = Color(229, 182, 87)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = br.senai.sp.jandira.lion_school_mobile.R.string.title_name).uppercase(),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .width(65.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176)
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth(),
            color = Color(229, 182, 87)
        )
    }
}

@Composable
fun HeaderReturn() {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                var openCourses = Intent(context, CoursesActivity::class.java)
                context.startActivity(openCourses)
            }) {
                Icon(
                    painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.baseline_arrow_circle_left_24),
                    contentDescription = "",
                    modifier = Modifier.size(42.dp),
                    tint = Color(51, 71, 176)
                )
            }

            Row(
                modifier = Modifier
                    .height(45.dp)
            ) {
                Image(
                    painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.logo_image),
                    contentDescription = "",
                    modifier = Modifier
                        .height(45.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier
                            .height(84.dp)
                            .width(2.dp),
                        color = Color(229, 182, 87)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = br.senai.sp.jandira.lion_school_mobile.R.string.title_name).uppercase(),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .width(65.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color(51, 71, 176)
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth(),
            color = Color(229, 182, 87)
        )
    }
}


@Preview
@Composable
fun HeaderPreview() {
    HeaderConfig()
    HeaderReturn()
}

@Preview
@Composable
fun FooterPreview() {
    Footer()
}

@Composable
fun Footer() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        Divider(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth(),
            color = Color(229, 182, 87)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(24.dp)
                .width(244.dp)
        ) {
            Icon(painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.youtube),
                contentDescription = "",
            modifier = Modifier
                .size(width = 24.dp, height = 24.dp),
                tint = Color(51, 71, 176)
            )
            Icon(painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.twitter),
                contentDescription = "",
                modifier = Modifier
                        .size(width = 24.dp, height = 24.dp),
                tint = Color(51, 71, 176)
            )
            Icon(painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.instagram),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 24.dp, height = 24.dp),
                tint = Color(51, 71, 176)
            )
            Icon(painter = painterResource(id = br.senai.sp.jandira.lion_school_mobile.R.drawable.facebook),
                contentDescription = "",
                modifier = Modifier
                    .size(width = 24.dp, height = 24.dp),
                tint = Color(51, 71, 176)
            )
        }
    }
}