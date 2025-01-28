package org.example.pajak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.pajak.ui.layout.Sidebar
import org.example.pajak.ui.pages.Camera
import org.example.pajak.ui.pages.DataPemilik
import org.example.pajak.ui.pages.DetailPemilik
import org.example.pajak.ui.pages.Laporan
import org.example.pajak.ui.pages.TambahData

sealed class Screen {
    object Camera : Screen()
    object Laporan : Screen()
    object DataPemilik : Screen()
    data class DetailPemilik(val id: Int) : Screen()
    object TambahData : Screen()

}




// Navigation Manager
class NavigationManager {
    private val _currentScreen = mutableStateOf<Screen>(Screen.Camera)
    val currentScreen: State<Screen> get() = _currentScreen

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navigationManager: NavigationManager) {
    val currentScreen by navigationManager.currentScreen
    val isSidebarVisible = currentScreen !is Screen.DetailPemilik && currentScreen !is Screen.TambahData
    var isSidebarOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isSidebarVisible) {
            Row(modifier = Modifier.fillMaxSize()) {
                Sidebar(
                    isSidebarOpen = isSidebarOpen,
                    toggleSidebar = { isSidebarOpen = !isSidebarOpen },
                    currentScreen = currentScreen,
                    navigateCamera = { navigationManager.navigateTo(Screen.Camera) },
                    navigateLaporan = { navigationManager.navigateTo(Screen.Laporan) },
                    navigateDataPemilik = { navigationManager.navigateTo(Screen.DataPemilik) }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                ) {
                    ScreenContent(currentScreen, navigationManager)
                }
            }
        } else {
            ScreenContent(currentScreen, navigationManager)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(currentScreen: Screen, navigationManager: NavigationManager) {
    when (currentScreen) {
        is Screen.Camera -> Camera()
        is Screen.Laporan -> Laporan()
        is Screen.DataPemilik ->  { DataPemilik(
            navigateToDetail = {
                navigationManager.navigateTo(Screen.DetailPemilik(it))
            },
            onClick = {
                navigationManager.navigateTo(Screen.TambahData)
            })
        }
        is Screen.DetailPemilik -> {
            DetailPemilik(id = currentScreen.id, navigateBack = {
                navigationManager.navigateTo(Screen.DataPemilik)
            })
        }
        is Screen.TambahData -> {
            TambahData(
                navigateBack = {
                    navigationManager.navigateTo(Screen.DataPemilik)
                }
            )
        }
    }
}





