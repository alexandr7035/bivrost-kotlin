package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.contracts.HelloWorld
import org.example.project.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sampleapp.composeapp.generated.resources.Res
import sampleapp.composeapp.generated.resources.app_title
import sampleapp.composeapp.generated.resources.encode_button
import sampleapp.composeapp.generated.resources.encode_description
import sampleapp.composeapp.generated.resources.encoded_data_description
import sampleapp.composeapp.generated.resources.encoded_data_label
import sampleapp.composeapp.generated.resources.template_platform

private val titleSize = 32.sp
private val subtitleSize = 20.sp
private val bodySize = 18.sp
private val labelSize = 16.sp
private val codeSize = 16.sp

@Composable
@Preview
fun App() {
    AppTheme {
        val platform = getPlatform()
        var encodedData by remember { mutableStateOf<String?>(null) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .safeDrawingPadding()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.app_title),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(Res.string.encode_description),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = subtitleSize
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    try {
                        val encoded = HelloWorld.SayHelloWorld.encode()
                        encodedData = encoded
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = "Error: ${e.message}"
                        encodedData = null
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(Res.string.encode_button),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = bodySize,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            errorMessage?.let { error ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Text(
                        text = error,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = bodySize
                        ),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }

            encodedData?.let { encoded ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.encoded_data_label),
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = labelSize,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        SelectionContainer {
                            Text(
                                text = encoded,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = codeSize,
                                    fontFamily = FontFamily.Monospace
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = stringResource(Res.string.encoded_data_description),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = labelSize
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(Res.string.template_platform, platform.name),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = bodySize
                ),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
