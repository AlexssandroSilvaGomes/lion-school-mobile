package br.senai.sp.jandira.lion_school_mobile.model

data class StudentScore(
    var nome: String,
    var foto: String,
    var matricula: String,
    var status: String,
    var disciplinas: List<Discipline>
)
