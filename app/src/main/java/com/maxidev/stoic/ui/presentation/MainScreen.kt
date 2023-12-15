package com.maxidev.stoic.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maxidev.stoic.R
import com.maxidev.stoic.data.model.StoicModel
import com.maxidev.stoic.ui.components.AppButton
import com.maxidev.stoic.ui.components.AppTopBar
import com.maxidev.stoic.ui.state.NetTypeState
import com.maxidev.stoic.ui.viewmodel.StoicViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: StoicViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            AppTopBar(title = R.string.app_name)
        }
    ) { paddingValues ->
        CheckNet(
            modifier = modifier.padding(paddingValues),
            netTypeState = viewModel.randomStoicState,
            onClick = {
                viewModel.getRandomStoicQuote()
            }
        )
    }
}

@Composable
private fun CheckNet(
    modifier: Modifier,
    netTypeState: NetTypeState,
    onClick: () -> Unit
) {
    when (netTypeState) {
        is NetTypeState.Success -> ContentStoic(
            stoic = netTypeState.onSuccess,
            modifier = modifier,
            onClick = onClick
        )
        is NetTypeState.Error -> ConnectionError(
            errorText = R.string.connection_problems
        )
        is NetTypeState.Loading -> Loading()
    }
}

@Composable
private fun ContentStoic(
    modifier: Modifier = Modifier,
    stoic: StoicModel,
    onClick: () -> Unit
) {
    QuoteAndAuthor(
        modifier = modifier,
        quote = stoic.text,
        author = stoic.author,
        onClick = onClick
    )
}

@Composable
private fun QuoteAndAuthor(
    modifier: Modifier = Modifier,
    quote: String,
    author: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthorStoic(
            author = author
        )
        QuoteStoic(
            quote = quote
        )
        AppButton(
            buttonName = R.string.random_quote,
            onClick = onClick
        )
    }
}

@Composable
private fun QuoteStoic(quote: String) {
    val quoteChange by remember { mutableStateOf(quote) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = """
                "$quoteChange"
            """.trimIndent(),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
private fun AuthorStoic(author: String) {
    val authorChange by remember { mutableStateOf(author) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = 5.dp,
                bottom = 5.dp,
                end = 5.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = authorChange,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}