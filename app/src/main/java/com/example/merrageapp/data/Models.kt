package com.example.merrageapp.data

data class UserProfile(
    val id: String,
    val name: String,
    val age: Int,
    val bio: String,
    val images: List<String>,
    val occupation: String = "",
    val location: String = "",
    val distance: String = "",
    val interests: List<Interest> = emptyList(),
    val isVerified: Boolean = false,
    val isOnline: Boolean = false,
    val gender: String = "Woman",
    val jobTitle: String = "",
    val school: String = ""
)

data class Interest(
    val icon: String,
    val name: String
)

data class ChatMessage(
    val id: String,
    val senderId: String,
    val text: String,
    val timestamp: String,
    val isSeen: Boolean = false,
    val isImage: Boolean = false
)

data class ChatSummary(
    val user: UserProfile,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int = 0,
    val isActive: Boolean = false
)

data class Match(
    val user: UserProfile,
    val timestamp: String,
    val isNew: Boolean = true
)
