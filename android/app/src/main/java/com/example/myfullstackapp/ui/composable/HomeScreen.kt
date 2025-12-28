package com.example.myfullstackapp.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfullstackapp.network.ApiClient
import com.example.myfullstackapp.ui.state.UiState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    var state by remember { mutableStateOf<UiState>(UiState.Loading) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            state = try {
                val data = ApiClient.api.getHome()
                UiState.Success(data)
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)      // ✅ this avoids overlap with top bars
            .padding(16.dp)             // ✅ nice screen padding
    ) {
        when (val s = state) {
            UiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(12.dp))
                    Text("Loading...")
                }
            }

            is UiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(s.message)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = {
                        scope.launch {
                            state = UiState.Loading
                            state = try {
                                UiState.Success(ApiClient.api.getHome())
                            } catch (e: Exception) {
                                UiState.Error(e.message ?: "Unknown error")
                            }
                        }
                    }) {
                        Text("Retry")
                    }
                }
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = s.data.greeting,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Cards from your Experience API",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    items(s.data.cards) { card ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = card.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = card.subtitle,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
