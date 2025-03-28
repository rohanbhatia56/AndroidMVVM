package com.example.myapplication.presentation.ui

import android.annotation.SuppressLint
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.theme.UserListTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("ShowToast")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListTheme {
                val scrollBehavior =
                    TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }
                var selected by remember { mutableStateOf(false) }
                var user by remember { mutableStateOf<User?>(null) }
                var userId by remember { mutableStateOf<String>("") }
                val context = LocalContext.current
                var onTimeout by remember { mutableStateOf("default") }
                val latestOnTimeout = rememberUpdatedState(onTimeout)
                var sensorManager = remember { mutableStateOf("sensor") }
                LaunchedEffect(Unit) {
                    println("LaunchedEffect 1 called")
                    delay(5000)  // 5 seconds delay
                    println(latestOnTimeout.value)  // Calls the latest lambda reference
                }

                LaunchedEffect(userId) {
                    println("LaunchedEffect called")
                    println(latestOnTimeout.value)
                    Toast.makeText(context, "Id updated", Toast.LENGTH_SHORT)
                }

                DisposableEffect(sensorManager) {
                    println("DisposableEffect")
                    onDispose {  // Cleanup when Composable leaves the UI
                        println("onDispose")
                    }
                }

                SideEffect {
                    Log.d("Counter", "Recomposed with count: ")
                }
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = colorScheme.primaryContainer,
                                titleContentColor = colorScheme.primary,
                            ),
                            title = {
                                Card(
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 6.dp
                                    ),
                                ) {
                                    Text(text = "Hello, world!")

                                    Text(
                                        "Centered Top App Bar",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            actions = {
                                IconButton(onClick = { userId = "new" }) {
                                    Icon(
                                        Icons.Filled.Check,
                                        contentDescription = "Localized description"
                                    )
                                }
                                IconButton(onClick = { onTimeout="ff" }) {
                                    Icon(
                                        Icons.Filled.Edit,
                                        contentDescription = "Localized description",
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Refresh,
                                        contentDescription = "Localized description",
                                    )
                                }
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        Icons.Filled.Person,
                                        contentDescription = "Localized description",
                                    )
                                }

//                                AssistChip(
//                                    onClick = { Log.d("Assist chip", "hello world") },
//                                    label = { Text("Assist chip") },
//                                    leadingIcon = {
//                                        Icon(
//                                            Icons.Filled.Edit,
//                                            contentDescription = "Localized description",
//                                            Modifier.size(AssistChipDefaults.IconSize)
//                                        )
//                                    }
//                                )

                                FilterChip(
                                    onClick = { selected = !selected },
                                    label = {
                                        Text("Filter chip")
                                    },
                                    selected = selected,
                                    leadingIcon = if (selected) {
                                        {
                                            Icon(
                                                imageVector = Icons.Filled.Done,
                                                contentDescription = "Done icon",
                                                modifier = Modifier.size(5.dp)
                                            )
                                        }
                                    } else {
                                        null
                                    },
                                )


                            },
                            floatingActionButton = {
                                FloatingActionButton(
                                    onClick = { showBottomSheet = true },
                                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                ) {
                                    Icon(Icons.Filled.Add, "Localized description")
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    val navController = rememberNavController()


                    NavHost(navController = navController, startDestination = "UserListScreen") {
                        composable("UserListScreen") {
                            UserListScreen()
                        }
                        composable("UserScreen/{data}") { backStackEntry ->
                            val data = backStackEntry.arguments?.getString("data") ?: "No Data"
                            UserScreen(navController, data)
                        }
                    }


                    if (showBottomSheet) {
                        ModalBottomSheet(
                            modifier = Modifier.fillMaxHeight(),
                            sheetState = sheetState,
                            onDismissRequest = { showBottomSheet = false }
                        ) {
                            Text(
                                "Swipe up to open sheet. Swipe down to dismiss.",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}