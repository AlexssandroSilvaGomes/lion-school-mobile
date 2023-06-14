package br.senai.sp.jandira.lion_school_mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lion_school_mobile.model.StudentScore
import br.senai.sp.jandira.lion_school_mobile.service.RetrofitFactory
import br.senai.sp.jandira.lion_school_mobile.ui.theme.LionSchoolMobileTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getStringExtra("numeroMatricula")
        setContent {
            LionSchoolMobileTheme {
                // A surface container using the 'background' color from the theme
                StudentDataScreen(data.toString())
            }
        }
    }
}

@Composable
fun StudentDataScreen(matricula: String) {
    val context = LocalContext.current

    var studentData by remember {
        mutableStateOf(StudentScore("", "", "", "", emptyList()))
    }

    val call = RetrofitFactory().getScoreStudent().getStudentByRegistration(matricula)

    call.enqueue(object : Callback<StudentScore> {
        override fun onResponse(
            call: Call<StudentScore>,
            response: Response<StudentScore>
        ) {
            if(response.isSuccessful) {
                val studentResponse = response.body()
                if(studentResponse != null) {
                    studentData = studentResponse
                }
            }
        }

        override fun onFailure(call: Call<StudentScore>, t: Throwable) {

        }
    })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = getColorStatus(studentData.status)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {
                    var openCourses = Intent(context, CoursesActivity::class.java)
                    context.startActivity(openCourses)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_circle_left_24),
                        contentDescription = "",
                        modifier = Modifier.size(42.dp),
                        tint = Color.White
                    )
                }

            }
            Spacer(modifier = Modifier.height(36.dp))
            AsyncImage(
                model = studentData.foto,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = studentData.nome,
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(studentData.disciplinas) {
                        Spacer(modifier = Modifier.width(5.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(50.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.media,
                                color = getColorScore(it.media.toDouble()),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Column(
                                modifier = Modifier
                                    .height(intrinsicSize = IntrinsicSize.Min),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Surface() {
                                    Card(
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(16.dp),
                                        backgroundColor = Color.Gray
                                    ) {}
                                    Card(
                                        modifier = Modifier
                                            .height(it.media.toDouble().dp)
                                            .width(16.dp),
                                        backgroundColor = getColorScore(it.media.toDouble())
                                    ) {}
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = it.sigla,
                                    color = getColorScore(it.media.toDouble()),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }

                    }
                }
            }
        }
    }

}

fun getColorScore (nota: Double): Color {
    return if (nota > 69){
        Color(51, 71, 176)
    }else if(nota > 49 && nota < 70){
        Color(229,182,87)
    }else{
        Color(193,16,16)
    }
}
