package com.example.latihan_2

data class UsersResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: ArrayList<UserMode>,
    val support: Support
)