package br.senai.sp.jandira.lion_school_mobile.service

import br.senai.sp.jandira.lion_school_mobile.model.StudentScore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ScoreService {
    @GET("alunos/{matricula}")
    fun getStudentByRegistration(@Path("matricula") matricula: String): Call<StudentScore>
}