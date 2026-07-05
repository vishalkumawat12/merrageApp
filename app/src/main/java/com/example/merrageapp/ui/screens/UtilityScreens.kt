package com.example.merrageapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.merrageapp.data.SampleData
import com.example.merrageapp.ui.components.SoulmateTextField
import com.example.merrageapp.ui.components.SoulmateTopBar
import com.example.merrageapp.ui.theme.*

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit
) {
    Scaffold(
        topBar = { SoulmateTopBar(title = "Settings", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SettingsSection(title = "Account Settings") {
                SettingsItem(icon = Icons.Default.Smartphone, title = "Phone Number", subtitle = "+1 234 567 8900")
                SettingsItem(icon = Icons.Default.Mail, title = "Email", subtitle = "chloe.designer@example.com")
                SettingsItem(icon = Icons.Default.Lock, title = "Password", subtitle = "Last changed 3 months ago")
            }

            SettingsSection(title = "Discovery Settings") {
                SettingsItem(icon = Icons.Default.LocationOn, title = "Location", subtitle = "Brooklyn, NY")
                // Distance Range Placeholder
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Distance Range", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                        Text("50 miles", color = SoulmatePrimary, fontWeight = FontWeight.Bold)
                    }
                    Slider(value = 0.5f, onValueChange = {}, colors = SliderDefaults.colors(thumbColor = SoulmatePrimary, activeTrackColor = SoulmatePrimary))
                }
            }

            SettingsSection(title = "Notifications & Privacy") {
                SettingsToggle(icon = Icons.Default.Notifications, title = "Push Notifications", checked = true)
                SettingsToggle(icon = Icons.Default.VisibilityOff, title = "Incognito Mode", checked = false)
            }

            SettingsSection(title = "Help & Support") {
                SettingsItem(icon = Icons.Default.Help, title = "Help Center")
                SettingsItem(icon = Icons.Default.Policy, title = "Privacy Policy", onClick = onPrivacyPolicyClick)
                SettingsItem(icon = Icons.Default.Security, title = "Safety Center", onClick = { /* Navigate to Safety Center handled in NavGraph */ })
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = androidx.compose.foundation.BorderStroke(2.dp, SoulmatePrimaryTransparent)
            ) {
                Text("Log Out", color = SoulmatePrimary, fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Version 1.0.0 (Soulmate Prototype)",
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = SoulmatePrimary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Surface(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            shadowElevation = 1.dp
        ) {
            Column(content = content)
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp).background(SoulmatePrimaryTransparent5, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = SoulmatePrimary, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
            if (subtitle != null) {
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
    }
}

@Composable
fun SettingsToggle(icon: ImageVector, title: String, checked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = SoulmatePrimary)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = { }, colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = SoulmatePrimary))
    }
}

@Composable
fun SafetyCenterScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { SoulmateTopBar(title = "Safety Center", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Your safety is our priority",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Explore tools and resources designed to keep your experience safe.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text("Safety Tools", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                SafetyToolCard(icon = Icons.Default.Report, title = "Report", subtitle = "Flag behavior", modifier = Modifier.weight(1f))
                SafetyToolCard(icon = Icons.Default.Block, title = "Block", subtitle = "Prevent contact", modifier = Modifier.weight(1f))
            }
            
            Spacer(modifier = Modifier.height(32.dp))

            Text("Emergency Resources", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Card(
                colors = CardDefaults.cardColors(containerColor = SoulmatePrimary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Call, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("National Dating Safety Hotline", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun SafetyToolCard(icon: ImageVector, title: String, subtitle: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.size(32.dp).background(SoulmatePrimaryTransparent5, CircleShape), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = SoulmatePrimary, modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        }
    }
}

@Composable
fun PrivacyPolicyScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = { SoulmateTopBar(title = "Privacy Policy", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = "Your Privacy Matters",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "At Soulmate, we build connections based on trust. This starts with being transparent about how we handle your information.",
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            PrivacySection(
                icon = Icons.Default.PersonSearch,
                title = "Information We Collect",
                content = "To help you find your perfect match, we collect information such as your name, age, gender, and location."
            )
            
            PrivacySection(
                icon = Icons.Default.Insights,
                title = "How We Use Your Data",
                content = "We use your data to power our matchmaking algorithms and provide personalized recommendations."
            )
        }
    }
}

@Composable
fun PrivacySection(icon: ImageVector, title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).background(SoulmatePrimaryTransparent5, RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = SoulmatePrimary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(content, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@Composable
fun EditProfileScreen(onBackClick: () -> Unit) {
    val user = SampleData.users[0]
    var name by remember { mutableStateOf(user.name) }
    var bio by remember { mutableStateOf(user.bio) }
    var jobTitle by remember { mutableStateOf(user.jobTitle) }
    var school by remember { mutableStateOf(user.school) }

    Scaffold(
        topBar = {
            SoulmateTopBar(
                title = "Edit Profile",
                onBackClick = onBackClick,
                actions = {
                    TextButton(onClick = onBackClick) {
                        Text("Done", color = SoulmatePrimary, fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Photos", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(2f).aspectRatio(0.8f).clip(RoundedCornerShape(12.dp))) {
                    AsyncImage(model = user.images.firstOrNull(), contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    IconButton(onClick = {}, modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp).background(SoulmatePrimary, CircleShape).size(24.dp)) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(2) { index ->
                        Box(modifier = Modifier.aspectRatio(1f).clip(RoundedCornerShape(12.dp)).background(Color.LightGray.copy(alpha = 0.2f))) {
                            if (user.images.size > index + 1) {
                                AsyncImage(model = user.images[index + 1], contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                            } else {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.align(Alignment.Center), tint = SoulmatePrimary)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            SoulmateTextField(value = name, onValueChange = { name = it }, label = "Name", placeholder = "Enter your name")
            Spacer(modifier = Modifier.height(16.dp))
            SoulmateTextField(value = bio, onValueChange = { bio = it }, label = "About Me", placeholder = "Tell us about yourself")
            Spacer(modifier = Modifier.height(16.dp))
            SoulmateTextField(value = jobTitle, onValueChange = { jobTitle = it }, label = "Job Title", placeholder = "What do you do?")
            Spacer(modifier = Modifier.height(16.dp))
            SoulmateTextField(value = school, onValueChange = { school = it }, label = "School", placeholder = "Where did you study?")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text("Interests", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Select up to 5 interests to show on your profile.", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Interest Chips Placeholder
            Row(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    color = SoulmatePrimary,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Photography", color = Color.White, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp)
                }
                Surface(
                    color = SoulmatePrimaryTransparent5,
                    border = BorderStroke(1.dp, SoulmatePrimaryTransparent20),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Cooking", color = SoulmatePrimary, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
