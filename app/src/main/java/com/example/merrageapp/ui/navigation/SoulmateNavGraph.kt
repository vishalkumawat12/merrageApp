package com.example.merrageapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.merrageapp.ui.screens.*
import kotlinx.serialization.Serializable

@Serializable
sealed interface SoulmateRoute

@Serializable
data object Welcome : SoulmateRoute
@Serializable
data object SignIn : SoulmateRoute
@Serializable
data object CreateAccount : SoulmateRoute
@Serializable
data object Discovery : SoulmateRoute
@Serializable
data object Likes : SoulmateRoute
@Serializable
data object Messages : SoulmateRoute
@Serializable
data object Profile : SoulmateRoute
@Serializable
data class ProfileDetail(val userId: String) : SoulmateRoute
@Serializable
data class Chat(val userId: String) : SoulmateRoute
@Serializable
data object Settings : SoulmateRoute
@Serializable
data object EditProfile : SoulmateRoute
@Serializable
data object SafetyCenter : SoulmateRoute
@Serializable
data object PrivacyPolicy : SoulmateRoute

class SoulmateNavigator(val backStack: MutableList<Any>) {
    fun navigateTo(route: Any) {
        if (route is Discovery || route is Likes || route is Messages || route is Profile) {
            // Top level routes: clear stack and add
            backStack.clear()
            backStack.add(route)
        } else {
            backStack.add(route)
        }
    }

    fun pop() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.size - 1)
        }
    }
}

@Composable
fun SoulmateNavGraph() {
    val backStack = remember { mutableStateListOf<Any>(Welcome) }
    val navigator = remember { SoulmateNavigator(backStack) }

    NavDisplay(
        backStack = backStack,
        onBack = { navigator.pop() },
        entryProvider = { key ->
            when (key) {
                is Welcome -> NavEntry(key) {
                    WelcomeScreen(
                        onSignInClick = { navigator.navigateTo(SignIn) },
                        onCreateAccountClick = { navigator.navigateTo(CreateAccount) }
                    )
                }
                is SignIn -> NavEntry(key) {
                    SignInScreen(
                        onBackClick = { navigator.pop() },
                        onSignInSuccess = { navigator.navigateTo(Discovery) },
                        onSignUpClick = { navigator.navigateTo(CreateAccount) }
                    )
                }
                is CreateAccount -> NavEntry(key) {
                    CreateAccountScreen(
                        onBackClick = { navigator.pop() },
                        onSignUpSuccess = { navigator.navigateTo(Discovery) },
                        onSignInClick = { navigator.navigateTo(SignIn) }
                    )
                }
                is Discovery -> NavEntry(key) {
                    DiscoveryScreen(
                        onProfileClick = { id -> navigator.navigateTo(ProfileDetail(id)) },
                        onNavigate = { routeStr ->
                            when(routeStr) {
                                "likes" -> navigator.navigateTo(Likes)
                                "messages" -> navigator.navigateTo(Messages)
                                "profile" -> navigator.navigateTo(Profile)
                                else -> {}
                            }
                        }
                    )
                }
                is Likes -> NavEntry(key) {
                    LikesScreen(
                        onNavigate = { routeStr ->
                            when(routeStr) {
                                "discover" -> navigator.navigateTo(Discovery)
                                "messages" -> navigator.navigateTo(Messages)
                                "profile" -> navigator.navigateTo(Profile)
                                else -> {}
                            }
                        }
                    )
                }
                is Messages -> NavEntry(key) {
                    MessagesScreen(
                        onChatClick = { id -> navigator.navigateTo(Chat(id)) },
                        onNavigate = { routeStr ->
                            when(routeStr) {
                                "discover" -> navigator.navigateTo(Discovery)
                                "likes" -> navigator.navigateTo(Likes)
                                "profile" -> navigator.navigateTo(Profile)
                                else -> {}
                            }
                        }
                    )
                }
                is Profile -> NavEntry(key) {
                    ProfileScreen(
                        onSettingsClick = { navigator.navigateTo(Settings) },
                        onSafetyClick = { navigator.navigateTo(SafetyCenter) },
                        onEditProfileClick = { navigator.navigateTo(EditProfile) },
                        onNavigate = { routeStr ->
                            when(routeStr) {
                                "discover" -> navigator.navigateTo(Discovery)
                                "likes" -> navigator.navigateTo(Likes)
                                "messages" -> navigator.navigateTo(Messages)
                                else -> {}
                            }
                        }
                    )
                }
                is ProfileDetail -> NavEntry(key) {
                    ProfileDetailScreen(
                        userId = key.userId,
                        onBackClick = { navigator.pop() }
                    )
                }
                is Chat -> NavEntry(key) {
                    ChatScreen(
                        userId = key.userId,
                        onBackClick = { navigator.pop() }
                    )
                }
                is Settings -> NavEntry(key) {
                    SettingsScreen(
                        onBackClick = { navigator.pop() },
                        onPrivacyPolicyClick = { navigator.navigateTo(PrivacyPolicy) }
                    )
                }
                is EditProfile -> NavEntry(key) {
                    EditProfileScreen(onBackClick = { navigator.pop() })
                }
                is SafetyCenter -> NavEntry(key) {
                    SafetyCenterScreen(onBackClick = { navigator.pop() })
                }
                is PrivacyPolicy -> NavEntry(key) {
                    PrivacyPolicyScreen(onBackClick = { navigator.pop() })
                }
                else -> error("Unknown route: $key")
            }
        }
    )
}
