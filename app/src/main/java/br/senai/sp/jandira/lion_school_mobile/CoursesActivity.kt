package br.senai.sp.jandira.lion_school_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lion_school_mobile.components.Footer
import br.senai.sp.jandira.lion_school_mobile.components.Header
import br.senai.sp.jandira.lion_school_mobile.model.Courses
import br.senai.sp.jandira.lion_school_mobile.model.CoursesList
import br.senai.sp.jandira.lion_school_mobile.service.RetrofitFactory
import br.senai.sp.jandira.lion_school_mobile.ui.theme.LionSchoolMobileTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolMobileTheme {
                CoursesScreen()
            }
        }
    }
}

@Composable
fun CoursesScreen() {

    val context = LocalContext.current

    var listCourses by remember {
        mutableStateOf(listOf<Courses>())
    }

    var searchState by remember {
        mutableStateOf("")
    }

    val Call = RetrofitFactory().getCoursesService().getCursos()

    Call.enqueue(object : Callback<CoursesList>{
        override fun onResponse(
            call: Call<CoursesList>,
            response: Response<CoursesList>
        ) {
            listCourses = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CoursesList>, t: Throwable) {

        }
    })

    Surface (
        modifier = Modifier.fillMaxSize()
            ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Header()
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f)
            ) {
                OutlinedTextField(
                    value = searchState,
                    onValueChange = {searchState = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .background(
                            color = Color(51, 71, 176),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(
                            2.dp,
                            color = Color(229, 182, 87),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = stringResource(id = R.string.search_courses),
                        color = Color.White,
                        modifier = Modifier.fillMaxSize(),
                        fontSize = 16.sp)},
                    trailingIcon = ({ Icon(painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(width = 32.dp, height = 32.dp)
                            .rotate(90f)
                    )})
                )
                Spacer(modifier = Modifier.height(48.dp))
                Text(text = stringResource(id = R.string.courses),
                    fontSize = 24.sp,
                    color = Color.Black)
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn() {
                items(listCourses) {
                    Button(onClick = {
                        var openStudents = Intent(context, StudentActivity::class.java)
                        openStudents.putExtra("sigla", it.sigla)
                        context.startActivity(openStudents)
                    },
                        colors = ButtonDefaults.buttonColors(Color(51, 71, 176)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        border = BorderStroke(width = 4.dp, color = Color(229, 182, 87)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                        ) {
                            AsyncImage(model = it.icone,
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(width = 80.dp, height = 80.dp))
                            Spacer(modifier = Modifier.width(20.dp))
                            Column (
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(text = it.sigla,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White)
                                Text(text = it.nome,
                                    fontSize = 16.sp,
                                    color = Color.White)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
            Footer()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolMobileTheme {
        CoursesScreen()
    }
}