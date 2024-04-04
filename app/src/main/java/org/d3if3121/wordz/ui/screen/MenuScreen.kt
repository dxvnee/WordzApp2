package org.d3if3121.wordz.ui.screen

import org.d3if3121.wordz.R

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.wordz.model.Words
import org.d3if3121.wordz.model.WordsEvents
import org.d3if3121.wordz.model.WordsState
import org.d3if3121.wordz.ui.component.TopAppBarFinal
import org.d3if3121.wordz.ui.theme.poppinsFontFamily

@Preview(showBackground = true)
@Composable
fun MyComposablePreview() {
    Coba()
}

@Composable
fun Coba(){
    // Membuat dummy data WordsState
    val state = WordsState(
        wordsList = listOf(
            Words("Word 1", "Meaning 1", "Notes 1", "2024-02-01"),
            Words("Word 2", "Meani", "Notes 2", "2024-02-01"),
            Words("Word 3", "Meaningdfd 3", "Notes 3", "2024-02-01")
        ),
        words = remember { mutableStateOf("") },
        meaning = remember { mutableStateOf("") },
        notes = remember { mutableStateOf("") }
    )

    // Menampilkan MenuScreen dengan data dummy
    MenuScreen(state = state, navController = rememberNavController()) { }
}

@OptIn(ExperimentalMaterial3Api::class)
var counter = 1
@Composable
fun MenuScreen(
    state: WordsState,
    navController: NavController,
    onEvent: (WordsEvents) -> Unit
) {
    val quizIcon = false
    val appIcon = true
    val backIcon = false
    Scaffold(

        topBar = {
            TopAppBarFinal.TopAppFinal(navController, state, quizIcon, appIcon, backIcon){
                TopAppContext()
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    state.words.value = ""
                    state.meaning.value = ""
                    navController.navigate("AddWordsScreen")
                },
                    containerColor = warnaUngu,
                    shape = RoundedCornerShape(15.dp),
                ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new words",
                    tint = warnaPutih
                )
            }
        },

    ) { paddingValues ->



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            if (state.wordsList.size == 0){
                Text(
                    text = stringResource(R.string.no_words),
                    color = warnaUngu,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.my_words),
                    color = warnaUngu,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(state.wordsList.size) { index ->
                    WordsItem(
                        listnumber = counter++,
                        state = state,
                        index = index,
                        onEvent = onEvent
                    )
                }

            }
            Box(
                modifier = Modifier
                    .padding(paddingValues)
            ){

            }
        }


    }

}

@Composable
fun WordsItem(
    listnumber: Int,
    state: WordsState,
    index: Int,
    onEvent: (WordsEvents) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = warnaPutih)
            .padding(6.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp,))
                .background(color = warnaUngu)
                .padding(horizontal = 20.dp, vertical = 7.dp),

            ) {
            Column(
                modifier = Modifier.weight(1f),

                ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = state.wordsList[index].words,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = warnaPutih
                    )

                    Spacer(modifier = Modifier.width(10.dp)) // Memberikan padding di kiri ikon
                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = "Add new words",
                        tint = warnaPutih,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = state.wordsList[index].meaning,
                        fontSize = 16.sp,
                        color = warnaPutih,
                        fontWeight = FontWeight.Normal,

                        )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End, // Mengatur space antara elemen di dalam row
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            onClick = {
                                onEvent(WordsEvents.DeleteWords(state.wordsList[index]))
                            },
                            modifier = Modifier
                                .size(35.dp)
                                .align(Alignment.Bottom)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Delete Note",
                                modifier = Modifier.size(25.dp),
                                tint = warnaPutih,
                                )

                        }
                    }


                }
                Spacer(modifier = Modifier.height(8.dp))

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp,))
                .background(color = warnaPutih)
                .padding(vertical = 12.dp, horizontal = 18.dp)

        ){
            Row(

            )
            {
                Text(
                    text = stringResource(R.string.notes),
                    fontSize = 16.sp,
                    color = warnaUnguTua,
                    fontWeight = FontWeight.SemiBold,

                    )
                Text(
                    text = state.wordsList[index].notes,
                    fontSize = 16.sp,
                    color = warnaUnguTua,
                    fontWeight = FontWeight.Normal,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.wordsList[index].dateAdded,
                    fontSize = 10.sp,
                    color = warnaAbu,
                    fontWeight = FontWeight.Light,

                    )

            }

        }

    }

}


val warnaUngu = Color(0xFF4200DB)
val warnaUnguTua = Color(0xFF3300A8)
val warnaPutih = Color(0xFFFFFFFF)
val warnaHitam = Color(0xFF000000)
val warnaAbu = Color(0xFF747474)
val warnaMerah = Color(0xFFCA0000)

@Composable
fun TopAppContext() {
    Text(text = stringResource(R.string.app_name),
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = warnaPutih,
        modifier = Modifier.padding(vertical = 17.dp, horizontal = 3.dp)
    )
}
