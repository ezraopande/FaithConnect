package com.ezra.newmvvm.data.branches

data class Branch(
    val pastor: String?,
    val departments: List<String>,
    val name: String,
    val location: String,
    val phone: String,
    val email: String,
    val about: String,
    val branch_image: String?
)
