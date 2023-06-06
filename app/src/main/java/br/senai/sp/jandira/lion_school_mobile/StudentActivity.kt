package br.senai.sp.jandira.lion_school_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lion_school_mobile.components.Footer
import br.senai.sp.jandira.lion_school_mobile.components.HeaderReturn
import br.senai.sp.jandira.lion_school_mobile.model.Student
import br.senai.sp.jandira.lion_school_mobile.model.StudentList
import br.senai.sp.jandira.lion_school_mobile.service.RetrofitFactory
import br.senai.sp.jandira.lion_school_mobile.ui.theme.LionSchoolMobileTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dado = intent.getStringExtra("sigla")
        setContent {
            LionSchoolMobileTheme {
                // A surface container using the 'background' color from the theme
                StudentScreen(dado.toString())
            }
        }
    }
}

@Composable
fun StudentScreen(sigla: String) {
    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var name by remember {
        mutableStateOf("")
    }

    val call = RetrofitFactory().getStudentsService().getStudentByCourse(sigla)

    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            listStudent = response.body()!!.aluno

            name = response.body()!!.NomeCurso
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {

        }
    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderReturn()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f)
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(text = stringResource(id = R.string.courses),
                    fontSize = 24.sp,
                    color = Color.Black)
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn() {
                items(listStudent) {
                    Button(
                        onClick = {
                            var openScore = Intent()
                            openScore.putExtra("numeroMatricula", it.matricula)
                            context.startActivity(openScore)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max),
                        colors = ButtonDefaults.buttonColors(getColorStatus(it.status)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max)
                        ) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(80.dp)
                            )
                            Text(
                                text = it.nome.uppercase(),
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
            Footer()
        }
    }
}

fun getColorStatus(status: String): Color{
    return if(status != "Finalizado") {
        Color(51, 71, 176)
    }else{
        Color(229,182,87)
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DefaultPreview3() {
//    LionSchoolMobileTheme {
//        StudentScreen(dado.toString())
//    }
//}