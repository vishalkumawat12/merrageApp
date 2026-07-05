package com.example.merrageapp.data

object SampleData {
    val interests = listOf(
        Interest("🎨", "Art & Design"),
        Interest("☕", "Coffee Culture"),
        Interest("🧘‍♀️", "Yoga"),
        Interest("🎸", "Live Music"),
        Interest("🍷", "Wine Tasting"),
        Interest("⛰️", "Hiking"),
        Interest("🌮", "Tacos"),
        Interest("📸", "Photography"),
        Interest("✈️", "Travel")
    )

    val users = listOf(
        UserProfile(
            id = "1",
            name = "Chloe",
            age = 26,
            bio = "Coffee enthusiast, Sunday market regular, and amateur ceramicist. Just looking for someone who can appreciate a good pun and a better playlist. Spent most of my time designing apps by day and exploring the best hidden speakeasies by night.",
            images = listOf(
                "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1000&auto=format&fit=crop",
                "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?q=80&w=1000&auto=format&fit=crop",
                "https://images.unsplash.com/photo-1517841905240-472988babdf9?q=80&w=1000&auto=format&fit=crop"
            ),
            occupation = "UX Designer at Tech Studio",
            location = "Brooklyn, NY",
            distance = "2 miles away",
            interests = interests.take(6),
            isVerified = true,
            isOnline = true,
            jobTitle = "UX Designer",
            school = "NYU"
        ),
        UserProfile(
            id = "2",
            name = "Sarah",
            age = 24,
            bio = "Adventure seeker and coffee lover. Let's find the best sunset spot in the city. Looking for someone to share stories with.",
            images = listOf(
                "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?q=80&w=1000&auto=format&fit=crop",
                "https://images.unsplash.com/photo-1534528741775-53994a69daeb?q=80&w=1000&auto=format&fit=crop"
            ),
            occupation = "Graphic Designer",
            location = "Manhattan, NY",
            distance = "3 miles away",
            interests = listOf(interests[0], interests[1], interests[8]),
            isVerified = true,
            jobTitle = "Designer",
            school = "Parsons"
        ),
        UserProfile(
            id = "3",
            name = "Elena",
            age = 25,
            bio = "Yoga teacher and plant mom. 🌿 Let's grab some matcha!",
            images = listOf(
                "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?q=80&w=1000&auto=format&fit=crop",
                "https://images.unsplash.com/photo-1502672260266-1c1ef2d93688?q=80&w=1000&auto=format&fit=crop"
            ),
            occupation = "Yoga Instructor",
            location = "Queens, NY",
            distance = "5 miles away",
            interests = listOf(interests[2], interests[1], interests[5]),
            isVerified = false,
            jobTitle = "Instructor",
            school = "Columbia"
        )
    )

    val currentUser = UserProfile(
        id = "me",
        name = "Alex",
        age = 28,
        bio = "Tech enthusiast and amateur photographer. Love exploring the city and finding new places to eat.",
        images = listOf(
            "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1000&auto=format&fit=crop"
        ),
        occupation = "Software Engineer",
        location = "Brooklyn, NY",
        interests = interests.take(4),
        isVerified = true
    )


    val chatSummaries = listOf(
        ChatSummary(
            user = users[0],
            lastMessage = "That restaurant sounds amazing! When are you free?",
            timestamp = "2m ago",
            unreadCount = 2,
            isActive = true
        ),
        ChatSummary(
            user = UserProfile("4", "Jessica", 23, "", listOf("https://lh3.googleusercontent.com/aida-public/AB6AXuCsi-6TdEMdYLf1jFogKJHAurKo-1MSqGLFP23Xpz-gBbckdey4tHPBkFgvEBU94Tcz9iTWeViqYfTetoV4TUGVUw_FPxUlfYFgwdvWIG3S99-GptTPsmdq1DOW4kzrY4jIyYcUWPEwm07Lqu3BZdKhPCRetyJqw0n_56wsKcWfZ9sNyWxOWLiuNKCWqdIxWC48sPgbXZBARq5VmespZVBz0IUeX8r340u8RuzGeBMIuiQhsF7YA6LENQ5R7E3rtpA2xxwK31q9itZe"), "", "", ""),
            lastMessage = "Haha, I know exactly what you mean!",
            timestamp = "1h ago"
        ),
        ChatSummary(
            user = UserProfile("5", "Sophie", 25, "", listOf("https://lh3.googleusercontent.com/aida-public/AB6AXuCV5r8qfG0HkYDfblMdE2oPiILU-xDcDsnS2qoH6lGObKVdxsBt39nQSzWUBt9J3N2YhnkeeWN_OHOkAk_O5e7fa_hChrpO4M6CzupHCaS8ZtCUHKThwVInnQmxdamSZ5VsEIwy26Nl3NYstxhd1uG2mX0UQ8n4T_gPtZLB7h23ulo3ODQ7utNUMRx91ZMr41R7O3ChO_60lccM0Z0fcZQAxgveNDzIEVCyommpOtu8fTMEhvGQegpZTCruJdZo-vMC7F4FTEXG5yo7"), "", "", ""),
            lastMessage = "Sent a photo",
            timestamp = "3h ago"
        )
    )

    val chatMessages = listOf(
        ChatMessage("1", "1", "Hey Alex! I saw on your profile that you're a fan of street photography. Did you catch the new exhibition downtown? 📸", "10:24 AM"),
        ChatMessage("2", "me", "Oh hey! Yes, I actually went there last Saturday. The black and white series was absolutely stunning. Have you been yet?", "10:28 AM", isSeen = true),
        ChatMessage("3", "1", "Not yet! I was planning to go this weekend. Would you want to go again and show me your favorite pieces? I'd love a pro's perspective! 😊", "10:30 AM"),
        ChatMessage("4", "me", "I'd love to! I definitely don't mind a second look. How about Sunday afternoon?", "10:32 AM", isSeen = true)
    )

    val matches = listOf(
        Match(users[2], "Today"),
        Match(users[1], "Yesterday"),
        Match(users[0], "2 days ago")
    )
    
    val likedUsers = listOf(
        users[1],
        users[0],
        users[2]
    )
}
