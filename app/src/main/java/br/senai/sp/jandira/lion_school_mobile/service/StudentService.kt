package br.senai.sp.jandira.lion_school_mobile.service

import br.senai.sp.jandira.lion_school_mobile.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {
    @GET("alunos")
    fun getStudentByCourse(@Query("curso") curso: String): Call<StudentList>
}