package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ionspin.kotlin.bignum.integer.toBigInteger
import io.swisseth.solidity.model.Solidity
import org.example.project.contracts.HelloWorld
import org.example.project.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sampleapp.composeapp.generated.resources.Res
import sampleapp.composeapp.generated.resources.app_title
import sampleapp.composeapp.generated.resources.encode_description
import sampleapp.composeapp.generated.resources.encode_sayhello_button
import sampleapp.composeapp.generated.resources.encode_sayhelloto_button
import sampleapp.composeapp.generated.resources.encode_sum_button
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
fun App() = AppTheme {
    val platform = getPlatform()

    var encodedData by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var sumNum1 by remember { mutableStateOf<Long>(0) }
    var sumNum2 by remember { mutableStateOf<Long>(0) }

    var name by remember { mutableStateOf("") }

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

        AppButton(
            text = stringResource(Res.string.encode_sayhello_button),
            encodingCall = {
                HelloWorld.SayHelloWorld.encode()
            },
            onSuccess = { encoded ->
                encodedData = encoded
                errorMessage = null
            },
            onError = { error ->
                errorMessage = "Error: ${error.message}"
                encodedData = null
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppButton(
                text = stringResource(Res.string.encode_sum_button),
                encodingCall = {
                    HelloWorld.Sum.encode(
                        a = Solidity.UInt256(sumNum1.toBigInteger()),
                        b = Solidity.UInt256(sumNum2.toBigInteger()),
                    )
                },
                onSuccess = { encoded ->
                    encodedData = encoded
                    errorMessage = null
                },
                onError = { error ->
                    errorMessage = "Error: ${error.message}"
                    encodedData = null
                },
                modifier = Modifier.weight(1f),
            )

            OutlinedTextField(
                value = sumNum1.toString(),
                onValueChange = { value ->
                    sumNum1 = value.toLongOrNull() ?: 0
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.width(80.dp),
            )

            OutlinedTextField(
                value = sumNum2.toString(),
                onValueChange = { value ->
                    sumNum2 = value.toLongOrNull() ?: 0
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.width(80.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppButton(
                text = stringResource(Res.string.encode_sayhelloto_button),
                encodingCall = {
                    HelloWorld.SayHelloTo.encode(
                        name = Solidity.String(name)
                    )
                },
                onSuccess = { encoded ->
                    encodedData = encoded
                    errorMessage = null
                },
                onError = { error ->
                    errorMessage = "Error: ${error.message}"
                    encodedData = null
                },
                modifier = Modifier.weight(2f),
            )

            OutlinedTextField(
                value = name,
                onValueChange = { value ->
                    name = value
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
        }

        AnimatedVisibility(errorMessage.isNullOrBlank().not()) {
            AppCard(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = errorMessage.orEmpty(),
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = bodySize
                    ),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }

        AnimatedVisibility(encodedData.isNullOrBlank().not()) {
            EncodedDataCard(
                encodedData = encodedData.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
            )
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

@Composable
private fun AppButton(
    text: String,
    encodingCall: () -> String,
    onSuccess: (String) -> Unit,
    onError: (Exception) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            try {
                val encoded = encodingCall()
                onSuccess(encoded)
            } catch (e: Exception) {
                onError(e)
            }
        },
        modifier = modifier,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = bodySize,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            content = { content() },
        )
    }
}

@Composable
private fun EncodedDataCard(
    encodedData: String,
    modifier: Modifier = Modifier,
) = AppCard(
    modifier = modifier,
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
            text = encodedData,
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
