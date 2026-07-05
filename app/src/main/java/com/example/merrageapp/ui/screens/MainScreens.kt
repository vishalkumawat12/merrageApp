package com.example.merrageapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.merrageapp.data.SampleData
import com.example.merrageapp.ui.components.*
import com.example.merrageapp.ui.theme.*

@Composable
fun DiscoveryScreen(
    onProfileClick: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            SoulmateTopBar(
                title = "Soulmate",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Tune, contentDescription = "Filter", tint = Color.Gray)
                    }
                }
            )
        },
        bottomBar = {
            SoulmateBottomBar(currentRoute = "discover", onNavigate = onNavigate)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            val user = SampleData.users[0]
            
            ProfileCard(
                user = user,
                onClick = { onProfileClick(user.id) },
                modifier = Modifier.weight(1f)
            )

            ActionButtons(
                onDislike = { },
                onLike = { },
                onStar = { }
            )
        }
    }
}

@Composable
fun LikesScreen(
    onNavigate: (String) -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    
    Scaffold(
        topBar = {
            SoulmateTopBar(
                title = "Soulmate",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = SoulmatePrimary)
                    }
                }
            )
        },
        bottomBar = {
            SoulmateBottomBar(currentRoute = "likes", onNavigate = onNavigate)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Tabs
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Likes",
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = 0 }
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center,
                    color = if (selectedTab == 0) SoulmatePrimary else Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Matches",
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = 1 }
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center,
                    color = if (selectedTab == 1) SoulmatePrimary else Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.LightGray.copy(alpha = 0.2f))) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .align(if (selectedTab == 0) Alignment.CenterStart else Alignment.CenterEnd)
                        .background(SoulmatePrimary)
                )
            }

            if (selectedTab == 0) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { GridItemSpan(2) }) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = SoulmatePrimary),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("See who likes you", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Upgrade to Gold to reveal 42 people!", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(12.dp))
                                Button(
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(vertical = 8.dp)
                                ) {
                                    Text("Get Soulmate Gold", color = SoulmatePrimary, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    items(SampleData.likedUsers) { user ->
                        Box(
                            modifier = Modifier
                                .aspectRatio(0.75f)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            AsyncImage(
                                model = user.images.firstOrNull(),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().blur(8.dp),
                                contentScale = ContentScale.Crop
                            )
                            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))
                            Text(
                                text = "${user.name}, ${user.age}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)
                            )
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(8.dp)
                                    .background(SoulmatePrimary, CircleShape)
                                    .padding(4.dp)
                                    .size(12.dp)
                            )
                        }
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(SampleData.matches) { match ->
                        Box(
                            modifier = Modifier
                                .aspectRatio(0.75f)
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { }
                        ) {
                            AsyncImage(
                                model = match.user.images.firstOrNull(),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        androidx.compose.ui.graphics.Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                                            startY = 100f
                                        )
                                    )
                            )
                            Text(
                                text = "${match.user.name}, ${match.user.age}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp)
                            )
                            if (match.isNew) {
                                Surface(
                                    color = SoulmatePrimary,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                                ) {
                                    Text(
                                        "NEW",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MessagesScreen(
    onChatClick: (String) -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            SoulmateTopBar(
                title = "Messages",
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Tune, contentDescription = "Filter", tint = Color.Gray)
                    }
                }
            )
        },
        bottomBar = {
            SoulmateBottomBar(currentRoute = "messages", onNavigate = onNavigate)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Text(
                    text = "New Matches",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(SampleData.users) { user ->
                        MatchBubble(user = user, onClick = { onChatClick(user.id) })
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.LightGray.copy(alpha = 0.2f))
            }

            item {
                Text(
                    text = "Recent Messages",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
                )
            }

            items(SampleData.chatSummaries) { summary ->
                ChatRow(summary = summary, onClick = { onChatClick(summary.user.id) })
            }
        }
    }
}

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit,
    onSafetyClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val currentUser = SampleData.users[0] // Mock current user

    Scaffold(
        bottomBar = {
            SoulmateBottomBar(currentRoute = "profile", onNavigate = onNavigate)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            Box(contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    border = BorderStroke(4.dp, SoulmatePrimaryTransparent20)
                ) {
                    AsyncImage(
                        model = currentUser.images.firstOrNull(),
                        contentDescription = null,
                        modifier = Modifier.clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                IconButton(
                    onClick = onEditProfileClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(SoulmatePrimary, CircleShape)
                        .size(32.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${currentUser.name}, ${currentUser.age}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = currentUser.occupation,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileOptionCard(
                    icon = Icons.Default.Settings,
                    label = "Settings",
                    onClick = onSettingsClick,
                    modifier = Modifier.weight(1f)
                )
                ProfileOptionCard(
                    icon = Icons.Default.Shield,
                    label = "Safety",
                    onClick = onSafetyClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Premium Banner
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = SoulmatePrimaryTransparent)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(48.dp).background(SoulmatePrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Soulmate Gold", fontWeight = FontWeight.Bold, color = SoulmatePrimary)
                        Text("Get unlimited likes & more", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Completeness progress
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Profile Completeness", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("80%", color = SoulmatePrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { 0.8f },
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                    color = SoulmatePrimary,
                    trackColor = SoulmatePrimaryTransparent
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun ProfileOptionCard(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = SoulmatePrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelLarge)
        }
    }
}
