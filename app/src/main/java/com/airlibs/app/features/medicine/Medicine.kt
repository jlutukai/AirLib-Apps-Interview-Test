package com.airlibs.app.features.medicine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airlibs.app.R
import com.airlibs.app.activity.ui.theme.Purple40
import com.airlibs.app.activity.ui.theme.Typography
import com.airlibs.domain.models.data.MedicineData

@Composable
fun MedicinePage(
    onBackPressed: () -> Unit,
    viewModel: MedicineViewModel = hiltViewModel<MedicineViewModel>()
) {
    val medicine by viewModel.getMedicine().collectAsStateWithLifecycle(null)

    MedicineContent(
        medicine = medicine,
        onBackPressed = onBackPressed
    )
}

@Composable
fun MedicineContent(medicine: MedicineData?, onBackPressed: () -> Unit) {
    Scaffold { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .background(Purple40)
                .padding(paddingValues)
        ) {

            Spacer(Modifier.size(10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {onBackPressed()},
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = stringResource(
                            R.string.go_back
                        )
                    )
                }
                Text(
                    "Medicine Details",
                    modifier = Modifier.padding(horizontal = 10.dp).weight(1f),
                    textAlign = TextAlign.Center,
                    style = Typography.titleMedium.copy(
                        color = colorResource(R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(Modifier.size(10.dp))

            Column(
                Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp)

            ) {
                Spacer(Modifier.size(20.dp))
                ItemContent(
                    title = "Name",
                    value = medicine?.name?:""
                )
                HorizontalDivider(Modifier.padding(horizontal = 15.dp, vertical = 10.dp))
                ItemContent(
                    title = "Dose",
                    value = medicine?.dose?:""
                )
                HorizontalDivider(Modifier.padding(horizontal = 15.dp, vertical = 10.dp))
                ItemContent(
                    title = "Strength",
                    value = medicine?.strength?:""
                )

            }

        }

    }
}

@Composable
fun ItemContent(title: String, value: String) {
    Column (Modifier.padding(horizontal = 20.dp)){
        Text(title, style = Typography.titleMedium.copy(
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        ))
        Spacer(Modifier.size(10.dp))
        Text(value.ifEmpty{ "$title Not Set"}, style = Typography.headlineMedium.copy(
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ))
    }
}
