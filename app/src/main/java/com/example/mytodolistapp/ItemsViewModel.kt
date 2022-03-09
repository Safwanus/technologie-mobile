package com.example.mytodolistapp

import java.util.UUID

data class ItemsViewModel(var status: String, var title: String, var description: String, val uuid: String) {
}
