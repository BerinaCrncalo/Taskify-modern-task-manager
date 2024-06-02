package com.example.taskify_moderntaskmanager

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskify_moderntaskmanager.ui.home.HomeDestination
import com.example.taskify_moderntaskmanager.ui.info.InfoDestination
import com.example.taskify_moderntaskmanager.ui.navigation.TaskifyNavHost
import com.example.taskify_moderntaskmanager.ui.task_finished.TaskifyFinishedDestination
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskifyApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val bottomBarList = listOf(
        HomeDestination.route,
        TaskifyFinishedDestination.route,
        InfoDestination.route
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                modifier = modifier
                    .height(54.dp)
                    .padding(bottom = 6.dp, start = 6.dp, end = 6.dp),
                selectedIndex = selectedIndex,
                cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                ballAnimation = Straight(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = Color(0xFF320064),
                ballColor = Color(0xFF320064),
            ) {
                bottomBarList.forEachIndexed { index, route ->
                    Box(
                        modifier = modifier.background(Color(0xFF320064))
                            .fillMaxSize()
                            .noRippleClickable {
                                selectedIndex = index
                                navController.navigate(route)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = modifier.size(26.dp),
                            imageVector = when (route) {
                                HomeDestination.route -> Icons.Default.Home
                                TaskifyFinishedDestination.route -> Icons.Default.CheckCircle
                                InfoDestination.route -> Icons.Default.Info
                                else -> Icons.Default.Home
                            },
                            contentDescription = null,
                            tint = if (selectedIndex == index) Color(0xFFD9D0DE) else Color(0xFFECE7EE)
                        )
                    }
                }
            }
        }
    ) {
        TaskifyNavHost(navController = navController)
    }
}
