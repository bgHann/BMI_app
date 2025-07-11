package com.example.bmi_app_nhom17.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_app_nhom17.viewmodel.BmiViewModel

@Composable
fun DetailsScreen(
    bmi: Float,
    onResult: (Float, String) -> Unit,
    viewModel: BmiViewModel = viewModel()
) {
    val category = viewModel.getCategory(bmi)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "SUMMARY",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Your BMI: ")

                    withStyle(style = SpanStyle(fontSize = 32.sp, color = Color.Black)) {
                        append("%.1f".format(bmi)) // BMI to hơn
                    }

                    append("  ")

                    withStyle(style = SpanStyle(fontSize = 20.sp, color = Color.Gray)) {
                        append(category) // Phân loại nhỏ hơn
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(10.dp, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF2F2F2)),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                BmiCategoryRow("Less than 18.5", "Underweight")
                BmiCategoryRow("18.5 to 24.9", "Healthy")
                BmiCategoryRow("25 to 29.9", "Overweight")
                BmiCategoryRow("30 or above", "Obese")
            }
        }

        Button(
            onClick = {
                onResult(bmi, category)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Results")
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun BmiCategoryRow(range: String, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = range, color = Color.Gray)
        Text(text = label, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    MaterialTheme {
        DetailsScreen(
            bmi = 23.5f,
            onResult = { bmi, category -> println("$bmi, $category") }
        )
    }
}
