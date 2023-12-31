package com.odisby.copa_feminina.features

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.odisby.copa.womens.domain.model.MatchDomain
import com.odisby.copa.womens.domain.model.TeamDomain
import com.odisby.copa_feminina.R
import com.odisby.copa_feminina.data.remote.mapper.toDeviceTimeString
import com.odisby.copa_feminina.ui.theme.Shapes

typealias NotificationOnClick = (match: MatchDomain) -> Unit
@Composable
fun MainScreen(prevMatches: List<MatchDomain>, nextMatches: List<MatchDomain>, onNotificationClick: NotificationOnClick) {
    val tabs = listOf(stringResource(id = R.string.next_games_tab), stringResource(id = R.string.prev_games_tab))

    var tabIndex by remember { mutableStateOf(0) }

    Scaffold { padding ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
            verticalArrangement = Arrangement.Top

        ) {
            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },

                        )
                }
            }
            when (tabIndex) {
                0 -> NextGames(matches = nextMatches, onNotificationClick)
                1 -> PrevGames(matches = prevMatches)
            }
        }

    }
}

@Composable
fun NextGames(matches: List<MatchDomain>, onNotificationClick: NotificationOnClick) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(matches) { match ->
                MatchInfo(match, onNotificationClick)
            }
        }
    }
}

@Composable
fun PrevGames(matches: List<MatchDomain>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(matches) { match ->
                MatchInfo(match, null, prevGame = true)
            }
        }
    }
}

@Composable
fun MatchInfo(match: MatchDomain, onNotificationClick: NotificationOnClick?, prevGame: Boolean = false) {
    Card(
        shape = Shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
        ) {
            if(prevGame) {
                Title(match)
                TeamsPrevGame(match)
            } else {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Title(match)
                    Notification(match, onNotificationClick!!)
                }
                Teams(match)
            }
        }
    }
}

@Composable
fun Notification(match: MatchDomain, onNotificationClick: NotificationOnClick) {

    val context = LocalContext.current
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else mutableStateOf(true)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
            if(isGranted) {
                onNotificationClick(match)
            }
        }
    )

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        val drawable = if (match.notificationEnabled) R.drawable.ic_notification_active
        else R.drawable.ic_notification_disable

        Image(
            painter = painterResource(id = drawable),
            modifier = Modifier.clickable {
                if(hasNotificationPermission) {
                    onNotificationClick(match)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            },
            contentDescription = null
        )
    }
}

@Composable
fun Title(match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = match.name.uppercase(),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = " - ",
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = match.date.toDeviceTimeString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Teams(match: MatchDomain) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TeamItem(team = match.teamA)
        Text(
            text = "x",
            modifier = Modifier.padding(end = 16.dp, start = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        TeamItem(team = match.teamB, right = true)
    }
}

@Composable
fun TeamsPrevGame(match: MatchDomain) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PrevTeamItem(match)
        Text(
            text = "x",
            modifier = Modifier.padding(end = 16.dp, start = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        PrevTeamItem(match, right = true)
    }
}

@Composable
fun TeamItem(team: TeamDomain, right: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if(team.displayName != null) {
            if(right) {
                Text(
                    text = team.flag!!,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = team.displayName!!,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
            } else {
                Text(
                    text = team.displayName!!,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = team.flag!!,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
            }
        } else {
            if(right) {
                Image(
                    painter = painterResource(id = R.drawable.ic_country_flag_default),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.country_not_defined),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.country_not_defined),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_country_flag_default),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun PrevTeamItem(match: MatchDomain, right: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if(right) {
            Text(
                text = match.score.teamAScore.toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = match.teamA.flag!!,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
            )
        } else {
            Text(
                text = match.teamB.flag!!,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = match.score.teamBScore.toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight =  FontWeight.Bold)
            )
        }
    }

}