package br.senai.sp.jandira.lion_school_mobile.model

data class Aluno(
    val foto: String? = null,
    val nome: String? = null,
    val status: String? = null,
    val curso: List<CoursesList>? = null
)
