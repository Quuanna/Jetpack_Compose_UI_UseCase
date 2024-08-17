package com.anna.jetpack_compose_project_usecase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anna.jetpack_compose_project_usecase.data.BottomMenuContent
import com.anna.jetpack_compose_project_usecase.data.Feature
import com.anna.jetpack_compose_project_usecase.ui.theme.Beige1
import com.anna.jetpack_compose_project_usecase.ui.theme.Beige2
import com.anna.jetpack_compose_project_usecase.ui.theme.Beige3
import com.anna.jetpack_compose_project_usecase.ui.theme.BlueViolet1
import com.anna.jetpack_compose_project_usecase.ui.theme.BlueViolet2
import com.anna.jetpack_compose_project_usecase.ui.theme.BlueViolet3
import com.anna.jetpack_compose_project_usecase.ui.theme.ButtonBlue
import com.anna.jetpack_compose_project_usecase.ui.theme.DarkerButtonBlue
import com.anna.jetpack_compose_project_usecase.ui.theme.DeepBlue
import com.anna.jetpack_compose_project_usecase.ui.theme.LightGreen1
import com.anna.jetpack_compose_project_usecase.ui.theme.LightGreen2
import com.anna.jetpack_compose_project_usecase.ui.theme.LightGreen3
import com.anna.jetpack_compose_project_usecase.ui.theme.LightRed
import com.anna.jetpack_compose_project_usecase.ui.theme.OrangeYellow1
import com.anna.jetpack_compose_project_usecase.ui.theme.OrangeYellow2
import com.anna.jetpack_compose_project_usecase.ui.theme.OrangeYellow3
import com.anna.jetpack_compose_project_usecase.util.standardQuadFromTo


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipsSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        "Sleep meditation",
                        R.drawable.ic_action_music,
                        BlueViolet3,
                        BlueViolet2,
                        BlueViolet1

                    ),
                    Feature(
                        "Tips for sleeping",
                        R.drawable.ic_action_tips,
                        LightGreen3,
                        LightGreen2,
                        LightGreen1
                    ),
                    Feature(
                        "Night island",
                        R.drawable.ic_action_night,
                        OrangeYellow3,
                        OrangeYellow2,
                        OrangeYellow1
                    ),
                    Feature(
                        "Calming sounds",
                        R.drawable.ic_action_cloud,
                        Beige3,
                        Beige2,
                        Beige1
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_bottom_home),
                BottomMenuContent("Meditate", R.drawable.ic_bottom_meditate),
                BottomMenuContent("Sleep", R.drawable.ic_bottom_night),
                BottomMenuContent("Music", R.drawable.ic_bottom_headset),
                BottomMenuContent("Profile", R.drawable.ic_bottom_person)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    initialSelectedItemIndex: Int = 0
) {

    var selectedItemIndex by remember {
        mutableIntStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.LightGray,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}


@Composable
fun GreetingSection(
    name: String = "Anna"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Text(
                text = "We wish you have a good day!",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_action_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)

        )
    }
}

@Composable
fun ChipsSection(chips: List<String>) {

    var selectedChipIndex by remember {
        mutableIntStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }
}


@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Text(
                text = "Meditation • 3-10 min",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_action_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}


@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val (mediumColorPath, lightColorPath) = backgroundColorPair()

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = mediumColorPath,
                color = feature.mediumColor
            )

            drawPath(
                path = lightColorPath,
                color = feature.lightColor
            )
        }

        // Item
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.White
            )

            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            Text(
                text = "start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

@Composable
private fun BoxWithConstraintsScope.backgroundColorPair(): Pair<Path, Path> {
    // background color
    val width = constraints.maxWidth
    val height = constraints.maxHeight

    // Medium colored path
    val mediumColorPoint1 = Offset(0f, height * 0.3f)
    val mediumColorPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColorPoint3 = Offset(width * 0.4f, height * 0.05f)
    val mediumColorPoint4 = Offset(width * 0.75f, height * 0.7f)
    val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

    val mediumColorPath = Path().apply {
        moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
        standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
        standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
        standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
        standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }

    // light colored path
    val lightColorPoint1 = Offset(0f, height * 0.35f)
    val lightColorPoint2 = Offset(width * 0.1f, height * 0.4f)
    val lightColorPoint3 = Offset(width * 0.3f, height * 0.35f)
    val lightColorPoint4 = Offset(width * 0.65f, height.toFloat())
    val lightColorPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

    val lightColorPath = Path().apply {
        moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
        standardQuadFromTo(lightColorPoint1, lightColorPoint2)
        standardQuadFromTo(lightColorPoint2, lightColorPoint3)
        standardQuadFromTo(lightColorPoint3, lightColorPoint4)
        standardQuadFromTo(lightColorPoint4, lightColorPoint5)
        lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }
    return Pair(mediumColorPath, lightColorPath)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen()
}
