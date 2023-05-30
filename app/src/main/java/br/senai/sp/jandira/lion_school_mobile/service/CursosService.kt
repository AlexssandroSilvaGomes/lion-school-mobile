package br.senai.sp.jandira.lion_school_mobile.service

import br.senai.sp.jandira.lion_school_mobile.model.CoursesList
import retrofit2.Call
import retrofit2.http.GET

interface CursosService {
    @GET("cursos")
    fun getCursos(): Call<CoursesList>
}