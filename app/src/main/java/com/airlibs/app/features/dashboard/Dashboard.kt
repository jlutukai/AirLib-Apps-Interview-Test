package com.airlibs.app.features.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airlibs.app.R
import com.airlibs.app.activity.ui.theme.Purple40
import com.airlibs.app.activity.ui.theme.Typography
import com.airlibs.app.common.dialogs.ErrorDialog
import com.airlibs.app.common.dialogs.LoaderDialog
import com.airlibs.app.common.utils.asString
import com.airlibs.app.features.app.Medicine
import com.airlibs.domain.models.data.MedicineData
import java.util.Calendar
import javax.inject.Inject

@Composable
fun DashBoardPage(
    viewModel: DashBoardViewModel = hiltViewModel(),
    onNavigate: (Any) -> Unit
) {
    val context = LocalContext.current
    val events = viewModel.events.collectAsStateWithLifecycle(DashBoardEvents.IdleState)
    val uiState by viewModel.dashBoardUiStateState.collectAsStateWithLifecycle()
    val medicines by viewModel.getMedicines().collectAsStateWithLifecycle(emptyList())
    val userDetails by viewModel.getCurrentUserDetails().collectAsStateWithLifecycle("")

    val greetingString = rememberSaveable {
       mutableStateOf( "")
    }
    LaunchedEffect(userDetails) {
        val greeting: GetGreeting = GetGreetingImpl(calendar = Calendar.getInstance())
        greetingString.value = "${greeting.getGreeting()}, $userDetails"
    }



    if (uiState.isLoading) {
        LoaderDialog()
    }

    when (val event = events.value) {
        is DashBoardEvents.Error -> {
            ErrorDialog(
                title = event.errorTitle.asString(context),
                message = event.error.asString(context),
                onDismiss = { viewModel.onEvent(DashBoardEvents.IdleState) }
            )
        }

        DashBoardEvents.IdleState -> {

        }
    }

    DashBoardContent(
        medicines = medicines,
        greetingString = greetingString.value,
        onNavigate = onNavigate
    )

}

@Composable
fun DashBoardContent(medicines: List<MedicineData>, greetingString: String, onNavigate: (Any) -> Unit) {

    Scaffold { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .background(Purple40)
                .padding(paddingValues)
        ) {

            Spacer(Modifier.size(20.dp))
            Text(
                greetingString,
                modifier = Modifier.padding(horizontal = 15.dp),
                style = Typography.titleMedium.copy(
                    color = colorResource(R.color.white),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.size(20.dp))

            Column(
                Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)

            ) {
                MedicineListing(
                    modifier = Modifier.fillMaxSize(),
                    medicines = medicines,
                    onNavigate = onNavigate
                )
            }

        }

    }
}

@Composable
fun MedicineListing(medicines: List<MedicineData>, modifier: Modifier, onNavigate: (Any) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(medicines.size) { index ->
            MedicineItem(
                item = medicines[index],
                onTap = { id->
                    onNavigate(Medicine(id))
                }
            )
        }
    }
}

@Composable
fun MedicineItem(item: MedicineData, onTap: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(color = Purple40.copy(alpha = 0.02f), shape = RoundedCornerShape(8.dp))
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable { onTap(item.id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(item.name, style = Typography.titleLarge)
            Spacer(Modifier.size(10.dp))
            Text(item.dose.ifEmpty { "Dose Not Specified" })
        }
        Spacer(Modifier.size(10.dp))
        Text(item.strength.ifEmpty { "Strength Not Specified" })
    }
}

interface GetGreeting{
    fun getGreeting(): String
}

class GetGreetingImpl (
    private  val calendar: Calendar
): GetGreeting{
    override fun getGreeting(): String {
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        return when (currentHour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }

}
